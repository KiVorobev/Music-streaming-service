<#macro audios page>
    <#if page == "saved">
        <div class="main_activity_table">
            <div id="topic">
                <div id="back_button" onclick="goTo('persons/${person.username}')">
                    <img id="back_arrow" src="/images/arrow.png">
                    Назад
                </div>
                <div id="caption">Сохраненные аудио</div>
                <#if person.username == loggedPerson.username>
                    <div id="make_block" onclick="goTo('playlists/create')">Создать плейлист</div>
                <#else>
                    <div id="make_block"></div>
                </#if>
            </div>
            <div class="scroll-table-body">
                <table>
                    <tbody>
                    <#list person.savedAudios as elem>
                        <tr onclick="goTo('audios/${elem.id?c}')">
                            <td class="image">
                                <#if elem.image??>
                                    <img src="/audios/${elem.id?c}/avatar">
                                <#else>
                                    <img src="https://stihi.ru/pics/2019/07/14/5314.jpg">
                                </#if>
                            </td>
                            <td class="name_authors">
                                <span class="elem_name">${elem.name}</span>
                                <br>
                                <span class="elem_authors">
                                        <#list elem.authors as author>
                                            <span class="elem_author">
                                            ${author.username}<#if author_has_next>,</#if></span></#list></span>
                            </td>
                        </tr>
                    </#list>
                    </tbody>
                </table>
            </div>
        </div>
    <#elseif page == "loaded">
        <div class="main_activity_table">
            <div id="topic">
                <div id="back_button" onclick="goTo('persons/${person.username}')">
                    <img id="back_arrow" src="/images/arrow.png">
                    Назад
                </div>
                <div id="caption">Авторство</div>
                <#if person.username == loggedPerson.username>
                    <div id="make_block" onclick="goTo('audios/create')">Добавить аудио</div>
                <#else>
                    <div id="make_block"></div>
                </#if>
            </div>
            <div class="scroll-table-body">
                <table>
                    <tbody>
                    <#list person.loadedAudios as elem>
                        <tr onclick="goTo('audios/${elem.id?c}')">
                            <td class="image">
                                <#if elem.image??>
                                    <img src="/audios/${elem.id?c}/avatar">
                                <#else>
                                    <img src="https://stihi.ru/pics/2019/07/14/5314.jpg">
                                </#if>
                            </td>
                            <td class="name_authors">
                                <span class="elem_name">${elem.name}</span>
                                <br>
                                <span class="elem_authors">
                                        <#list elem.authors as author>
                                            <span class="elem_author">
                                            ${author.username}<#if author_has_next>,</#if></span></#list></span>
                            </td>
                        </tr>
                    </#list>
                    </tbody>
                </table>
            </div>
        </div>
    </#if>
</#macro>