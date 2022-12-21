<#include "../templates/main_template.ftl"/>
<#include "../templates/app_header.ftl"/>

<#macro header>
    <@appHeader/>
</#macro>
<#macro content>
    <form enctype="multipart/form-data" method="post" action="/audios/create">
        <div>
            <input type="text" name="name" placeholder="Название"/>
            <textarea type="text" name="text" placeholder="Текст" required></textarea>
            <input type="file" name="image" placeholder="Картинка">
            <select multiple="multiple" name="authors">
                <#list persons as person>
                    <option value="${person.username}">${person.username}</option>
                </#list>
            </select>
            <select multiple="multiple" name="genres">
                <#list genres as genre>
                    <option value="${genre.name}">${genre.name}</option>
                </#list>
            </select>
            <button type="submit">Отправить</button>
        </div>
    </form>
</#macro>

<@main cssPage="audio_create"/>