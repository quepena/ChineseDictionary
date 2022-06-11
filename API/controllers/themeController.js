import Theme from '../models/themeModel.js';
import Word from '../models/wordModel.js';
import asyncHandler from 'express-async-handler';

const createTheme = asyncHandler(async (req, res) => {
    const { name } = req.body;

    const newTheme = await Theme.create({
        name
    })

    res.json(newTheme)
})

const getThemesById = asyncHandler(async (req, res) => {
    const theme = await Theme.findById(req.params.id);

    res.json(theme);
})

const getThemes = asyncHandler(async (req, res) => {
    const themes = await Theme.find({}).sort('name')

    for (let i = 0; i < themes.length; i++) {
        let count = await Word.countDocuments({
            themeId: themes[i].id
        })

        await Theme.findOneAndUpdate({ _id: themes[i].id }, { quantity: count }, { new: true });
    }

    res.json({ result: themes });
})

const createWord = asyncHandler(async (req, res) => {
    const { themeId, character, pinyin, pinyinVariations, translation } = req.body;

    const newWord = await Word.create({
        themeId, character, pinyin, pinyinVariations, translation
    })

    if (newWord) {
        res.status(201).json({
            _id: newWord._id,
            themeId: newWord.themeId,
            character: newWord.character,
            pinyin: newWord.pinyin,
            pinyinWithoutSpaces: newWord.pinyinWithoutSpaces,
            pinyinVariations: newWord.pinyinVariations,
            pinyinVariationsWithoutSpaces: newWord.pinyinVariationsWithoutSpaces,
            translation: newWord.translation,
        })
    } else {
        res.status(400).json({ message: "Invalid word data" });
    }
})

const getWordsByTheme = asyncHandler(async (req, res) => {
    const words = await Word.find({
        themeId: req.params.themeId
    })

    res.json({ result: words });
})

const deleteTheme = asyncHandler(async (req, res) => {
    const theme = await Theme.findById(req.params.themeId);

    if (theme) {
        await theme.remove();
        res.json({ messsage: 'Theme removed' })
    } else {
        res.status(404).json({ message: 'Theme not found' });
    }
})


export { createTheme, getThemesById, getThemes, createWord, getWordsByTheme, deleteTheme };