var url = 'http://127.0.0.1:6969/';
var request = require('request');

request.post(url + 'takeoff/');

setTimeout(function() {
  request.post(url + 'move/up/0.1/');
  setTimeout(function() {
    request.post(url + 'stop/');
    setTimeout(function() {
      request.post(url + 'move/left/0.1/');
      setTimeout(function() {
        request.post(url + 'move/right/0.1/');
        setTimeout(function() {
          request.post(url + 'land');
          }, 7000);
        }, 7000);
      }, 2000);
    }, 5000);
  }, 10000);
