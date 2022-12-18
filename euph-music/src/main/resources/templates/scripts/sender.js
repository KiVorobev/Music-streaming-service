function sendUserReg(username, email, password, image) {
    console.log('privet!')
    var fd = new FormData();
    fd.append("username", username);
    fd.append("email", email);
    fd.append("password", password);
    fd.append("image", image[0]);
    console.log(image[0])
    $.ajax({
        url: "http://localhost:8080/registration",
        type: "POST",
        // contentType: "multipart/form-data",
        data: fd,
        processData: false,
        contentType:false,
        success: function () {
            console.log('Пользователь успешно добавлен')
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
    let image = $('#image')[0].files;
    sendUserReg(username, email, password, image)
}

function userAuth() {
    let username = document.getElementById('username').value
    let password = document.getElementById('password').value
    sendUserAuth(username, password)
}

function followTo() {
    let username = document.getElementById("username_text").innerText
    goTo("persons/follow/" + username)
}

function unfollowFrom() {
    let username = document.getElementById("username_text").innerText
    goTo("persons/unfollow/" + username)
}