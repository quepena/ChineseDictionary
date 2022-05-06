import mongoose from 'mongoose'

const themeSchema = new mongoose.Schema({
    name: {
        type: String,
        required: true,
        unique: true,
    },
})

const Theme = mongoose.model('Themes', themeSchema);

export default Theme