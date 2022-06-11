import Example from "../models/exampleModel.js";
import asyncHandler from 'express-async-handler';

const createExample = asyncHandler(async(req, res) => {
    const { charactersIds, characters, utf8, pinyin, translation } = req.body;

    const newExample = await Example.create({
        charactersIds, characters, utf8, pinyin, translation
    })

    res.json(newExample)
})

const deleteExample = asyncHandler(async (req, res) => {
    const example = await Example.findById(req.params.exampleId);

    if (example) {
        await example.remove();
        res.json({ messsage: 'Example removed' })
    } else {
        res.status(404).json({ message: 'Example not found' });
    }
})

export { createExample, deleteExample };