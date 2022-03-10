// THIS CHECKS FOR COOKIES AND IF THEY EXSIST
// AUTO LOGIN / Send to user page
console.log("LOADED THE LOGIN.JS");
if (getCookie("username") != "") {
    console.log("GOT COOKIE TEST");
    window.location.href = "user.html";
}
else {
    console.log("COOKIE DIDNT MATCH");
}
function getCookie(cname) {
    let name = cname + "=";
    let decodedCookie = decodeURIComponent(document.cookie);
    let ca = decodedCookie.split(';');
    for (let i = 0; i < ca.length; i++) {
        let c = ca[i];
        while (c.charAt(0) == ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}