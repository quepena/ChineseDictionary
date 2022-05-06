import express from "express"
import 'dotenv/config'
import connectDB from "./config/db.js"
import themeRoutes from "./routes/themeRoutes.js"
import wordRoutes from "./routes/wordRoutes.js"
import exampleRoutes from "./routes/exampleRoutes.js"

connectDB()

const PORT = process.env.PORT || 8080

const app = express()

app.use(express.json())

app.use('/api/themes', themeRoutes)

app.use('/api/words', wordRoutes)

app.use('/api/examples', exampleRoutes)

app.listen(PORT, () => console.log(`App listening at port ${PORT}`))