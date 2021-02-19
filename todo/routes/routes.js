const { getTodo, getTodos, createTodo, updateTodo, deleteTodo } = require('./routesHelper');
const { Router } = require('express');
const router = Router();

router.route('/')
.get(getTodos)
.post(createTodo);

router.route('/:id')
.get(getTodo)
.put(updateTodo)
.delete(deleteTodo)

module.exports = { router };