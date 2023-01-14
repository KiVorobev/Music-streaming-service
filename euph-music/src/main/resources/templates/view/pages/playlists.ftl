<#include "../templates/main_template.ftl"/>
<#include "../templates/app_header.ftl"/>

<#macro header>
    <@appHeader/>
</#macro>

<#macro content>
    <div id="topic">
        <div id="back_button" onclick="window.history.go(-1)">
            <img id="back_arrow" src="/images/arrow.png">
            Назад
        </div>
        <div id="caption">Плейлисты</div>
        <div id="make_block" onclick="goTo('playlists/create')">Создать плейлист</div>
    </div>
    <div id="content">
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
    </div>
</#macro>

<@main cssPage="playlists"/>