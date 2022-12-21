function followTo() {
    let username = document.getElementById('username_text').innerText
    goTo("persons/follow/" + username)
}

function unfollowFrom() {
    let username = document.getElementById('username_text').innerText
    goTo("persons/unfollow/" + username)
}