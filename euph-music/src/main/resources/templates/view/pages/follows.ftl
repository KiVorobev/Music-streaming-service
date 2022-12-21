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
    <div id="left_table">
        <div class="main_activity_table">
            <span>Подписчики:</span>
            <div class="scroll-table-body">
                <table>
                    <tbody>
                    <#list person.followers as user>
                        <tr onclick="goTo('persons/${user.username}')">
                            <td class="image">
                                <#if user.image??>
                                    <img onclick="goTo('persons/${user.username}')"
                                         src="/persons/${user.username}/avatar">
                                <#else>
                                    <img onclick="goTo('persons/${user.username}')"
                                         src="/persons/avatar">
                                </#if>
                            </td>
                            <td class="username">${user.username}</td>
                        </tr>
                    </#list>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <div id="right_table">
        <div class="main_activity_table">
            <span>Подписки:</span>
            <div class="scroll-table-body">
                <table>
                    <tbody>
                    <#list person.followTo as user>
                        <tr onclick="goTo('persons/${user.username}')">
                            <td class="image">
                                <#if user.image??>
                                    <img onclick="goTo('persons/${user.username}')"
                                         src="/persons/${user.username}/avatar">
                                <#else>
                                    <img onclick="goTo('persons/${user.username}')"
                                         src="/persons/avatar">
                                </#if>
                            </td>
                            <td class="username">${user.username}</td>
                        </tr>
                    </#list>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</#macro>

<@main cssPage="follows"/>