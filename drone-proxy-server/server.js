var app = require('koa')()

var arDrone = require('ar-drone');
global.droneClient  = arDrone.createClient();

// global.Promise = require('bluebird');

// var handleErrors = require('./lib/handle_errors')
var handlerRoutes = require('./routes/routes')

// app.use(handleErrors)
app.use(require('koa-body')());
app.use(require('koa-validate')());
app.use(handlerRoutes())

log.info('listening on port ' + conf.port)
module.exports = app.listen(conf.port)
