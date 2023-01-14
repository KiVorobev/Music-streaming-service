<#macro entryHeader page>
    <#if page="login">
        <button id="swap_page" onclick="goTo('registration')">Регистрация</button>
    <#elseif page="registration">
        <button id="swap_page" onclick="goTo('login')">Войти</button>
    </#if>
</#macro>