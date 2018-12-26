// Create a simple server using express (DONE)
// that returns static files (hard coded text files or images you provide).
// The server should use the stream api pipe() to output the data.
// Hint: res is actually a stream!
// The server should serve the files using a custom URL.
// Example - http://server/files/file-name
// Tag it as EXERCISE_05


//add the relevant packages
const express = require('express');
const stream = require('stream');
const fs = require('fs');

//create the express app
let app = express();

//decide the port
const PORT = 8080;
// get file from server

app.get('files/:filename', (req,res) => {
  const filename = req.params.filename;
  fs.createReadStream('./files/${file}.txt').pipe(fs.createWriteStream(res));
  //res.set('Content-Type', 'text/html');
  res.status(200).send(res);
  return res;
});

//listining to the URL
app.listen(PORT, () => {
  console.log(`listening at port ${PORT}`);
})
