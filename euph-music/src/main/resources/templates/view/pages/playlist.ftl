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
                <div class="audio_info">
                    <span>Название: <span class="text">${playlist.name}</span></span>
                </div>
                <div class="audio_info">
                    <span>Описание: <span class="text">${playlist.description}</span></span>
                </div>
                <div class="audio_info">
                    <span>Дата загрузки: <span class="text">${playlist.creationDate}</span></span>
                </div>
                <div class="audio_info">
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
            <div id="audios" class="audio_info">
                <#list playlist.audios as audio>
                    ${audio.name}
                </#list>
            </div>
        </div>
    </div>
</#macro>

<@main cssPage="playlist"/>