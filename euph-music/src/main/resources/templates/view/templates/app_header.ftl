<#macro appHeader>
    <div id="left">
        <div id="logo">
            <img onclick="goTo('')"
                 src="https://adonius.club/uploads/posts/2022-02/1644938357_35-adonius-club-p-lotos-na-belom-fone-56.png"/>
        </div>
    </div>
    <div id="search_block">
        <input id="search_input" type="text" placeholder="Поиск" onsubmit="goTo('search')"/>
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
        <div id="balance" onclick="goTo('balance')">
            <img src="https://e7.pngegg.com/pngimages/10/205/png-clipart-coin-money-bag-computer-icons-coin-text-trademark.png"/>
            <text>${loggedPerson.balance}</text>
        </div>
    </div>
</#macro>