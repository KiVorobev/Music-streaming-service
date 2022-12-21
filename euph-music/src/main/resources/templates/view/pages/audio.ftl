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
        <div id="top">
            <div id="left">
                <div id="image">
                    <img src="https://stihi.ru/pics/2019/07/14/5314.jpg">
                </div>
            </div>
            <div id="right">
                <div id="information_block">
                    <span>Информация</span>
                </div>
                <div class="audio_info">
                    <span>Название: <span class="text">${audio.name}</span></span>
                </div>
                <div class="audio_info">
                    <span>Жанры:
                    <#list audio.genres as genre>
                        <span class="text">${genre.name}</span><#if genre_has_next>,</#if>
                    </#list>
                </div>
                <div class="audio_info">
                    <span>Дата загрузки: <span class="text">${audio.uploadDate}</span></span>
                </div>
                <div class="audio_info">
            <span>Авторы:
                <#list audio.authors as author>
                    <span class="hover_span text"
                          onclick="goTo('persons/${author.username}')">${author.username}</span><#if author_has_next>,</#if>
                </#list>
            </span>
                </div>
            </div>
        </div>
        <div id="bottom">
            <div id="text" class="audio_info">
                <span id="label_text">Текст</span>
                <br>
                <span id="audio_text" class="text">${audio.text}</span>
            </div>
        </div>
    </div>
</#macro>

<@main cssPage="audio"/>