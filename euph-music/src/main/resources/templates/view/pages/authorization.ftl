<#include "../templates/main_template.ftl"/>
<#include "../templates/entry_template.ftl"/>
<#include "../templates/entry_header.ftl"/>

<#macro header>
    <@entryHeader page = "authorization"/>
</#macro>

<#macro content>
    <@entry page = "authorization"/>
</#macro>


<@main cssPage="entry"/>