<#include "../templates/main_template.ftl"/>
<#include "../templates/app_header.ftl"/>

<#macro header>
    <@appHeader/>
</#macro>

<#macro content>
    <div id="caption">
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