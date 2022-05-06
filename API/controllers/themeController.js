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

    res.json(themes);
})

const createWord = asyncHandler(async(req, res) => {
    const { themeId, character, utf8, pinyin, pinyinVariations, translation } = req.body;

    const newWord = await Word.create({
        themeId, character, utf8, pinyin, pinyinVariations, translation
    })

    if(newWord) {
        res.status(201).json({
            _id: newWord._id,
            themeId: newWord.themeId,
            character: newWord.character,
            utf8: newWord.utf8,
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

    res.json(words);
})

const getWordById = asyncHandler(async (req, res) => {
    const word = await Word.findById(req.params.id);

    res.json(word);
})

export { createTheme, getThemesById, getThemes, createWord, getWordsByTheme, getWordById };