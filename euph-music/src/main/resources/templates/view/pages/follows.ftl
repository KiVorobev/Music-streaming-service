<#include "../templates/main_template.ftl"/>
<#include "../templates/app_header.ftl"/>

<#macro header>
    <@appHeader/>
</#macro>

<#macro content>
    <div id="left_table">
        <ul>
            <div class="main_activity_table">
                <span>Подписчики:</span>
                <div class="scroll-table-body">
                    <table>
                        <tbody>
                        <#list person.followers as user>
                            <tr onclick="goTo('persons/${user.username}')">
                                <td class="image">
                                    <img src="https://all-aforizmy.ru/wp-content/uploads/2022/01/6936_43430_1a2d8f8dc6.jpg">
                                </td>
                                <td class="username">${user.username}</td>
                            </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>
            </div>
        </ul>
    </div>
    <div id="right_table">
        <ul>
            <div class="main_activity_table">
                <span>Подписки:</span>
                <div class="scroll-table-body">
                    <table>
                        <tbody>
                        <#list person.followTo as user>
                            <tr onclick="goTo('persons/${user.username}')">
                                <td class="image">
                                    <img src="https://all-aforizmy.ru/wp-content/uploads/2022/01/6936_43430_1a2d8f8dc6.jpg">
                                </td>
                                <td class="username">${user.username}</td>
                            </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>
            </div>
        </ul>
    </div>
</#macro>

<@main cssPage="follows"/>