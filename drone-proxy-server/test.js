var koaApp = require('server')
global.supertest = require('supertest-as-promised')(koaApp)
