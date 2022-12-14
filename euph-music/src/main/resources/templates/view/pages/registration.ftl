<#include "../templates/main_template.ftl"/>
<#include "../templates/entry_template.ftl"/>
<#include "../templates/entry_header.ftl"/>

<#macro header>
    <@entryHeader page = "registration"/>
</#macro>

<#macro content>
    <@entry page = "registration"/>
</#macro>


<@main cssPage="entry"/>