<#macro entry page>
    <div id="logo">
        <img src="https://e7.pngegg.com/pngimages/436/592/png-clipart-ink-purple-computer-file-purple-ink-circle-antiquity-violet.png"/>
    </div>
    <div id="inputs_block">
        <div class="inputs">
            <input id="login" type="text" placeholder="Логин"/>
        </div>
        <div class="inputs">
            <input id="password" type="password" placeholder="Пароль"/>
        </div>
        <#if page == 'registration'>
            <div class="inputs">
                <input id="repeat_password" type="password" placeholder="Повторите пароль"/>
            </div>
            </div>
            <div id="buttons">
                <button type="submit" onclick="userReg()">Зарегистрироваться</button>
            </div>
            <#elseif page == 'authorization'>
                </div>
                <div id="buttons">
                    <button type="submit" onclick="userAuth()">Войти</button>
                </div>
        </#if>
</#macro>