import express from "express";

const PORT = process.env.PORT || 8080

const app = express()

app.use(express.json())

app.get('/', async (req, res) => {
    res.json({ status: true, message: "Our node.js app works" })
})

app.listen(PORT, () => console.log(`App listening at port ${PORT}`))