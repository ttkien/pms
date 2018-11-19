<%@ page contentType = "text/html; charset = UTF-8" %>
<html>
<head>
    <title>Getting Started: Serving Web Content</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <script src="public/js/crypto-js/aes.js"></script>
    <script src="public/js/crypto-js/pbkdf2.js"></script>
    <script src="public/js/jquery.cookie.min.js"></script>

</head>
<body>
<p>name: ${} #{}</p>

Welcome to Password Management System!
Please input user/user to pass the authentication
<form action="login" method="post" onsubmit="return beforeSubmit()">
    <input type="hidden" name="passwordHash" id="passwordHash" value=""/>
    <table>
        <tr>
            <td>Username</td>
            <td>
                <input type="text" name="username" id="username">
            </td>
        </tr>
        <tr>
            <td>Password</td>
            <td>
                <input type="password" id="password">
            </td>
        </tr>
        <tr>
            <td></td>
            <td>
                <button type="submit">Submit</button>
            </td>
        </tr>

    </table>
</form>
<script type="text/javascript">
    document.addEventListener("DOMContentLoaded", function(event) {
        // - Code to execute when all DOM content is loaded.
        // - including fonts, images, etc.
        clearEncryptKeyFromCookie();
    });

    function beforeSubmit() {
        var password = document.getElementById("password").value ;
        var username = document.getElementById("username").value ;

        var passwordHash = createPasswordHash(password, username);
        document.getElementById("passwordHash").value = passwordHash;

        var encryptKey = createEncryptKey(password, username);
        setEncryptKeyFromCookie(encryptKey);
    }

    function hash(message, salt) {
        var iterations = 100;
        var userHash = CryptoJS.PBKDF2(message, salt, { keySize: 256/32, iterations: iterations });

        return userHash.toString();
    }

    function createEncryptKey(password, username) {
        return hash(password, username);
    }

    function createPasswordHash(password, username) {
        return hash(hash(password, username), username);
    }

    function clearEncryptKeyFromCookie() {
        $.removeCookie('EncryptKey');
    }

    function setEncryptKeyFromCookie(key) {
        $.cookie('EncryptKey', key);
    }

</script>
</body>
</html>