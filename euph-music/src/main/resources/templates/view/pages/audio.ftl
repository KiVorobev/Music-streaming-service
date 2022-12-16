<#include "../templates/main_template.ftl"/>
<#include "../templates/app_header.ftl"/>

<#macro header>
    <@appHeader/>
</#macro>

<#macro content>
    ${audio.name}
    ${audio.text}
    ${audio.uploadDate}
    <#list audio.authors as author>
        ${author.username}
    </#list>
</#macro>

<@main cssPage="audio"/>