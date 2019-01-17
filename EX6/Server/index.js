/*
Create a node.js server that will monitor stock quotes from AlphaVantage https://www.alphavantage.co/. 
Create an Android client for it which allows the user to enter a stock name. 
The server will monitor the last price of the stock and send the client price updates every 15 seconds
Start out with a live connection using Socket.IO. 
Once that works, you can implement push with Firebase in case the Android app isn’t open
*/

const express = require('express');

// Set up the server
const server = require('http').createServer();
const io = require('socket.io')(server);
// Used specific key that alpha gave me
const alpha = require('alphavantage')({ key: 'EFCR3UVVCE2EYRSI' });
//decide the port
const PORT = process.env.PORT || 8080;
let app = express();
//set up app to the right PORT
app.set('port',PORT);

// Reference from socket io chat app
io.on('connection', function(socket){
  console.log("Client connected");
  //when client emits a stock name, this listens and executes
  socket.on('stock', function(data) {
    //listens every 5 seconds
    setInterval((data) => {
      socket.emit('stockN',{
          Data: retrieveData(stockN)
        }), 5000;
      }); 
  });
});

//function that takes the data
function retrieveData(name){
  alpha.data.batch([`${name}`]).then(data => {
    let value = `${data['Stock Quotes'][0]['2. price']}`;
    return(`${name} -> ${data['Stock Quotes'][0]['2. price']}`);
  //for every then we need to have a catch
  }).catch(err => {
    console.error("Error! -> " + err);
});
};

//listining to the URL
app.listen(PORT, () => {
  console.log(`listening at port ${PORT}`);
});