# todoMakerBackend
----------
A small spring boot app to store/delete/modify todo list

**List of APIs**

1. **GET** http://localhost:9000/todoApp/tasks
2. **POST** http://localhost:9000/todoApp/tasks
3. **PUT** http://localhost:9000/todoApp/tasks/{id}
4. **DELETE** http://localhost:9000/todoApp/tasks/{id}

Sample request body for `POST` and `PUT` requests

```javascript
    // request body for POST and PUT http request
    {
        'todo': 'your todo item',
        'status': "PENDING or COMPLETED"
    }
```

----------

*Please note that I have not followed TDD and also not provided any unit and integration test cases because of time constrains*

----------

**APP SCREENSHOT BELOW**

!["TODO APP SCREENSHOT"](https://github.com/ecpro/todoMakerBackend/blob/master/screenshot.png)