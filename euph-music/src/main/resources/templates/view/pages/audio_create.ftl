<#include "../templates/main_template.ftl"/>
<#include "../templates/app_header.ftl"/>

<#macro header>
    <@appHeader/>
</#macro>
<#macro content>
    <div id="topic">
        <div id="back_button" onclick="goTo('persons/${loggedPerson.username}/saved')">
            <img id="back_arrow" src="/images/arrow.png">
            Назад
        </div>
    </div>
    <form enctype="multipart/form-data" method="post" action="/audios/create">
        <div id="content">
            <div id="audio_name_block">
                <input id="audio_name_input" type="text" name="name" placeholder="Название" required>
            </div>
            <div id="audio_text_block">
                <textarea id="audio_text" name="text" placeholder="Текст" required></textarea>
            </div>
            <div id="audio_image_block">
                <input id="audio_image_input" type="file" name="image" placeholder="Картинка">
            </div>
            <div id="audio_authors_block">
                <label for="audio_authors_select">Выберите соавторов</label>
                <select id="audio_authors_select" multiple="multiple" name="authors">
                    <#list persons as person>
                        <option value="${person.username}">${person.username}</option>
                    </#list>
                </select>
            </div>
            <div>
                <label for="audio_genres_select">Выберите жанр</label>
                <select id="audio_genres_select" multiple="multiple" name="genres">
                    <#list genres as genre>
                        <option value="${genre.name}">${genre.name}</option>
                    </#list>
                </select>
            </div>
        </div>
        <div id="buttons">
            <button type="submit">Создать аудио</button>
        </div>
    </form>
    <script src="https://cdn.jsdelivr.net/gh/habibmhamadi/multi-select-tag/dist/js/multi-select-tag.js"></script>
    <script>
        new MultiSelectTag('audio_authors_select')
        new MultiSelectTag('audio_genres_select')
    </script>
</#macro>

<@main cssPage="audio_create"/>