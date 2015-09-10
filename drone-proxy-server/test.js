var koaApp = require('server')
global.supertest = require('supertest-as-promised')(koaApp)

yield supertest.post('/takeoff/').send()
sleep(5000);
yield supertest.post('/move/up/0.1').send()
sleep(5000);
yield supertest.post('/stop/').send()
sleep(1000);
yield supertest.post('/animate/wave/5000').send()
sleep(1000);
yield supertest.post('/animate/thetaDance/5000').send()
sleep(1000);
