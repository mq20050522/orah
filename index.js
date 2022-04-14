const mysql = require('mysql');

const express = require('express')
const app = express();
const http = require('http');
const hostname = '127.0.0.1';
const port = 3000;
const server = require('http').createServer(app);

app.use(express.static(__dirname + "/public"));
app.set('views', __dirname + "/public");
// app.set('view engine', 'mustache');
// app.engine('mustache', mustache());

app.get('/', (req, res) => {
    res.sendFile(__dirname + '/public/anneslogin.html')
})
server.listen(port, hostname, () => {
    console.log(`Server running at http://${hostname}:${port}/`);
});


// const http = require('http');

// const hostname = '127.0.0.1';
// const port = 3000;


// const server = http.createServer((req, res) => {
//   res.statusCode = 200;
//   res.setHeader('Content-Type', 'text/plain');
//   res.end('Hello World');
//   res.sendFile(__dirname + '/anneslogin.html')
// });

// server.listen(port, hostname, () => {
//   console.log(`Server running at http://${hostname}:${port}/`);
// });
