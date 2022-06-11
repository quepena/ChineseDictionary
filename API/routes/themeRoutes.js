import express from 'express'
import { getThemes, getThemesById, createTheme, getWordsByTheme, createWord, deleteTheme } from '../controllers/themeController.js';

const router = express.Router();

router.route('/:themeId').get(getThemesById).delete(deleteTheme)
router.route('/').get(getThemes).post(createTheme);
router.route('/:themeId/words').get(getWordsByTheme).post(createWord)

export default router;