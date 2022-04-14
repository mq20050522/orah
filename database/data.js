const userDB = [];

function addExistingUsers(){
    var fs = require('fs');
    var data = JSON.parse(fs.readFileSync('existingData.json', 'utf-8'));
}

module.exports = { userDB };