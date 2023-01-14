<#include "../templates/main_template.ftl"/>
<#include "../templates/app_header.ftl"/>

<#macro header>
    <@appHeader/>
</#macro>

<#macro content>
    <div id="caption">
        <div id="back_button" onclick="goTo('persons/${loggedPerson.username}')">
            <img id="back_arrow" src="/images/arrow.png">
            Назад
        </div>
        <span>Новая запись</span>
    </div>
    <div id="content">
        <form method="post" action="create">
            <div id="description">
                <span id="description_label">Описание</span>
                <textarea maxlength="512" id="description_input" name="description"></textarea>
            </div>
            <div id="add_media">
                <div id="select_audio_block">
                    <label for="select_audio">Выбрать аудио</label>
                    <select id="select_audio" onchange="audioSelect()" name="audioId">
                        <option selected value>Выберите аудио</option>
                        <#list audios as audio>
                            <option value="${audio.id?c}">${audio.name}</option>
                        </#list>
                    </select>
                </div>
                <div id="select_playlist_block">
                    <label for="select_playlist">Выбрать плейлист</label>
                    <select id="select_playlist" onchange="playlistSelect()" name="playlistId">
                        <option selected value>Выберите плейлист</option>
                        <#list playlists as playlist>
                            <option value="${playlist.id?c}">${playlist.name}</option>
                        </#list>
                    </select>
                </div>
                <div id="submit_button">
                    <button type="submit">Добавить запись</button>
                </div>
            </div>
        </form>
    </div>
    <script>
        const audio = document.getElementById('select_audio')
        const playlist = document.getElementById('select_playlist')

        function audioSelect() {
            if (audio.options[0].selected) playlist.disabled = false
            else playlist.disabled = true
        }

        function playlistSelect() {
            if (playlist.options[0].selected) audio.disabled = false
            else audio.disabled = true
        }
    </script>
</#macro>

<@main cssPage="post_create"/>