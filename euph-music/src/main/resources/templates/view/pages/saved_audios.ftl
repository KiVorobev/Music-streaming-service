<#include "../templates/main_template.ftl"/>
<#include "../templates/app_header.ftl"/>
<#include "../templates/audios_template.ftl"/>

<#macro header>
    <@appHeader/>
</#macro>

<#macro content>
    <@audios page="saved"/>
</#macro>

<@main cssPage="audios"/>