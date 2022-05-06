import express from 'express'
import { createExample } from '../controllers/exampleController.js';

const router = express.Router();

router.route('/').post(createExample)

export default router;