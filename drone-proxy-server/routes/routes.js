var router = require('koa-router')()

var droneController = require('../controllers/drone_controller')

module.exports = function routes() {
  router.post('/takeoff/', droneController.takeOff)
  router.post('/land/', droneController.land)
  router.post('/stop/', droneController.stop)
  router.post('/move/:direction/:speed', droneController.move)
  router.post('/animate/:animation/:duration', droneController.stop)
  return router.routes()
}
