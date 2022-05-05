import Word from '../models/wordModel.js';
import asyncHandler from 'express-async-handler';

const createWord = asyncHandler(async(req, res) => {
    const { themeId, character, utf8, pinyin, translation } = req.body;

    const newWord = await Word.create({
        themeId, character, utf8, pinyin, translation
    })

    if(newWord) {
        res.status(201).json({
            _id: newWord._id,
            themeId: newWord.themeId,
            character: newWord.character,
            utf8: newWord.utf8,
            pinyin: newWord.pinyin,
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

const getWords = asyncHandler(async (req, res) => {
    const keyword = req.query.keyword ? {
        $or: [
            {
                character: {
                    $regex: req.query.keyword,
                }
            },
            {
                pinyin: {
                    $regex: req.query.keyword,
                    $options: 'i'
                }
            },
            {
                pinyinVariations: {
                    $regex: req.query.keyword,
                    $options: 'i'
                }
            },
            {
                translation: {
                    $regex: req.query.keyword,
                    $options: 'i'
                }
            }
        ]
    } : {}

    const words = await Word.find({ ...keyword })
    res.json(words);
})

export { createWord, getWordsByTheme, getWords, getWordById };