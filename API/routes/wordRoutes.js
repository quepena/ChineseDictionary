import express from 'express'
import { getWords, getWordById, getExampleByWord } from '../controllers/wordController.js';

const router = express.Router();

router.route('/search').get(getWords)
router.route('/:wordId').get(getWordById)
router.route('/:wordId/examples').get(getExampleByWord)

export default router;