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
<form action="/login" method="post">
    <label for="username"> Username:
        <input type="text" name="username">
    </label><br>
    <label for="password"> Paswword:
        <input type="password" name="password">
    </label><br>
    <button style="height: 20px; width: 50px" type="submit">Submit</button>
</form>
<button onclick="goTo('registration')">Go to reg</button>
</body>
</html>