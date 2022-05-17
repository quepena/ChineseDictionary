import Word from '../models/wordModel.js';
import Example from "../models/exampleModel.js";
import asyncHandler from 'express-async-handler';

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
    res.json({ result: words });
})

const getWordById = asyncHandler(async (req, res) => {
    const word = await Word.findById(req.params.wordId);

    res.json({result: word});
})

const getExampleByWord = asyncHandler(async (req, res) => {
    const examples = await Example.find({
        charactersIds: { $in: [req.params.wordId] }
    })

    res.json({result: examples});
})

export { getWords, getWordById, getExampleByWord };