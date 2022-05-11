import Word from '../models/wordModel.js';
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
    res.json({ results: words });
})

export { getWords };