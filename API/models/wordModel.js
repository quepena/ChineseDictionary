import mongoose from 'mongoose'

const wordSchema = new mongoose.Schema({
    themeId: {
        type: Array,
        required: true,
    },
    character: {
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
    pinyinVariations: {
        type: String,
    },
    translation: {
        type: String,
        required: true
    },
})

wordSchema.pre('save', async function() {
    this.utf8 = await encodeURI(this.character)

    switch (this.pinyin) {
        case this.pinyin.includes(/[\u{0101}|\u{00E0}|\u{00E1}|\u{1CE}]/gu):
            this.pinyinVariations = await this.pinyin.replace(/[\u{0101}|\u{00E0}|\u{00E1}|\u{1CE}]/gu, "a");
    
        case this.pinyin.includes(/[\u{00EC}|\u{00ED}|\u{1D0}|\u{12B}]/gu):
            this.pinyinVariations = await this.pinyin.replace(/[\u{00EC}|\u{00ED}|\u{1D0}|\u{12B}]/gu, "i");

        case this.pinyin.includes(/[\u{14D}|\u{F2}|\u{1D2}|\u{F3}]/gu):
            this.pinyinVariations = await this.pinyin.replace(/[\u{14D}|\u{F2}|\u{1D2}|\u{F3}]/gu, "o");

        case this.pinyin.includes(/[\u{16B}|\u{F2}|\u{1D2}|\u{F3}]/gu):
            this.pinyinVariations = await this.pinyin.replace(/[\u{14D}|\u{F2}|\u{1D2}|\u{F3}]/gu, "u");
    }
})

const Word = mongoose.model('Words', wordSchema);

export default Word