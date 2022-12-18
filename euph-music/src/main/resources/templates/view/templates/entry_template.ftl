<#macro entry page>
    <div id="logo">
        <img src="https://adonius.club/uploads/posts/2022-02/1644938357_35-adonius-club-p-lotos-na-belom-fone-56.png"/>
    </div>
    <div id="inputs_block">
        <div class="inputs">
            <input id="username" type="text" placeholder="Логин"/>
        </div>
        <#if page == 'registration'>
            <div class="inputs">
                <input id="email" type="text" placeholder="Email"/>
            </div>
            <label for="image"> Image
                <input id="image" type="file" name="image">
            </label>
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