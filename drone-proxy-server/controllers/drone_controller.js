
exports.takeOff = function* takeOff(next) {
  console.log('takeoff')
  droneClient.takeoff();
}

exports.land = function* land(next) {
  console.log('land')
  droneClient.land();
}
