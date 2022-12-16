<#macro audios page>
    <div>
        <#if page == 'saved'>
            <#list person.savedAudios as audio>
                ${audio.name}
            </#list>
        <#elseif page == 'loaded'>
            <#list person.loadedAudios as audio>
                ${audio.name}
            </#list>
        </#if>
    </div>
</#macro>