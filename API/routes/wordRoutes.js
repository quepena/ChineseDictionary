import express from 'express'
import { getWords, getWordsByTheme, getWordById, createWord } from '../controllers/wordController.js';

const router = express.Router();

router.route('/:themeId/words').get(getWordsByTheme).post(createWord)
router.route('/:themeId/words/:wordId').get(getWordById);
router.route('/search').get(getWords)

export default router;