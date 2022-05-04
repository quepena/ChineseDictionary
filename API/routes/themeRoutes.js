import express from 'express'
import { getThemes, getThemesById, createTheme } from '../controllers/themeController.js';

const router = express.Router();

router.route('/:themeId').get(getThemesById).post(createTheme)
router.route('/').get(getThemes);

export default router;