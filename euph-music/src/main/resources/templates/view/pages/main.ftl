<#include "../templates/main_template.ftl"/>
<#include "../templates/app_header.ftl"/>

<#macro header>
    <@appHeader username="username" balance="balance"/>
</#macro>

<#macro content>
    Common salam aleikum!
</#macro>

<@main cssPage="main"/>