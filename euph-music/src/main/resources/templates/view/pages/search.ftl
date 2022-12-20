<#include "../templates/main_template.ftl"/>
<#include "../templates/app_header.ftl"/>

<#macro header>
    <@appHeader/>
</#macro>

<#macro content>
    <#list persons as person>
        ${person.username}
    </#list>
    <#list audios as audio>
        ${audio.id?c}
        ${audio.name}
        <#list audio.authors as author>
            ${author.username}<#if author_has_next>,</#if>
        </#list>
    </#list>
    <#list playlists as playlist>
        ${playlist.name}
    </#list>
</#macro>

<@main cssPage="search"/>