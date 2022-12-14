<#macro entry page>
    <div id="logo">
        <img src="https://e7.pngegg.com/pngimages/436/592/png-clipart-ink-purple-computer-file-purple-ink-circle-antiquity-violet.png"/>
    </div>
    <div id="inputs_block">
        <div class="inputs">
            <input id="username" type="text" placeholder="Логин"/>
        </div>
        <#if page == 'registration'>
            <div class="inputs">
                <input id="email" type="text" placeholder="Email"/>
            </div>
        </#if>
        <div class="inputs">
            <input id="password" type="password" placeholder="Пароль"/>
        </div>
    </div>
    <div id="buttons">
        <#if page == 'registration'>
            <button type="submit" onclick="userReg()">Зарегистрироваться</button>
        <#elseif page == 'login'>
            <button type="submit" onclick="userAuth()">Войти</button>
        </#if>
    </div>
</#macro>