const mongoose = require('mongoose');
const { Schema } = mongoose;

const todoSchema = new Schema({
    name: { type: String, required: true },
    completed: { type: Boolean, default: false },
    createdDate: { type: Date, default: Date.now }
});

const todoModel = mongoose.model('todoModel', todoSchema);

module.exports = { todoModel };