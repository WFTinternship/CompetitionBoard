//JS submit for button
function submitForm() {
    var searchStr = document.getElementById("searchStr").value;
    if (searchStr != null) {
        document.forms["loginForm"].submit();
    }
}
// function autoClick() {
//     if ("hrefLoginId") {
//         document.getElementById("hrefLogInId").click();
//     } else {
//         document.getElementById("hrefSignInId").click();
//
//     }
//
// }

var hidden = false;
function hiddenBtn() {
    hidden = !hidden;
    if (hidden) {
        document.getElementById("hide").style.visibility = 'hidden';
    } else {
        document.getElementById("unhide").style.visibility = 'visible';
    }
}