<#include "../templates/main_template.ftl"/>
<#include "../templates/app_header.ftl"/>

<#macro header>
    <@appHeader username="username" balance="balance"/>
</#macro>

<#macro content>
    ${playlist.name}
    ${playlist.description}
    ${playlist.date}
<#--    <#list playlist.audios as audio>-->
<#--        ${audio.name}-->
<#--    </#list>-->
</#macro>

<@main cssPage="playlist"/>