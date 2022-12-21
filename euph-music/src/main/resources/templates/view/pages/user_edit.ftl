<#include "../templates/main_template.ftl"/>
<#include "../templates/app_header.ftl"/>

<#macro header>
    <@appHeader/>
</#macro>

<#macro content>
    <form method="post" action="/persons/update" enctype="multipart/form-data">
        <div id="top_area">
            <div id="left">
                <div id="status">
                    <span>Статус</span>
                    <br>
                    <#if person.status??>
                        <textarea name="status" maxlength="128" id="input_status">${person.status}</textarea>
                    <#else> <textarea name="status" maxlength="128" id="input_status"></textarea>
                    </#if>
                </div>
                <div id="description">
                    <span>Описание</span>
                    <br>
                    <#if person.description??>
                        <textarea name="description" maxlength="512" id="input_description">${person.description}</textarea>
                    <#else> <textarea name="description" maxlength="512" id="input_description"></textarea>
                    </#if>
                </div>
            </div>
            <div id="right">
                <div id="image">
                    <#if person.image??>
                        <img src="/persons/${person.username}/avatar">
                    <#else>
                        <img src="/persons/avatar">
                    </#if>
                </div>
                <div id="image_edit_block">
                    <input id="image_edit" type="file" name="image">
                    <label id="select_file_label" for="image_edit">Выберите файл</label>
                </div>
            </div>
        </div>
        <div id="bottom_area">
            <button id="save_edit" type="submit">Сохранить изменения</button>
            <div id="cancel_edit" onclick="goTo('persons/${loggedPerson.username}')">Отменить изменения</div>
        </div>
    </form>
</#macro>

<@main cssPage="user_edit"/>