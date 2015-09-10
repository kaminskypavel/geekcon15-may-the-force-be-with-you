var app = require('koa')()
require('./config/conf')

var arDrone = require('ar-drone');
global.droneClient  = arDrone.createClient();

// var handleErrors = require('./lib/handle_errors')
var handlerRoutes = require('./routes/routes')

// app.use(handleErrors)
app.use(require('koa-body')());
// app.use(require('koa-validate')());
app.use(handlerRoutes())

console.log('listening on port ' + conf.port)
module.exports = app.listen(conf.port)
