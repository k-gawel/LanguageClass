'use strict'

const EasyGraphqlTester = require('easygraphql-tester')
const fs = require('fs')
const path = require('path')

const userSchema = fs.readFileSync(path.join(__dirname, 'sample-schema.graphqls'), 'utf-8')

const tester = new EasyGraphqlTester(userSchema);
describe("X", () => {
    console.log("XDDD");
    tester.test()
})

module.exports = {testX}