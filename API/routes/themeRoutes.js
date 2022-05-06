import express from 'express'
import { getThemes, getThemesById, createTheme } from '../controllers/themeController.js';
import { getWordsByTheme, createWord, getWordById } from '../controllers/themeController.js';

const router = express.Router();

router.route('/:themeId').get(getThemesById)
router.route('/').get(getThemes).post(createTheme);
router.route('/:themeId/words').get(getWordsByTheme).post(createWord)
router.route('/:themeId/words/:wordId').get(getWordById);

export default router;