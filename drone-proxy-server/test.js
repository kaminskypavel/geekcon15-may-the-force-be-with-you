var url = 'http://127.0.0.1:6969/';
var request = require('request');

function sleep(milliseconds) {
  var start = new Date().getTime();
  while (new Date().getTime() < start + milliseconds);
}

request.post(url + 'takeoff/');
sleep(5000);
request.post(url + 'move/up/0.2/');
sleep(3000);
request.post(url + 'stop/');
sleep(1000);
request.post(url + 'animate/wave/5000/');
sleep(1000);
request.post(url + 'animate/thetaDance/5000/');
