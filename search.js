/*
old format: 
["(name), (photo), (class), (notes)","(name), (photo), (class), (notes)","(name), (photo), (class), (notes)"]
new format: 
[["name", "photo", "class", "notes"], ["name", "photo", "class", "notes"], ["name", "photo", "class", "notes"]]
*/
listOStudents = [["Ming", "ming.jpg", "cs", "..."], ["Josh", "josh.png", "math", "^_-"]]
function search(keywords, listOStudents){
    var returnValue = []
    for(var i=0; i<listOStudents.length; i++){
        // console.log(listOStudents[i][0].toLowerCase())
        if(listOStudents[i][0].toLowerCase().includes(keywords)){
            returnValue.push(listOStudents[i])
        }
    }
    return(returnValue)
}

// console.log(search("min", listOStudents))
// console.log(search("osh", listOStudents))