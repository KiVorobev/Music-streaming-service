<#macro entry page>
    <div id="logo">
        <img src="https://adonius.club/uploads/posts/2022-02/1644938357_35-adonius-club-p-lotos-na-belom-fone-56.png"/>
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
                <input id="username" name="username" type="text" placeholder="Логин"/>
            </div>
            <#if page == 'registration'>
                <div class="inputs">
                    <input id="email" name="email" type="text" placeholder="Email"/>
                </div>
            </#if>
            <div class="inputs">
                <input id="password" name="password" type="password" placeholder="Пароль"/>
            </div>
            <button id="submit_button" type="submit">
                <#if page == 'login'>Войти
                <#elseif page == 'registration'>Зарегистрироваться
                </#if>
            </button>
        </form>
    </div>
</#macro>