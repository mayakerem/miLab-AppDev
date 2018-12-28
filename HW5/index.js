// Create a simple server using express (DONE)
// that returns static files (hard coded text files or images you provide). (DONE)
// The server should use the stream api pipe() to output the data. (DONE)
// Hint: res is actually a stream! (DONE)
// The server should serve the files using a custom URL. (DONE)
// Example - http://server/files/file-name
// Tag it as EXERCISE_05


//add the relevant packages
const express = require('express');
//const stream = require('stream');
const fs = require('fs');
var play = require('play');
//create the express app
let app = express();
//decide the port
const PORT = process.env.PORT || 8080;
//set up app to the right PORT
app.set('port',PORT);

//introduction to programm
app.get('/', (req,res) => {
  //title
  const headline = "<h1> Exercise 5 </h1>";
  //instructions
  const content = "<h2> This program will play a sample of the top songs from the year 2000. Please choose the songs by inputting /files/: eyeofthetiger, byebye, itsmylife, beautifulday. TURN UP YOUR SPEAKERS</h2>"
  res.set('Content-Type', 'text/html');
  res.status(200).send(headline+ content);
});

// get file from server, anything the user puts after /files/
app.get('/files/:title', (req,res) => {
  //without txt
  const title = req.params.title;
  //check is a file with this title exists withthe txt
  fs.exists(`./files/${title}.txt`, (exists) => {
    if (exists) {
      //startplaying song
      play.sound(`./sound/${title}.wav`);
      //pipe (merge) reading it and outputting it to the respons
      const rstream = fs.createReadStream(`./files/${title}.txt`);
      rstream.pipe(res);
    } else {
      //reached an error
      const error = "<h3> You have entered a song name not in the list, please choose either: beautifulday, byebyebye, itsmylife, eyeofthetiger. Turn on your speakers! </h3>"
      res.status(404).send(error);
      return;
    }
  });
});

//listining to the URL
app.listen(PORT, () => {
  console.log(`listening at port ${PORT}`);
});
