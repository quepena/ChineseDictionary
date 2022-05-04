import mongoose from 'mongoose'

const exampleSchema = new mongoose.Schema({
    charachterId: {
        type: String,
        required: true
    },
    charachters: {
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

const Example = mongoose.model('Examples', exampleSchema);

export default Example