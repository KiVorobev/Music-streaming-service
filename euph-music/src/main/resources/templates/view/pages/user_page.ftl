<#include "../templates/main_template.ftl"/>
<#include "../templates/app_header.ftl"/>

<#macro header>
    <@appHeader username="${person.username}" balance="${person.balance}"/>
</#macro>

<#macro content>
    <div id="container">
        <div class="up left" id="up-left">
            <text>Статус</text>
            <br>
            <text>Описание</text>
        </div>
        <div class="up right" id="up-right">
            <img src="https://flyclipart.com/thumbs/user-icon-business-man-flat-1030903.png">
        </div>
        <div class="down right" id="down-right">
            <#--        Достижения-->
            <#--        Сохраненные аудио-->
        </div>
        <div class="down left" id="down-left">
            <#--        Посты-->
        </div>
    </div>
</#macro>

<@main cssPage="user_page"/>