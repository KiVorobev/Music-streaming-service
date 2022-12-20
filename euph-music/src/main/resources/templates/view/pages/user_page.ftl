<#include "../templates/main_template.ftl"/>
<#include "../templates/app_header.ftl"/>
<#include "../templates/posts_template.ftl"/>

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
        <#if loggedPerson.username == person.username>
            <div class="block" id="add_post" onclick="goTo('posts/create')" onmouseover="hover_highlight('add_post_text')"
                 onmouseout="hover_unhighlight('add_post_text')">
                <span id="add_post_text">Добавить запись</span>
            </div>
        </#if>
        <div id="posts">
            <#if person.posts?has_content>
                <#list person.posts as note>
                    <#if note.audio.name??>
                        <@posts id=note.id date=note.publicationDate description=note.description media=note.audio comments=note.comments?size/>
                    <#elseif note.playlist.name??>
                        <@posts id=note.id date=note.publicationDate description=note.description media=note.playlist comments=note.comments?size/>
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
            <#if person.image??>
                <img src="/persons/${person.username}/avatar">
            <#else>
                <img src="https://all-aforizmy.ru/wp-content/uploads/2022/01/6936_43430_1a2d8f8dc6.jpg">
            </#if>
        </div>
        <#if loggedPerson.username == person.username>
            <div class="block" id="edit" onmouseover="hover_highlight('active_block')"
                 onmouseout="hover_unhighlight('active_block')" onclick="goTo('persons/${person.username}/edit')">
                <span id="active_block">Редактировать</span>
            </div>
        <#elseif !isFollowed>
            <div class="block" id="follow" onclick="followTo()" onmouseover="hover_highlight('active_block')"
                 onmouseout="hover_unhighlight('active_block')">
                <span id="active_block">Подписаться</span>
            </div>
        <#elseif isFollowed>
            <div class="block" id="unfollow" onclick="unfollowFrom()" onmouseover="hover_highlight('active_block')"
                 onmouseout="hover_unhighlight('active_block')">
                <span id="active_block">Отписаться</span>
            </div>
        </#if>
        <div class="block" id="user_info">
            <div class="user_info_hover" id="first_user_info" onclick="goTo('persons/${person.username}/follows')"
                 onmouseover="hover_highlight('info_followTo')"
                 onmouseout="hover_unhighlight('info_followTo')">
                <span id="info_followTo">Подписки: ${person.followTo?size}</span>
            </div>
            <div class="user_info_hover" onclick="goTo('persons/${person.username}/follows')"
                 onmouseover="hover_highlight('info_followers')"
                 onmouseout="hover_unhighlight('info_followers')">
                <span id="info_followers">Подписчики: ${person.followers?size}</span>
            </div>
            <div class="user_info_hover" onclick="goTo('persons/${person.username}/loaded')"
                 onmouseover="hover_highlight('info_loaded')"
                 onmouseout="hover_unhighlight('info_loaded')">
                <span id="info_loaded">Авторство: ${person.loadedAudios?size}</span>
            </div>
            <div class="user_info_hover" id="last_user_info" onclick="goTo('persons/${person.username}/saved')"
                 onmouseover="hover_highlight('info_saved')"
                 onmouseout="hover_unhighlight('info_saved')">
                <span id="info_saved">Сохраненные аудио: ${person.savedAudios?size}</span>
            </div>
        </div>
    </div>
    <script>
        function hover_highlight(id) {
            document.getElementById(id).classList.add('hover_elements')
        }

        function hover_unhighlight(id) {
            document.getElementById(id).classList.remove('hover_elements')
        }
    </script>
</#macro>

<@main cssPage="user_page"/>