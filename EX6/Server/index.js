/*
Create a node.js server that will monitor stock quotes from AlphaVantage https://www.alphavantage.co/. 
Create an Android client for it which allows the user to enter a stock name. 
The server will monitor the last price of the stock and send the client price updates every 15 seconds
Start out with a live connection using Socket.IO. 
Once that works, you can implement push with Firebase in case the Android app isn’t open
*/
console.log("Initiating Server");
const express = require('express');
const app = express();
// Set up the server
const server = require('http').createServer(app);
const io = require('socket.io')(server);
console.log("Sucessfully Defined Requires");
// Used specific key provided by that alpha vantage
const alpha = require('alphavantage')({ key: 'EFCR3UVVCE2EYRSI' });
// Choosing port
console.log("Alpha was set up");
const PORT = process.env.PORT || 8080;
console.log(`Choosen port was ${PORT}`);

app.set('port',PORT);
app.use(express.static(__dirname + '/public'));
console.log("Completed app set up");
let i = 0;
// Begin setting up socket with event 'connection
io.on('connection', function(socket){
  console.log("Client has connected");

  //When client emits a stock name, server listens to 'sendStockName'
  socket.on('sendStockName', function(stockName) {
    //Set up interval
    setInterval(() => {
      console.log("Begining interval");
      //Get relevant data from alpha using Batch Stocks
      alpha.data.batch([`${stockName}`]).then(data => {
        console.log("Time: " + `${data['Stock Quotes'][0]['4. timestamp']}`);
        console.log("Price: " + `${data['Stock Quotes'][0]['2. price']}`);
        //Sending JSON with relevant information to the client
        //Client will listen to 'sendStockData'
        socket.emit('sendStockData',{
          symbol: `${stockName}`,
          price: `${data['Stock Quotes'][0]['2. price']}`,
          time: `${data['Stock Quotes'][0]['4. timestamp']}`
        });
      }).catch(err => {
        //The provided name is invalid or there has been some error
        console.error("Encountered Error: " + err);
        //Emmiting error to event 'sendStockData'
        socket.emit('sendStockData',{
          symbol: `${stockName}`,
          price: "Invalid Name",
          time: "N/A"
        });

      //Send the JSON every X miliseconds
      })}, 5000); 
  });
});

//Listining to the URL
server.listen(PORT, () => {
  console.log(`Listening at port ${PORT}`);
});