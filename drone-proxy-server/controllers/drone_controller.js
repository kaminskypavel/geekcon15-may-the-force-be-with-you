
exports.takeOff = function* takeOff(next) {
  console.log('takeoff')
  droneClient.takeoff();
}

exports.land = function* land(next) {
  console.log('land')
  droneClient.land();
}

exports.move = function* move(next) {
  var direction = this.query.direction;
  var speed = this.query.speed;
  console.log('move ' + direction + ' at speed ' + speed)

  switch(direction){
    case 'up':
      droneClient.up(speed)
      break;
    case 'down':
      droneClient.down(speed)
      break;
    case 'front':
      droneClient.front(speed)
      break;
    case 'back':
      droneClient.back(speed)
      break;
    case 'left':
      droneClient.left(speed)
      break;
    case 'right':
      droneClient.right(speed)
      break;
  }
}
