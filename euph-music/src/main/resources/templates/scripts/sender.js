function sendUserReg(username, email, password) {
    console.log('privet!')
    $.ajax({
        url: "http://localhost:8080/registration",
        type: "POST",
        dataType: "json",
        contentType: "application/json;charset=utf-8",
        data: JSON.stringify({
            username: username,
            email: email,
            password: password
        }),
        success: function () {
            console.log('Пользователь успешно добавлен')
            goTo("/")
        },
        error: function () {
            console.log('Произошла ошибка')
        }
    })
}

function sendUserAuth(username, password) {
    var fd = new FormData();
    fd.append("username", username);
    fd.append("password", password);

    $.ajax({
        url: "http://localhost:8080/login",
        type: "POST",
        data: fd,
        processData: false,
        contentType: false,
        success: function () {
            console.log('Пользователь успешно авторизирован')
        },
        error: function () {
            console.log('Произошла ошибка')
        }
    })
}

function userReg() {
    let username = document.getElementById('username').value
    let email = document.getElementById('email').value
    let password = document.getElementById('password').value
    sendUserReg(username, email, password)
}

function userAuth() {
    let username = document.getElementById('username').value
    let password = document.getElementById('password').value
    sendUserAuth(username, password)
}