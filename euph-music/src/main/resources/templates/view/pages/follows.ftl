<#include "../templates/main_template.ftl"/>
<#include "../templates/app_header.ftl"/>

<#macro header>
    <@appHeader username="username" balance="balance"/>
</#macro>

<#macro content>
    <ul>
        <div class="main_activity_table">
            <div class="scroll-table">
                <table>
                    <caption>Подписчики:</caption>
                    <thead>
                    <tr>
                        <td>Username</td>
                    </tr>
                    </thead>
                </table>
            </div>
            <div class="scroll-table-body">
                <table>
                    <tbody>
                    <#list person.followers as user>
                        <tr>
                            <td>${user.username}</td>
                        </tr>
                    </#list>
                    </tbody>
                </table>
            </div>
        </div>
    </ul>
    <ul>
        <div class="main_activity_table">
            <div class="scroll-table">
                <table>
                    <caption>Подписки:</caption>
                    <thead>
                    <tr>
                        <td>Username</td>
                    </tr>
                    </thead>
                </table>
            </div>
            <div class="scroll-table-body">
                <table>
                    <tbody>
                    <#list person.followTo as user>
                        <tr>
                            <td>${user.username}</td>
                        </tr>
                    </#list>
                    </tbody>
                </table>
            </div>
        </div>
    </ul>
</#macro>

<@main cssPage="follows"/>