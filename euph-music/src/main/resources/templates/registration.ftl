<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript" charset="utf-8"></script>
<script>
    <#include "scripts/navigation.js">
    <#include "scripts/sender.js">
</script>
<style>
</style>
<body>
<input type="text" id="username" placeholder="username"/>
<input type="email" id="email" placeholder="email"/>
<input type="password" id="password" placeholder="password"/>
<button style="height: 20px; width: 50px" type="submit" onclick="userReg()">Reg</button>
<button onclick="goTo('login')">Go to Auth</button>
</body>
</html>
