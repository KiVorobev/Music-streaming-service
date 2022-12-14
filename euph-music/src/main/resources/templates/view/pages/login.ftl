<#include "../templates/main_template.ftl"/>
<#include "../templates/entry_template.ftl"/>
<#include "../templates/entry_header.ftl"/>

<#macro header>
    <@entryHeader page = "login"/>
</#macro>

<#macro content>
    <@entry page = "login"/>
</#macro>


<@main cssPage="entry"/>