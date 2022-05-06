import express from 'express'
import { getExamplesByWords } from '../controllers/exampleController.js';
import { getWords } from '../controllers/wordController.js';

const router = express.Router();

router.route('/search').get(getWords)
router.route('/:wordId').get(getExamplesByWords)

export default router;