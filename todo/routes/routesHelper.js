const db = require('../database');

const getTodos = (req, res) => {
    db.todoModel.find()
    .then(data => {
        res.json(data);
    }).catch(error => {
        res.send(error);
    })
};

const createTodo = (req, res) => {
    let todoToAdd = req.body;
    let todoDoc = new db.todoModel(todoToAdd);
    todoDoc.save()
    .then(data => res.status(201).json(data))
    .catch(error => res.send(error));
};

const getTodo = (req, res) => {
    let id = req.params.id;
    let searchQuery = { _id: id };
    db.todoModel.findOne(searchQuery)
    .then(data => res.json(data))
    .catch(error => res.send(error));    
};

const updateTodo = (req, res) => {
    let id = req.params.id;
    let searchQuery = { _id: id };
    let update = req.body;
    let options = { new: true, useFindAndModify: false };

    db.todoModel.findByIdAndUpdate(searchQuery, update, options)
    .then(data => res.send(data))
    .catch(error => res.send(error));
};

const deleteTodo = (req, res) => {
    let id = req.params.id;
    let searchQuery = { _id: id };
    db.todoModel.findOneAndDelete(searchQuery)
    .then(data => res.status(204).send(data))
    .catch(error => res.send(error));
};

module.exports = { getTodos, getTodo, createTodo, updateTodo, deleteTodo };