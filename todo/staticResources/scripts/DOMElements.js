const createTodo = newTodoName => {
    let closeIcon = document.createElement('i');
    closeIcon.classList.add('fa', 'fa-window-close');

    let todoSpan = document.createElement('span');
    todoSpan.classList.add('close_button');

    todoSpan.appendChild(closeIcon);

    let text = document.createTextNode(newTodoName)
    let todoParagraph = document.createElement('p');

    todoParagraph.appendChild(text);

    let todoDiv = document.createElement('div');
    todoDiv.classList.add('todo_item')

    todoDiv.appendChild(todoParagraph);
    todoDiv.appendChild(todoSpan);

    return todoDiv;
};

module.exports = { createTodo };