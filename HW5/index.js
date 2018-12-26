// Create a simple server using express (DONE)
// that returns static files (hard coded text files or images you provide).
// The server should use the stream api pipe() to output the data.
// Hint: res is actually a stream!
// The server should serve the files using a custom URL.
// Example - http://server/files/file-name
// Tag it as EXERCISE_05


//add the relevant packages
const express = require('express');
//const stream = require('stream');
const fs = require('fs');
//create the express app
let app = express();
//decide the port
const PORT = process.env.PORT || 8080;
//set up app to the right PORT
app.set('port',PORT);

// get file from server, anything the user puts after /files/
app.get('/files/:title', (req,res) => {
  //without txt
  const title = req.query.title;
  //check is a file with this title exists withthe txt
  fs.exists(`/files/${title}.txt`, (exists) => {
    if (exists) {
      //pipe (merge) reading it and outputting it to the response
      const rstream = fs.createReadStream(`/files/${title}.txt`);
      rstream.pipe(response);
    } else {
      //reached an error
      response.status(404).send('Encountered Error');
      return;
    }
  });
});

//listining to the URL
app.listen(PORT, () => {
  console.log(`listening at port ${PORT}`);
});
