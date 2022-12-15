<#macro appHeader username balance>
    <div id="right">
        <div id="username" onclick="goTo('person/${username}')">
            <text>${username}</text>
        </div>
        <div id="balance" onclick="goTo('balance')">
            <img src="https://e7.pngegg.com/pngimages/10/205/png-clipart-coin-money-bag-computer-icons-coin-text-trademark.png"/>
            <text>${balance}</text>
        </div>
    </div>
</#macro>