<#include "../templates/main_template.ftl"/>
<#include "../templates/app_header.ftl"/>

<#macro header>
    <@appHeader/>
</#macro>

<#macro content>
    <div id="left">
        <div id="username_text">
            ${person.username}
        </div>
        <#if person.status?? || person.description??>
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
        </#if>
        <div class="block" id="posts">
            <#if person.posts?has_content>
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
            <#else>
                Пользователь пока не добавил ни одну запись
                <br>
                <img id="null_post_img" src="https://i.centerdiseasecondtrol.com/images/256467/image.png">
            </#if>
        </div>
    </div>
    <div id="right">
    <div class="block" id="avatar">
        <img src="https://all-aforizmy.ru/wp-content/uploads/2022/01/6936_43430_1a2d8f8dc6.jpg">
    </div>
    <#if loggedPerson.username == person.username>
        <div class="block" id="edit_follow" onmouseover="hover_highlight()" onmouseout="hover_unhighlight()">
        <span id="active_block">Редактировать</span>
    <#elseif !isFollowed>
        <div class="block" id="edit_follow" onmouseover="hover_highlight()" onmouseout="hover_unhighlight()">
        <span id="active_block">Подписаться</span>
    <#elseif isFollowed>
        <div style="background: rgba(62,45,113,0.82)" class="block" id="edit_follow" onmouseover="hover_highlight()" onmouseout="hover_unhighlight()">
        <span id="active_block">Отписаться</span>
    </#if>
    </div>
    <script>
        function hover_highlight() {
            document.getElementById('active_block').setAttribute('style', 'color: #72065f')
        }

        function hover_unhighlight() {
            document.getElementById('active_block').removeAttribute('style', 'color')
        }
    </script>
    <div class="block" id="user_info">
        <span onclick="goTo('persons/${person.username}/follows')">Подписки: ${person.followTo?size}</span>
        <br>
        <span onclick="goTo('persons/${person.username}/follows')">Подписчики: ${person.followers?size}</span>
        <br>
        <span onclick="goTo('persons/${person.username}/loaded')">Авторство</span>
        <br>
        <span onclick="goTo('persons/${person.username}/saved')">Сохраненные аудио</span>
    </div>
    </div>
</#macro>

<@main cssPage="user_page"/>