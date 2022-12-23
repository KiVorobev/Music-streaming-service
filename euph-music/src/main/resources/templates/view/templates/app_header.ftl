<#macro appHeader>
    <div id="left">
        <div id="logo">
            <img onclick="goTo('')"
                 src="https://adonius.club/uploads/posts/2022-02/1644938357_35-adonius-club-p-lotos-na-belom-fone-56.png"/>
        </div>
    </div>
    <div id="search_block">
        <form id="search_form" method="get" action="/search">
            <input name="text" id="search_input" type="text" placeholder="Поиск">
        </form>
    </div>
    <div id="right">
        <div id="username">
            ${loggedPerson.username}
            <ul>
                <li><span id="to_profile" onclick="goTo('persons/${loggedPerson.username}')">Перейти в профиль</span>
                </li>
                <li><span id="logout" onclick="goTo('logout')">Выйти</span></li>
            </ul>
        </div>
    </div>
</#macro>