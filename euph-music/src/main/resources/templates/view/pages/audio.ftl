<#include "../templates/main_template.ftl"/>
<#include "../templates/app_header.ftl"/>

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
    <div id="content">
        <div id="left">
            <div id="image">
                <img src="https://stihi.ru/pics/2019/07/14/5314.jpg">
            </div>
            <div id="genres">
                <span>Жанры</span>
                <br>
                <#list audio.genres as genre>
                    <span>${genre.name}</span><#if genre_has_next>,</#if>
                </#list>
            </div>
        </div>
        <div id="right">
            <div id="information_block">
                <span>Информация</span>
            </div>
            <div class="audio_info">
                <span>Название: ${audio.name}</span>
            </div>
            <div class="audio_info">
                <span>Текст: <span id="audio_text">${audio.text}</span></span>
            </div>
            <div class="audio_info">
                <span>Дата загрузки: ${audio.uploadDate}</span>
            </div>
            <div class="audio_info">
            <span>Авторы:
                <#list audio.authors as author>
                    <span class="hover_span"
                          onclick="goTo('persons/${author.username}')">${author.username}</span><#if author_has_next>,</#if>
                </#list>
            </span>
            </div>
        </div>
    </div>
</#macro>

<@main cssPage="audio"/>