// JS submit for button
var searchStr;
function submitForm() {
     searchStr = document.getElementById("searchStr").value;
    if (searchStr != null) {
        document.forms["loginForm"].submit();
    }
}

// Hide/unhide button by specific element id
var hidden = false;
function hiddenBtn() {
    hidden = !hidden;
    if (hidden) {
        document.getElementById("hide").style.visibility = 'hidden';
    } else {
        document.getElementById("unhide").style.visibility = 'visible';
    }
}

function selectElement() {
    var x = document.getElementById("formatId");
    // if (x != 0){
    //     alert(x.options[x.selectedIndex].value);    
    // }
}

function showMenuItem() {
    document.getElementById("hide").style.visibility = "hidden";
    document.getElementById("unHide").style.visibility = "visible";
}

function showMenuItemReverse() {
    document.getElementById("unHide").style.visibility = "hidden";
    document.getElementById("hide").style.visibility = "visible";
}