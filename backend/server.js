let express = require("express")
let bodyParser = require("body-parser")
let db = require('./database.js')
let connection = null


let app = express()
app.use(bodyParser.json())
app.use(bodyParser.urlencoded())

app.get('/test', function(req, res) {
  console.log("test working")
})

app.post("/user", async(req, res) => {
  if(!req.body.email && !req.body.lozinka) {
    res.sendStatus(400).end()
  }
  //connect to database
  await db.connect()
    .then(
      function(conn) {
        connection = conn
      })
    .catch(
      function(err) {
        res.sendStatus(400).end()
      })

  //authentificate user from post data
  await db.authLogin(connection, req.body).then(
    function(value) {
      res.sendStatus(200)
    }
  ).catch(function(err) {
    console.log("error query", err)
    res.sendStatus(400).end()
  })
  connection.end()
})

app.listen(8000)
