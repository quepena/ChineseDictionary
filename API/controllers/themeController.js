import Theme from '../models/themeModel.js';
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

export { createTheme, getThemesById, getThemes };