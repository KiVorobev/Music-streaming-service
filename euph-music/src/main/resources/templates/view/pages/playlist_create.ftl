<#include "../templates/main_template.ftl"/>
<#include "../templates/app_header.ftl"/>

<#macro header>
    <@appHeader/>
</#macro>
<#macro content>
    <div id="topic">
        <div id="back_button" onclick="goTo('persons/${loggedPerson.username}')">
            <img id="back_arrow" src="/images/arrow.png">
            Назад
        </div>
    </div>
    <form enctype="multipart/form-data" method="post" action="/playlists/create">
        <div id="content">
            <div id="playlist_name_block">
                <input id="playlist_name_input" type="text" name="name" placeholder="Название" maxlength="32" required>
            </div>
            <div id="playlist_description_block">
                <textarea id="playlist_description" name="description" placeholder="Описание" maxlength="5000"
                          required></textarea>
            </div>
            <div id="playlist_image_block">
                <input id="playlist_image_input" type="file" name="image" placeholder="Картинка" requied>
            </div>
            <div id="playlist_authors_block">
                <label for="playlist_authors_block">Выберите соавторов</label>
                <select id="playlist_authors_select" multiple="multiple" name="authors">
                    <#list persons as person>
                        <option value="${person.username}">${person.username}</option>
                    </#list>
                </select>
            </div>
            <div>
                <label for="audios_select">Выберите аудио</label>
                <select id="audios_select" multiple="multiple" name="audios" required>
                    <#list audios as audio>
                        <option value="${audio.id?c}">${audio.name}</option>
                    </#list>
                </select>
            </div>
        </div>
        <div id="buttons">
            <button type="submit">Создать Плейлист</button>
        </div>
    </form>
    <script src="https://cdn.jsdelivr.net/gh/habibmhamadi/multi-select-tag/dist/js/multi-select-tag.js"></script>
    <script>
        new MultiSelectTag('playlist_authors_select')
        new MultiSelectTag('audios_select')
    </script>
</#macro>

<@main cssPage="playlist_create"/>