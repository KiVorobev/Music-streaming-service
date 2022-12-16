<#macro appHeader>
    <div id="right">
        <div id="username">
            <text onclick="goTo('persons/${loggedPerson.username}')">${loggedPerson.username}</text>
        </div>
        <div id="balance" onclick="goTo('balance')">
            <img src="https://e7.pngegg.com/pngimages/10/205/png-clipart-coin-money-bag-computer-icons-coin-text-trademark.png"/>
            <text>${loggedPerson.balance}</text>
        </div>
    </div>
</#macro>