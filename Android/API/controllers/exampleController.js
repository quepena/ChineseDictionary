import Example from "../models/exampleModel.js";
import asyncHandler from 'express-async-handler';

const createExample = asyncHandler(async(req, res) => {
    const { charactersIds, characters, utf8, pinyin, translation } = req.body;

    const newExample = await Example.create({
        charactersIds, characters, utf8, pinyin, translation
    })

    res.json(newExample)
})

const getExamplesByWords = asyncHandler(async (req, res) => {
    const examples = await Example.find({
        charactersIds: { $in: [req.params.wordId] }
    })

    res.json(examples);
})

export { createExample, getExamplesByWords };