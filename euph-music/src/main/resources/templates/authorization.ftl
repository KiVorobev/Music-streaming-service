<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript" charset="utf-8"></script>
    <script>
        <#include "scripts/navigation.js">
        <#include "scripts/sender.js">
    </script>
</head>
<body>
<input type="text" id="login" placeholder="login"/>
<input type="password" id="password" placeholder="password"/>
<button style="height: 20px; width: 50px" type="submit" onclick="userAuth()">Auth</button>
<button onclick="goTo('registration')">Go to reg</button>
</body>
</html>