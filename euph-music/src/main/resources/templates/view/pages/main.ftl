<#include "../templates/main_template.ftl"/>
<#include "../templates/app_header.ftl"/>

<#macro header>
    <@appHeader/>
</#macro>

<#macro content>
    <div id="topic">
        <div>Последние загруженные аудио</div>
    </div>
    <div id="table">
        <table>
            <tbody>
            <#list audios as audio>
                <tr onclick="goTo('audios/${audio.id?c}')">
                    <td class="image">
                        <#if audio.image??>
                            <img src="/audios/${audio.id?c}/avatar">
                        <#else>
                            <img src="https://stihi.ru/pics/2019/07/14/5314.jpg">
                        </#if>
                    </td>
                    <td class="name_authors">
                        <span class="elem_name">${audio.name}</span>
                        <br>
                        <span class="elem_authors">
                                        <#list audio.authors as author>
                                            <span class="elem_author">
                                            ${author.username}<#if author_has_next>,</#if></span></#list></span>
                    </td>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>
</#macro>

<@main cssPage="main"/>