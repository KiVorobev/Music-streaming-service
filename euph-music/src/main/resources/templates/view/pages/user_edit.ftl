<#include "../templates/main_template.ftl"/>
<#include "../templates/app_header.ftl"/>

<#macro header>
    <@appHeader/>
</#macro>

<#macro content>
    <div id="top_area">
        <div id="left">
            <div id="status">
                <span>Статус</span>
                <br>
                <#if person.status??>
                    <textarea maxlength="128" id="input_status">${person.status}</textarea>
                <#else> <textarea maxlength="128" id="input_status"></textarea>
                </#if>
            </div>
            <div id="description">
                <span>Описание</span>
                <br>
                <#if person.description??>
                    <textarea maxlength="512" id="input_description">${person.description}</textarea>
                <#else> <textarea maxlength="512" id="input_description"></textarea>
                </#if>
            </div>
        </div>
        <div id="right">
            <div id="image">
                <#if person.image??>
                    <img src="/persons/${person.username}/avatar">
                <#else>
                    <img src="https://all-aforizmy.ru/wp-content/uploads/2022/01/6936_43430_1a2d8f8dc6.jpg">
                </#if>
            </div>
            <div id="image_edit_block">
                <input id="image_edit" type="file" name="image">
                <label id="select_file_label" for="image_edit">Выберите файл</label>
            </div>
        </div>
    </div>
    <div id="bottom_area">
        <div id="save_edit" onclick="updateUser()">Сохранить изменения</div>
        <div id="cancel_edit" onclick="goTo('persons/${loggedPerson.username}')">Отменить изменения</div>
    </div>
</#macro>

<@main cssPage="user_edit"/>