<#include "../templates/main_template.ftl"/>
<#include "../templates/app_header.ftl"/>

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
    <div id="content">
        <div id="top">
            <div id="left">
                <div id="image">
                    <#if playlist.image??>
                        <img src="/playlists/${playlist.id?c}/avatar">
                    <#else>
                        <img src="https://stihi.ru/pics/2019/07/14/5314.jpg">
                    </#if>
                </div>
            </div>
            <div id="right">
                <div id="information_block">
                    <span>Информация</span>
                </div>
                <div class="playlist_info">
                    <span>Название: <span class="text">${playlist.name}</span></span>
                </div>
                <div class="playlist_info">
                    <span>Описание: <span class="text">${playlist.description}</span></span>
                </div>
                <div class="playlist_info">
                    <span>Дата загрузки: <span class="text">${playlist.creationDate}</span></span>
                </div>
                <div class="playlist_info">
            <span>Авторы:
                <#list playlist.authors as author>
                    <span class="hover_span text"
                          onclick="goTo('persons/${author.username}')">${author.username}</span><#if author_has_next>,</#if>
                </#list>
            </span>
                </div>
            </div>
        </div>
        <div id="bottom">
            <#if playlist.ownedBy>
                <form method="post" action="${playlist.id?c}/delete">
                    <button id="remove_button" class="remove_playlist" type="submit">Удалить плейлист</button>
                </form>
            </#if>
            <div id="audios" class="audio_info">
                <#list 0..playlist.audios?size-1 as index>
                    <div class="audio_block" onclick="goTo('audios/${playlist.audios[index].id?c}')">
                        <div class="numeration">${index+1}</div>
                        <div class="image audio_cell">
                            <#if playlist.audios[index].image??>
                                <img src="/audios/${playlist.audios[index].id?c}/avatar">
                            <#else>
                                <img src="https://stihi.ru/pics/2019/07/14/5314.jpg">
                            </#if>
                        </div>
                        <div class="name_authors audio_cell">
                            <span class="elem_name">${playlist.audios[index].name}</span>
                            <br>
                            <span class="elem_authors">
                                        <#list playlist.audios[index].authors as author>
                                            <span class="elem_author">
                                            ${author.username}<#if author_has_next>,</#if></span></#list></span>
                        </div>
                    </div>
                </#list>
            </div>
        </div>
    </div>
</#macro>

<@main cssPage="playlist"/>