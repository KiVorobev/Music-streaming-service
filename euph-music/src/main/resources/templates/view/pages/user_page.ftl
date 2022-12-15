<#include "../templates/main_template.ftl"/>
<#include "../templates/app_header.ftl"/>

<#macro header>
    <@appHeader username="${person.username}" balance="${person.balance}"/>
</#macro>

<#macro content>
    <div id="left">
        <div class="block" id="user_mood">
            <div id="status">
                <#if person.status??>
                    ${person.status}
                </#if>
            </div>
            <div id="description">
                <#if person.description??>
                    ${person.description}
                </#if>
            </div>
        </div>
        <div class="block" id="posts">
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
    <div id="right">
        <div class="block" id="avatar">
            <img src="https://flyclipart.com/thumbs/user-icon-business-man-flat-1030903.png">
        </div>
        <div class="block" id="user_info">
            <div id="follows" onclick="goTo('persons/${person.username}/follows')">
               <p>Подписки: ${person.followTo?size} Подписчики: ${person.followers?size}</p>
            </div>
            <#--        Достижения-->
            <div id="loaded" onclick="goTo('persons/${person.username}/loaded')">
                <p>Авторство</p>
            </div>
            <div id="saved" onclick="goTo('persons/${person.username}/saved')" )>
                <p>Сохраненные аудио</p>
            </div>
        </div>
    </div>
</#macro>

<@main cssPage="user_page"/>