import express from 'express'
import { getWords, getWordById, getExampleByWord, deleteWord } from '../controllers/wordController.js';

const router = express.Router();

router.route('/search').get(getWords)
router.route('/:wordId').get(getWordById).delete(deleteWord)
router.route('/:wordId/examples').get(getExampleByWord)

export default router;