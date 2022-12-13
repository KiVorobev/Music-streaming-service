function sendUserReg(login, password) {
    $.ajax({
        url: "http://localhost:8080/registration",
        type: "POST",
        dataType: "json",
        contentType: "application/json;charset=utf-8",
        data: JSON.stringify({
            login: login,
            password: password
        }),
        success: function () {
            console.log('Пользователь успешно добавлен')
        },
        error: function () {
            console.log('Произошла ошибка')
        }
    })
}

function sendUserAuth(login, password) {
    $.ajax({
        url: "http://localhost:8080/authorization",
        type: "POST",
        dataType: "json",
        contentType: "application/json;charset=utf-8",
        data: JSON.stringify({
            login: login,
            password: password
        }),
        success: function () {
            console.log('Пользователь успешно авторизирован')
        },
        error: function () {
            console.log('Произошла ошибка')
        }
    })
}

function userReg() {
    let login = document.getElementById('login').value
    let password = document.getElementById('password').value
    sendUserReg(login, password)
}

function userAuth() {
    let login = document.getElementById('login').value
    let password = document.getElementById('password').value
    sendUserAuth(login, password)
}