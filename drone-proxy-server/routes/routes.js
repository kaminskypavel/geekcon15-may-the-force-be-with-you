var router = require('koa-router')()

var droneController = require('../controllers/drone_controller')

module.exports = function routes() {
  router.post('/takeoff/', droneController.takeoff)
  router.post('/land/', droneController.land)
  return router.routes()
}
