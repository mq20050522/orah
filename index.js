const mysql = require('mysql');

const express = require('express')
const app = express();
const http = require('http');
const hostname = '127.0.0.1';
const port = 3000;
const server = require('http').createServer(app);

const users = require('./database/data').userDB

app.use(express.static(__dirname + "/public"));
app.set('views', __dirname + "/public");

app.get('/', (req, res) => {
    res.sendFile(__dirname + '/public/studentSearch.html');
    // res.send("<h1>hi<h1>");
});

app.get('/login', (req, res) => {
    res.sendFile(__dirname + '/public/anneslogin.html');
})

app.post('/login', (req, res) => {
    let email = req.body.email;
    let password = req.body.password;
    res.send(`Username: ${username} Password: ${password}`)
})

server.listen(port, hostname, () => {
    console.log(`Server running at http://${hostname}:${port}/`);
});
