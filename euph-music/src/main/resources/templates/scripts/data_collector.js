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