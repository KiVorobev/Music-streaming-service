<#macro main cssPage>
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport"
              content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>EuphoMusic</title>
        <link rel="stylesheet"
              href="https://cdn.jsdelivr.net/gh/habibmhamadi/multi-select-tag/dist/css/multi-select-tag.css">
        <style type="text/css">
            <#include "../../styles/template_styles/index.css">

            <#if cssPage == "entry">
            <#include "../../styles/template_styles/entry_header.css">
            <#else>
            <#include "../../styles/template_styles/app_header.css">
            </#if>

            <#include "../../styles/page_styles/${cssPage}.css">
        </style>
        <link rel="stylesheet" type="text/css"
              href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/css/toastr.css">
        <script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript" charset="utf-8"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/js/toastr.min.js"></script>
        <script>
            <#include "../../scripts/navigator.js">
        </script>
    </head>
    <body>
    <header>
        <@header/>
    </header>
    <main>
        <@content/>
    </main>
    </body>
    </html>
</#macro>