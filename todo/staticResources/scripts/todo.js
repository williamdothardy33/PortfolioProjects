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

const handleUpdate = event => {
    let id = event.target.parentNode.getAttribute('data-todoId');
    let isCompleted = (event.target.parentNode.getAttribute('data-completed') == 'completed')?
                        true:false;
    let update = { completed: !isCompleted };
    fetch(`http://localhost:3000/api/todos/${ id }`, {
        method: 'PUT',
        headers: { 'content-type': 'application/json' },
        body: JSON.stringify(update)
    })
    .then(res => res.json())
    .then(data => {
        if (data.completed) {
            if (!event.target.classList.contains('completed')) {
                event.target.parentNode.setAttribute('data-completed', 'completed');
                event.target.classList.toggle('completed');
            }
        }else {
            if (event.target.classList.contains('completed')) {
                event.target.parentNode.setAttribute('data-completed', '');
                event.target.classList.toggle('completed');
            }
        }
    })
    .catch(error => console.log(error));
};

const handleDelete = event => {
    let id = event.target.parentNode.parentNode.getAttribute('data-todoId');
    console.log(id);
    fetch(`http://localhost:3000/api/todos/${ id }`, { method: 'DELETE' })
    .then(data => {
        event.target.parentNode.parentNode.remove();
    })
    .catch(error => console.log(error));    
};

const renderTodo = data => {
    let todoElement = createTodo(data.name);
    todoElement.setAttribute('data-todoId', data._id);
    if (data.completed) {
        todoElement.classList.add('completed');
        todoElement.setAttribute('data-completed', 'completed');
    }
    let container = document.querySelector('#container');
    container.appendChild(todoElement);
    let todoParagraph = todoElement.querySelector('p');
    todoParagraph.addEventListener('click', handleUpdate);
    let todoCloseButton = todoElement.querySelector('.close_button');
    todoCloseButton.addEventListener('click', handleDelete);
};

const handleInputCreate = event => {
    let todoName = event.target.value;
    event.target.value = "";
    let todoObj = { name: todoName };
    fetch('http://localhost:3000/api/todos', {
        method: 'POST',
        headers: { 'content-type': 'application/json' },
        body: JSON.stringify(todoObj)
    })
    .then(res => res.json())
    .then(data => renderTodo(data))
    .catch(error => console.log(error));
};

const handleLoadCreate = event => {
    fetch('http://localhost:3000/api/todos')
    .then(res => res.json())
    .then(data => {
        data.forEach(todo => {
            renderTodo(todo);
        });
    })
};


let input = document.querySelector('input'); 

input.addEventListener('change', handleInputCreate);

window.addEventListener('load', handleLoadCreate);

