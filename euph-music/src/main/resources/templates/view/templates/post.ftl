<#macro post id date description media comments>
    <div class="block post_view">
        <div class="post">
            <div class="top_post">
                <div class="left_top_post">
                    <div class="post_description">
                        <span class="post_description">${description}</span>
                    </div>
                    <div class="content_info">
                        <span class="post_content">Название: <span class="hover_span" onclick="goTo('audios/${media.id}')">${media.name}</span></span>
                        <br>
                        <br>
                        <span class="post_content">Авторы:
                            <#list media.authors as author>
                                <span class="hover_span" onclick="goTo('persons/${author.username}')">${author.username}</span><#if author_has_next>,</#if>
                            </#list>
                        </span>
                    </div>
                </div>
                <div class="right_top_post">
                    <img onclick="goTo('audios/${media.id}')" src="https://stihi.ru/pics/2019/07/14/5314.jpg">
                </div>
                <br>
            </div>
            <div class="bottom_post">
                <div class="left_bottom_post">
                    <span class="post_comments" onclick="goTo('persons/posts/${id}/comments')">Комментарии: ${comments}</span>
                </div>
                <div class="right_bottom_post">
                    <span class="post_date">Опубликовано: ${date}</span>
                </div>
            </div>
        </div>
    </div>
</#macro>