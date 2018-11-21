function hashStringWithSalt(message, salt) {
    var iterations = 100;
    var userHash = CryptoJS.PBKDF2(message, salt, { keySize: 256/32, iterations: iterations });

    return userHash.toString();
}

function hashString(message) {
        var iterations = 100;
        var userHash = CryptoJS.PBKDF2(message, "", {keySize: 256 / 32, iterations: iterations});

        return userHash.toString();
}


function createEncryptKey(password, username) {
    return hashStringWithSalt(password, username);
}

function createPasswordHash(password, username) {
    return hashStringWithSalt(hashStringWithSalt(password, username), username);
}

function clearEncryptKeyFromCookie() {
    storeLocal("EncryptKey", null);
}

function storeEncryptedKey(key) {
    storeLocal("EncryptKey", key);
}

function getEncryptKeyFromCookie() {
    return getLocalData("EncryptKey");
}

function storeLocal(name,value) {
    // Check browser support
    if (typeof(Storage) !== "undefined") {
        // Store
        localStorage.setItem(name, value);

    } else {
        alert("Sorry, your browser does not support Web Storage...");
    }

}
function getLocalData(name) {
    // Check browser support
    if (typeof(Storage) !== "undefined") {
        // Store
        return localStorage.getItem(name);

    } else {
        alert("Sorry, your browser does not support Web Storage...");
    }
}

function encryptWithKey(clearPassword, key) {
    // - Code to execute when all DOM content is loaded.
    // - including fonts, images, etc.
    var iv  = CryptoJS.enc.Hex.parse("00000000000000000000000000000000");
    CryptoJS.pad.NoPadding = {pad: function(){}, unpad: function(){}};

    var bytes = CryptoJS.AES.encrypt(clearPassword, key);
    // var bytes = CryptoJS.AES.encrypt(clearPassword, key);
    var plaintext = bytes.toString();
    var base64String = Base64.encode(plaintext);
    return base64String;

}

function decryptPasswordWithKey(encryptedPasswordWithBase64, key) {
    // - Code to execute when all DOM content is loaded.
    // - including fonts, images, etc.
    try {
        // var iv  = CryptoJS.enc.Hex.parse("00000000000000000000000000000000");
        // CryptoJS.pad.NoPadding = {pad: function(){}, unpad: function(){}};
        //
        var encryptedPassword = Base64.decode(encryptedPasswordWithBase64);

        var bytes = CryptoJS.AES.decrypt(encryptedPassword, key);
        // var bytes = CryptoJS.AES.decrypt(encryptedPassword, key);
        //
        var plaintext = bytes.toString(CryptoJS.enc.Utf8);
        return plaintext;
    } catch (e){
        document.getElementById(atId).innerText = "Cannot decrypt";
    }
}

var Base64={_keyStr:"ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=",encode:function(e){var t="";var n,r,i,s,o,u,a;var f=0;e=Base64._utf8_encode(e);while(f<e.length){n=e.charCodeAt(f++);r=e.charCodeAt(f++);i=e.charCodeAt(f++);s=n>>2;o=(n&3)<<4|r>>4;u=(r&15)<<2|i>>6;a=i&63;if(isNaN(r)){u=a=64}else if(isNaN(i)){a=64}t=t+this._keyStr.charAt(s)+this._keyStr.charAt(o)+this._keyStr.charAt(u)+this._keyStr.charAt(a)}return t},decode:function(e){var t="";var n,r,i;var s,o,u,a;var f=0;e=e.replace(/[^A-Za-z0-9+/=]/g,"");while(f<e.length){s=this._keyStr.indexOf(e.charAt(f++));o=this._keyStr.indexOf(e.charAt(f++));u=this._keyStr.indexOf(e.charAt(f++));a=this._keyStr.indexOf(e.charAt(f++));n=s<<2|o>>4;r=(o&15)<<4|u>>2;i=(u&3)<<6|a;t=t+String.fromCharCode(n);if(u!=64){t=t+String.fromCharCode(r)}if(a!=64){t=t+String.fromCharCode(i)}}t=Base64._utf8_decode(t);return t},_utf8_encode:function(e){e=e.replace(/rn/g,"n");var t="";for(var n=0;n<e.length;n++){var r=e.charCodeAt(n);if(r<128){t+=String.fromCharCode(r)}else if(r>127&&r<2048){t+=String.fromCharCode(r>>6|192);t+=String.fromCharCode(r&63|128)}else{t+=String.fromCharCode(r>>12|224);t+=String.fromCharCode(r>>6&63|128);t+=String.fromCharCode(r&63|128)}}return t},_utf8_decode:function(e){var t="";var n=0;var r=c1=c2=0;while(n<e.length){r=e.charCodeAt(n);if(r<128){t+=String.fromCharCode(r);n++}else if(r>191&&r<224){c2=e.charCodeAt(n+1);t+=String.fromCharCode((r&31)<<6|c2&63);n+=2}else{c2=e.charCodeAt(n+1);c3=e.charCodeAt(n+2);t+=String.fromCharCode((r&15)<<12|(c2&63)<<6|c3&63);n+=3}}return t}}
