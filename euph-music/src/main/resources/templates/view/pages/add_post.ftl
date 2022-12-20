<#include "../templates/main_template.ftl"/>
<#include "../templates/app_header.ftl"/>

<#macro header>
    <@appHeader/>
</#macro>

<#macro content>
    <div id="caption">
        <div id="back_button" onclick="goTo('persons/${loggedPerson.username}')">
            <img id="back_arrow" src="https://stihi.ru/pics/2019/07/14/5314.jpg">
            Назад
        </div>
        <span>Новая запись</span>
    </div>
    <div id="content">
        <div id="description">
            <span id="description_label">Описание</span>
            <textarea maxlength="512" id="description_input"></textarea>
        </div>
        <div id="add_media">
            <div id="select_audio">
                <span id="select_audio_button">Выбрать аудио</span>
            </div>
            <div id="select_playlist">
                <span id="select_playlist_button">Выбрать плейлист</span>
            </div>
        </div>
    </div>
</#macro>

<@main cssPage="add_post"/>