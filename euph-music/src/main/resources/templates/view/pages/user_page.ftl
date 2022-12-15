<#include "../templates/main_template.ftl"/>
<#include "../templates/app_header.ftl"/>

<#macro header>
    <@appHeader username="${person.username}" balance="${person.balance}"/>
</#macro>

<#macro content>
    <div id="container">
        <div class="up left" id="up-left">
            <#if person.status??>
                <span id="status">${person.status}</span>
            </#if>
            <#if person.description??>
                <span id="description">${person.description}</span>
            </#if>
            followers: ${person.followers?size}
            follow to: ${person.followTo?size}
        </div>
        <div class="up right" id="up-right">
            <img src="https://flyclipart.com/thumbs/user-icon-business-man-flat-1030903.png">
        </div>
        <div class="down right" id="down-right">
            <#--        Достижения-->
            loaded:
            <#list person.loadedAudios as audio>
                ${audio.name}
                ${audio.uploadDate}
            </#list>
            saved:
            <#list person.savedAudios as audio>
                ${audio.name}
                ${audio.uploadDate}
            </#list>
        </div>
        <div class="down left" id="down-left">
            <#list person.posts as post>
                ${post.description}
                ${post.publicationDate}
                <#if post.audio.name??>
                    ${post.audio.name}
                    <#elseif post.playlist.name??>
                    ${post.playlist.name}
                </#if>
                ${post.comments?size}
            </#list>
        </div>
    </div>
</#macro>

<@main cssPage="user_page"/>