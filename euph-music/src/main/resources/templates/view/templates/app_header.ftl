<#macro appHeader username balance>
    <div id="right">
        <div id="username" onclick="goTo('person/${username}')">
            <text>${username}</text>
        </div>
        <div id="balance" onclick="goTo('balance')">
            <img src="https://e7.pngegg.com/pngimages/478/692/png-clipart-flow-realty-inc-finance-initial-coin-offering-cryptocurrency-technology-dtp-innovation-business.png"/>
            <text>${balance}</text>
        </div>
    </div>
</#macro>