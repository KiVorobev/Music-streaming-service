<#include "../templates/main_template.ftl"/>
<#include "../templates/app_header.ftl"/>

<#macro header>
    <@appHeader/>
</#macro>

<#macro content>
    <div id="content">
        <span>Авторы</span>
        <div id="authors">
            <#list persons as person>
                <div class="person_block" onclick="goTo('persons/${person.username}')">
                    <div class="image person_cell">
                        <#if person.image??>
                            <img src="/persons/${person.username}/avatar">
                        <#else>
                            <img src="/persons/avatar">
                        </#if>
                    </div>
                    <div class="name_authors person_cell">
                        ${person.username}
                    </div>
                </div>
            </#list>
        </div>
        <span>Плейлисты</span>
        <div id="playlists">
            <#list playlists as playlist>
                <div class="playlist_block" onclick="goTo('playlists/${playlist.id?c}')">
                    <div class="image playlist_cell">
                        <#if playlist.image??>
                            <img src="/playlists/${playlist.id?c}/avatar">
                        <#else>
                            <img src="https://stihi.ru/pics/2019/07/14/5314.jpg">
                        </#if>
                    </div>
                    <div class="name_authors playlist_cell">
                        <span class="elem_name">${playlist.name}</span>
                        <br>
                        <span class="elem_authors">
                                        <#list playlist.authors as author>
                                            <span class="elem_author">
                                            ${author.username}<#if author_has_next>,</#if></span></#list></span>
                    </div>
                </div>
            </#list>
        </div>
        <span>Аудио</span>
        <div id="audios" class="audio_info">
            <#list audios as audio>
                <div class="audio_block" onclick="goTo('audios/${audio.id?c}')">
                    <div class="image audio_cell">
                        <#if audio.image??>
                            <img src="/audios/${audio.id?c}/avatar">
                        <#else>
                            <img src="https://stihi.ru/pics/2019/07/14/5314.jpg">
                        </#if>
                    </div>
                    <div class="name_authors audio_cell">
                        <span class="elem_name">${audio.name}</span>
                        <br>
                        <span class="elem_authors">
                                        <#list audio.authors as author>
                                            <span class="elem_author">
                                            ${author.username}<#if author_has_next>,</#if></span></#list></span>
                    </div>
                </div>
            </#list>
        </div>
    </div>
</#macro>

<@main cssPage="search"/>