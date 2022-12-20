<#include "../templates/main_template.ftl"/>
<#include "../templates/app_header.ftl"/>
<#include "../templates/posts_template.ftl"/>

<#macro header>
    <@appHeader/>
</#macro>

<#macro content>
    <div id="topic">
        <div id="back_button" onclick="window.history.go(-1)">
            <img id="back_arrow" src="https://stihi.ru/pics/2019/07/14/5314.jpg">
            Назад
        </div>
    </div>
    <#if post.audio??>
        <@posts id=post.id date=post.publicationDate description=post.description media=post.audio comments=post.comments?size/>
    <#elseif post.playlist??>
        <@post id=post.id date=post.publicationDate description=post.description media=post.playlist comments=post.comments?size/>
    </#if>
    <div id="add_comment">
        <input id="add_comment_input" type="text" placeholder="Добавить комментарий"/>
    </div>
    <script>
        document.getElementById('add_comment_input').onkeypress = function (e) {
            if (e.keyCode === 13) {
                addComment(${post.id})
            }
        }
    </script>
    <div id="comments">
        <#list post.comments as comment>
            <div class="comment">
                <div class="top_comment">
                    <div class="image">
                        <#if comment.person.image??>
                            <img src="/persons/${comment.person.username}/avatar">
                        <#else>
                            <img src="https://all-aforizmy.ru/wp-content/uploads/2022/01/6936_43430_1a2d8f8dc6.jpg">
                        </#if>
                    </div>
                    <div class="author">${comment.person.username}</div>
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