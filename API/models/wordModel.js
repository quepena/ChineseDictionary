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
    pinyin: {
        type: String,
        required: true
    },
    pinyinWithoutSpaces: {
        type: String
    },
    pinyinVariations: {
        type: String,
    },
    pinyinVariationsWithoutSpaces: {
        type: String,
    },
    translation: {
        type: String,
        required: true
    },
})

wordSchema.pre('save', async function () {
    const pinyinSaved = this.pinyin

    for (let index = 0; index <= this.pinyin.split(" ").length - 1; index++) {
        switch (true) {

            //ā à á ǎ
            case (/[\u{0101}|\u{00E0}|\u{00E1}|\u{1CE}]/gu).test(this.pinyin):
                this.pinyinVariations = await this.pinyin.replace(/[\u{0101}|\u{00E0}|\u{00E1}|\u{1CE}]/gu, "a")
                this.pinyin = this.pinyinVariations
                break

            //ì í ǐ ī
            case (/[\u{00EC}|\u{00ED}|\u{1D0}|\u{12B}]/gu).test(this.pinyin):
                this.pinyinVariations = await this.pinyin.replace(/[\u{00EC}|\u{00ED}|\u{1D0}|\u{12B}]/gu, "i")
                this.pinyin = this.pinyinVariations
                break

            //ō ò ǒ ó
            case (/[\u{14D}|\u{F2}|\u{1D2}|\u{F3}]/gu).test(this.pinyin):
                this.pinyinVariations = await this.pinyin.replace(/[\u{14D}|\u{F2}|\u{1D2}|\u{F3}]/gu, "o")
                this.pinyin = this.pinyinVariations
                break

            //ē é ě è    
            case (/[\u{113}|\u{E9}|\u{11B}|\u{E8}]/gu).test(this.pinyin):
                this.pinyinVariations = await this.pinyin.replace(/[\u{113}|\u{E9}|\u{11B}|\u{E8}]/gu, "e")
                this.pinyin = this.pinyinVariations
                break

            //ū ú ǔ ù ǖ ǘ ǚ ǜ
            case (/[\u{16B}|\u{FA}|\u{1D4}|\u{F9}|\u{1D6}|\u{1D8}|\u{1DA}|\u{1DC}]/gu).test(this.pinyin):
                this.pinyinVariations = await this.pinyin.replace(/[\u{16B}|\u{FA}|\u{1D4}|\u{F9}|\u{1D6}|\u{1D8}|\u{1DA}|\u{1DC}]/gu, "u")
                this.pinyin = this.pinyinVariations
                break

            default:
                this.pinyinVariations = this.pinyin
                this.pinyin = this.pinyinVariations
                break
        }
    }
    this.pinyin = pinyinSaved

    this.pinyinWithoutSpaces = this.pinyin.replace(/ /g, '')
    this.pinyinVariationsWithoutSpaces = this.pinyinVariations.replace(/ /g, '')
})

const Word = mongoose.model('Words', wordSchema);

export default Word