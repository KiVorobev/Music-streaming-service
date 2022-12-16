<#include "../templates/main_template.ftl"/>
<#include "../templates/app_header.ftl"/>
<#include "../templates/post.ftl"/>

<#macro header>
    <@appHeader/>
</#macro>

<#macro content>
    <div id="username_text">
        ${person.username}
    </div>
    <div id="left">
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
        <div class="block" id="add_post" onmouseover="hover_highlight('add_post_text')" onmouseout="hover_unhighlight('add_post_text')">
            <span id="add_post_text">Добавить запись</span>
        </div>
        <div id="posts">
            <#if person.posts?has_content>
                <#list person.posts as note>
                    <#if note.audio.name??>
                        <@post date=note.publicationDate description=note.description media=note.audio comments=note.comments?size/>
                    <#elseif note.playlist.name??>
                        <@post date=note.publicationDate description=note.description media=note.playlist comments=note.comments?size/>
                    </#if>
                </#list>
            <#else>
                <div class="block" id="null_posts">
                    Пользователь пока не добавил ни одну запись
                    <br>
                    <img id="null_post_img" src="https://i.centerdiseasecondtrol.com/images/256467/image.png">
                </div>
            </#if>
        </div>
    </div>
    <div id="right">
    <div class="block" id="avatar">
        <img src="https://all-aforizmy.ru/wp-content/uploads/2022/01/6936_43430_1a2d8f8dc6.jpg">
    </div>
    <#if loggedPerson.username == person.username>
        <div class="block" id="edit" onmouseover="hover_highlight('active_block')" onmouseout="hover_unhighlight('active_block')">
        <span id="active_block">Редактировать</span>
    <#elseif !isFollowed>
        <div class="block" id="follow" onclick="followTo()" onmouseover="hover_highlight('active_block')" onmouseout="hover_unhighlight('active_block')">
        <span id="active_block">Подписаться</span>
    <#elseif isFollowed>
        <div class="block" id="unfollow" onclick="unfollowFrom()" onmouseover="hover_highlight('active_block')" onmouseout="hover_unhighlight('active_block')">
        <span id="active_block">Отписаться</span>
    </#if>
    </div>
    <script>
        function hover_highlight(id) {
            document.getElementById(id).setAttribute('style', 'color: #16ece6')
        }

        function hover_unhighlight(id) {
            document.getElementById(id).removeAttribute('style', 'color')
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