import mongoose from 'mongoose'

const exampleSchema = new mongoose.Schema({
    charactersIds: {
        type: Array,
        required: true
    },
    characters: {
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