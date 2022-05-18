import mongoose from 'mongoose'

const themeSchema = new mongoose.Schema({
    name: {
        type: String,
        required: true,
        unique: true,
    },
    quantity: {
        type: Number,
        required: true,
        default: 0
    }
})

const Theme = mongoose.model('Themes', themeSchema);

export default Theme