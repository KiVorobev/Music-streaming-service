<#macro entryHeader page>
    <#if page="authorization">
        <button id="swap_page" onclick="goTo('registration')">Регистрация</button>
    <#elseif page="registration">
        <button id="swap_page" onclick="goTo('authorization')">Авторизация</button>
    </#if>
</#macro>