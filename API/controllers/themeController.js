import Theme from '../models/themeModel.js';
import Word from '../models/wordModel.js';
import asyncHandler from 'express-async-handler';

const createTheme = asyncHandler(async(req, res) => {
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
    const themes = await Theme.find({})

    for (let i = 0; i < themes.length; i++) {
        if(themes[i].quantity == 0) {
            let count = await Word.countDocuments({
                themeId: themes[i].id
            })

            await Theme.findOneAndUpdate({_id: themes[i].id}, {quantity: count}, { new: true });
        }
    }

    res.json({result: themes});
})

const getQuantityThemes = asyncHandler(async (req, res) => {
    const themes = await Theme.find({})

    for (let i = 0; i < themes.length; i++) {
        const count = await Word.countDocuments({
            themeId: themes[i].id
        })
    }

    res.json({result: count});
})

const createWord = asyncHandler(async(req, res) => {
    const { themeId, character, pinyin, pinyinVariations, translation } = req.body;

    const newWord = await Word.create({
        themeId, character, pinyin, pinyinVariations, translation
    })

    if(newWord) {
        res.status(201).json({
            _id: newWord._id,
            themeId: newWord.themeId,
            character: newWord.character,
            pinyin: newWord.pinyin,
            pinyinVariations: newWord.pinyinVariations,
            translation: newWord.translation,
        })
    } else {
        res.status(400).json({message: "Invalid word data"});
    }
})

const getWordsByTheme = asyncHandler(async (req, res) => {
    const words = await Word.find({
        themeId: req.params.themeId
    })

    res.json({result: words});
})


export { createTheme, getThemesById, getThemes, createWord, getWordsByTheme };