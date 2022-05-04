import mongoose from 'mongoose'

const wordSchema = new mongoose.Schema({
    themeId: {
        type: Array,
        required: true,
    },
    charachter: {
        type: String,
        required: true
    },
    pinyin: {
        type: String,
        required: true
    },
    translation: {
        type: String,
        required: true
    },
})

const Word = mongoose.model('Words', wordSchema);

export default Word