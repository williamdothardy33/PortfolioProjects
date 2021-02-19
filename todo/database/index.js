const { todoModel } = require('./todoModel');
const mongoose = require('mongoose');
mongoose.set('debug', true);
mongoose.Promise = Promise; //so can use promises instead of callbacks

const path = require('path');
require('dotenv').config({ path: path.resolve(__dirname, '../.env') });

mongoose.connect(process.env.MONGO_URI, { useNewUrlParser: true, useUnifiedTopology: true });

const db = mongoose.connection;
db.on('error', console.error.bind(console, 'connection error:'));
db.once('open', function() {
  console.log("we're connected!");
});

module.exports = { todoModel };