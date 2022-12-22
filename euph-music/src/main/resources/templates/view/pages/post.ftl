<#include "../templates/main_template.ftl"/>
<#include "../templates/app_header.ftl"/>
<#include "../templates/posts_template.ftl"/>

<#macro header>
    <@appHeader/>
</#macro>

<#macro content>
    <div id="topic">
        <div id="back_button" onclick="window.history.go(-1)">
            <img id="back_arrow" src="/images/arrow.png">
            Назад
        </div>
    </div>
    <#if post.audio??>
        <@posts id=post.id date=post.publicationDate description=post.description media=post.audio comments=post.comments?size type='audios'/>
    <#elseif post.playlist??>
        <@posts id=post.id date=post.publicationDate description=post.description media=post.playlist comments=post.comments?size type='playlists'/>
    </#if>
    <div id="add_comment">
        <form id="add_comment_form" method="post" action="comments/add">
            <input name="text" id="add_comment_input" type="text" placeholder="Добавить комментарий"/>
        </form>
    </div>
    <script>
        document.getElementById('add_comment_input').onkeypress = function (e) {
            if (e.keyCode === 13) {
                document.getElementById('add_comment_form').submit()
            }
        }
    </script>
    <div id="comments">
        <#list post.comments as comment>
            <div class="comment">
                <div class="top_comment">
                    <div class="image">
                        <#if comment.person.image??>
                            <img onclick="goTo('persons/${comment.person.username}')"
                                 src="/persons/${comment.person.username}/avatar">
                        <#else>
                            <img onclick="goTo('persons/${comment.person.username}')"
                                 src="/persons/avatar">
                        </#if>
                    </div>
                    <div class="author hover_span"
                         onclick="goTo('persons/${comment.person.username}')">${comment.person.username}</div>
                </div>
                <div class="middle_comment">
                    <div class="comment_text">${comment.text}</div>
                </div>
                <div class="bottom_comment">
                    <div class="date">${comment.publicationDate}</div>
                </div>
            </div>
        </#list>
    </div>
</#macro>

<@main cssPage="post"/>