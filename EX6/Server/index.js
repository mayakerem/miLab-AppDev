/*
Create a node.js server that will monitor stock quotes from AlphaVantage https://www.alphavantage.co/. 
Create an Android client for it which allows the user to enter a stock name. 
The server will monitor the last price of the stock and send the client price updates every 15 seconds
Start out with a live connection using Socket.IO. 
Once that works, you can implement push with Firebase in case the Android app isn’t open
*/
console.log("V9 - starting server ");
const express = require('express');
const app = express();
// Set up the server
const server = require('http').createServer(app);
const io = require('socket.io')(server);
console.log("defined requires ");
// Used specific key that alpha gave me
const alpha = require('alphavantage')({ key: 'EFCR3UVVCE2EYRSI' });
//decide the port
console.log("set up Alpha");
const PORT = process.env.PORT || 8080;
console.log(`choose port: ${PORT}`);

app.set('port',PORT);
app.use(express.static(__dirname + '/public'));
console.log("before connected after app.set");

// Reference from socket io chat app
io.on('connection', function(socket){
  console.log("Client connected");

  //when client emits a stock name, this listens and executes
  socket.on('sendStockName', function(stockName) {
    //listens every 5 seconds
    console.log("begining interval");
    // if (stockName == undefined) {
    //   console.log("stockName is undefined");
    // }
   // setInterval(() => {
      alpha.data.batch([`${stockName}`]).then(data => {
        console.log("price: " + `${data['Stock Quotes'][0]['2. price']}`);
        socket.emit('sendStockData',{
          symbol: `${stockName}`,
          price: `${data['Stock Quotes'][0]['2. price']}`, 
        });
      }).catch(err => {
        console.error("Error! -> " + err);
     // }), 15000;
      }); 
  });
});

// //function that takes the data
// function retrieveData(name){
//   console.log("inside retrieveData");
//   alpha.data.batch([`${name}`]).then(data => {
//     socket.broadcast.emit('sendStockData', {
//       symbol: `${name}`,
//       price: `${data['Stock Quotes'][0]['2. price']}`, 
//     })
//     //return(`${name} -> ${data['Stock Quotes'][0]['2. price']}`);
//   //for every then we need to have a catch
//   }).catch(err => {
//     console.error("Error! -> " + err);
// });
// };

//listining to the URL
server.listen(PORT, () => {
  console.log(`listening at port ${PORT}`);
});