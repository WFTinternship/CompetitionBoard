// JS submit for button
function submitForm() {
    var searchStr = document.getElementById("searchStr").value;
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