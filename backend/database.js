function connection() {
    let db = require('mysql')

    let connection = db.createConnection({
      host: "localhost",
      user: "root",
      password: "root",
      database: "TruckTrack_DB"
    })

    return new Promise(
      function(resolve, reject) {
      connection.connect(function(err) {
        if(err) reject(err)
        resolve(connection)
      })
    })
}

function authLogin(connection, data) {
  return new Promise(
    function(resolve, reject) {
      connection.query("SELECT COUNT(email) as sum FROM korisnik WHERE \
      email = ? AND lozinka = ?", [data.email, data.lozinka], function(err, result, fields) {
        console.log("result", result[0], err)
        if(err) reject(err)
        if(result[0].sum === 0) reject(err)
        else resolve(true)
      })
    }
  )
}


module.exports = {
  connect: connection,
  authLogin: authLogin

}
