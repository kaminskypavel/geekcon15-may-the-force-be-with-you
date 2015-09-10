
exports.takeOff = function* takeOff(next) {
  console.log('takeoff')
  droneClient.takeoff();
  this.status = 200;
}

exports.land = function* land(next) {
  console.log('land')
  droneClient.land();
  this.status = 200;
}

exports.stop = function* stop(next) {
  console.log('stop')
  droneClient.stop();
  this.status = 200;
}

exports.animate = function* animate(next) {
  console.log('animate ' +this.params.animation )
  droneClient.animate(this.params.animation, this.params.duration);
  this.status = 200;
}

exports.move = function* move(next) {
  var direction = this.params.direction;
  var speed = this.params.speed;
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

  this.status = 200;
}
