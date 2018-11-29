var http = require('http');
var server = http.createServer();

server.on('request', function(request, response) {
    response.writeHead(200);
  //   console.log(request.method);
  //   console.log(request.headers);
  //   console.log(request.url);
    console.log(request.headers.host+request.url);
    response.end(request.headers.host+request.url);
});

server.listen(8080);





