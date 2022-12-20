function sendUserReg(username, email, password, image) {
    let fd = new FormData();
    fd.append("username", username);
    fd.append("email", email);
    fd.append("password", password);
    fd.append("image", image[0]);
    $.ajax({
        url: "http://localhost:8080/registration",
        type: "POST",
        data: fd,
        processData: false,
        contentType: false,
        success: function () {
            console.log('Пользователь успешно добавлен')
        },
        error: function () {
            console.log('Произошла ошибка')
        }
    })
}

function sendUserAuth(username, password) {
    let fd = new FormData();
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

function sendUpdateUser(status, description, image) {
    let fd = new FormData();
    fd.append("status", status);
    fd.append("description", description);
    if (image.length !== 0) {
        console.log('lenght is not 0!')
        fd.append("image", image[0]);
    }
    $.ajax({
        url: "http://localhost:8080/persons/update",
        type: "POST",
        data: fd,
        processData: false,
        contentType: false,
        success: function () {
            console.log('Пользователь успешно изменен')
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
    let image = $('#image')[0].files
    sendUserReg(username, email, password, image)
}

function userAuth() {
    let username = document.getElementById('username').value
    let password = document.getElementById('password').value
    sendUserAuth(username, password)
}

function followTo() {
    let username = document.getElementById('username_text').innerText
    goTo("persons/follow/" + username)
}

function unfollowFrom() {
    let username = document.getElementById('username_text').innerText
    goTo("persons/unfollow/" + username)
}

function updateUser() {
    let status = document.getElementById('input_status').value
    let description = document.getElementById('input_description').value
    let image = $('#image_edit')[0].files
    sendUpdateUser(status, description, image)
}