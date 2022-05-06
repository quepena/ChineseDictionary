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
    utf8: {
        type: String,
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

exampleSchema.pre('save', async function () {
    this.utf8 = await encodeURI(this.characters)
})

const Example = mongoose.model('Examples', exampleSchema);

export default Example