<#macro entry page>
    <div id="logo">
        <img src="/images/logo.png">
    </div>
    <div id="inputs_block">
        <form method="post"
                <#if page == 'login'>
                    action="/login"
                <#elseif page == 'registration'>
                    action="/registration"
                </#if>
        >
            <div class="inputs">
                <input id="username" name="username" type="text" placeholder="Логин" required/>
            </div>
            <#if page == 'registration'>
                <div class="inputs">
                    <input id="email" name="email" type="text" placeholder="Email" required/>
                </div>
            </#if>
            <div class="inputs">
                <input id="password" name="password" type="password" placeholder="Пароль" required/>
            </div>
            <button id="submit_button" type="submit">
                <#if page == 'login'>Войти
                <#elseif page == 'registration'>Зарегистрироваться
                </#if>
            </button>
        </form>
    </div>
</#macro>