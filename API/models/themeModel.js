import mongoose from 'mongoose'

const themeSchema = new mongoose.Schema({
    name: {
        type: String,
        required: true,
        unique: true,
    },
    // wordsQuantity: {
    //     type: Number,
    //     default: 0,
    // }
})

const Theme = mongoose.model('Themes', themeSchema);

export default Theme