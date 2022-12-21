<#macro appHeader>
    <div id="left">
        <div id="logo">
            <img onclick="goTo('')"
                 src="https://adonius.club/uploads/posts/2022-02/1644938357_35-adonius-club-p-lotos-na-belom-fone-56.png"/>
        </div>
    </div>
    <div id="search_block">
        <form id="search_form" method="get" action="/search">
            <input id="search_input" type="text" placeholder="Поиск"/>
        </form>
    </div>
    <script>
        document.getElementById('search_input').onkeypress = function (e) {
            if (e.keyCode === 13) {
                document.getElementById('search_form').submit()
            }
        }
        if (window.location.href === 'http://localhost:8080/search') {
            document.getElementById('search_block').style.display = 'none'
        }
    </script>
    <div id="right">
        <div id="username">
            ${loggedPerson.username}
            <ul>
                <li><span id="to_profile" onclick="goTo('persons/${loggedPerson.username}')">Перейти в профиль</span>
                </li>
                <li><span id="logout" onclick="goTo('logout')">Выйти</span></li>
            </ul>
        </div>
        <div id="balance" onclick="goTo('balance')">
            <img src="https://e7.pngegg.com/pngimages/10/205/png-clipart-coin-money-bag-computer-icons-coin-text-trademark.png"/>
            <text>${loggedPerson.balance}</text>
        </div>
    </div>
</#macro>