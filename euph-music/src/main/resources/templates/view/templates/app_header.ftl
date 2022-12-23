<#macro appHeader>
    <div id="left">
        <div id="logo">
            <img onclick="goTo('')" src="/images/logo.png">
        </div>
        <div id="search_block">
            <form id="search_form" method="get" action="/search">
                <input name="text" id="search_input" type="text" placeholder="Поиск" required>
            </form>
        </div>
    </div>
    <div id="right">
        <div id="avatar">
            <#if loggedPerson.image??>
                <img src="/persons/${loggedPerson.username}/avatar">
            <#else>
                <img src="/persons/avatar">
            </#if>
        </div>
        <div id="username">
            ${loggedPerson.username}
        </div>
        <ul>
            <li><span id="to_profile" onclick="goTo('persons/${loggedPerson.username}')">Перейти в профиль</span>
            </li>
            <li><span id="logout" onclick="goTo('logout')">Выйти</span></li>
        </ul>
    </div>
</#macro>