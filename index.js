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
// app.set('view engine', 'mustache');
// app.engine('mustache', mustache());

app.get('/', (req, res) => {
    res.sendFile(__dirname + '/public/anneslogin.html')
});

app.post('/register', async (req, res) => {
    res.send('register');
});

app.post('/login', async (req, res) => {
    res.send('login');
});

server.listen(port, hostname, () => {
    console.log(`Server running at http://${hostname}:${port}/`);
});
