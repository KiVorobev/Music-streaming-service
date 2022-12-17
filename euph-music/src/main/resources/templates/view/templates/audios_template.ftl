<#macro audios page>
    <#if page == "saved">
        <div class="main_activity_table">
            <span id="caption">Сохраненные аудио <span class="username" onclick="goTo('persons/${person.username}')">${person.username}</span></span>
            <div class="scroll-table-body">
                <table>
                    <tbody>
                    <#list person.savedAudios as elem>
                        <tr onclick="goTo('audios/${elem.id}')">
                            <td class="image">
                                <img src="https://stihi.ru/pics/2019/07/14/5314.jpg">
                            </td>
                            <td class="name_authors">
                                <span class="elem_name">${elem.name}</span>
                                <br>
                                <span class="elem_authors">
                                        <#list elem.authors as author>
                                        <span class="elem_author" onclick="goTo('persons/${author.username}')">
                                            ${author.username}<#if author_has_next>,</#if></span></#list></span>
                            </td>
                            <td class="action_button">
                                <button/>
                            </td>
                        </tr>
                    </#list>
                    </tbody>
                </table>
            </div>
        </div>
    <#elseif page == "loaded">
        <div class="main_activity_table">
            <span id="caption">Сохраненные аудио <span class="username" onclick="goTo('persons/${person.username}')">${person.username}</span></span>
            <div class="scroll-table-body">
                <table>
                    <tbody>
                    <#list person.loadedAudios as elem>
                        <tr onclick="goTo('audios/${elem.id}')">
                            <td class="image">
                                <img src="https://stihi.ru/pics/2019/07/14/5314.jpg">
                            </td>
                            <td class="name_authors">
                                <span class="elem_name">${elem.name}</span>
                                <br>
                                <span class="elem_authors">
                                        <#list elem.authors as author>
                                        <span class="elem_author" onclick="goTo('persons/${author.username}')">
                                            ${author.username}<#if author_has_next>,</#if></span></#list></span>
                            </td>
                            <td class="action_button">
                                <button/>
                            </td>
                        </tr>
                    </#list>
                    </tbody>
                </table>
            </div>
        </div>
    </#if>
</#macro>