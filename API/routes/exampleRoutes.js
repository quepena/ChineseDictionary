import express from 'express'
import { createExample, deleteExample } from '../controllers/exampleController.js';

const router = express.Router();

router.route('/').post(createExample).delete(deleteExample)

export default router;