// JS submit for tournament search button
var searchStr;
function submitSearchNameInForm() {
     searchStr = document.getElementById("searchStr").value;
    if (searchStr != null) {
        document.forms["loginForm"].submit();
    }
}

// return date value from calendar
var startDate;
var endDate;
function submitDateNameInForm() {
    startDate = document.getElementById("startDate").value;
    endDate = document.getElementById("endDate").value;
}


// Hide/unhide button by specific element id
var hidden = false;
function hiddenBtn() {
    hidden = !hidden;
    if (hidden) {
        // document.getElementById("hide").style.visibility = 'hidden';
        // document.getElementById("hide").style.display = 'none';
    } else {
        // document.getElementById("unhide").style.visibility = 'visible';
        // document.getElementById("unhide").style.display = 'block';
    }
}

function selectElement() {
    var x = document.getElementById("formatId");
    // if (x != 0){
    //     alert(x.options[x.selectedIndex].value);    
    // }
}

$(document).ready(function() {     
    showMenuItem();
});

function showMenuItem() {
    if($('#login-status').val() !== '') {
        $('.visible-when-logged-in').each(function() {
            $(this).removeClass('hidden-element');
        });
        $('.hidden-when-logged-in').each(function() {
            $(this).addClass('hidden-element');
        });
    } else {
        $('.visible-when-logged-in').each(function () {
            $(this).addClass('hidden-element');
        });
        $('.hidden-when-logged-in').each(function () {
            $(this).removeClass('hidden-element');
        });
    }
    
    
}

function showMenuItemReverse() {
    // document.getElementById("unHide").style.display = "none";
    // document.getElementById("hide").style.display = "block";
    
    // document.querySelectorAll(".unHide2").style.display = "none";
    // document.querySelectorAll(".hide2").style.display = "block";
    $('.visible-element').each(function() {
        $(this).removeClass('visible-element').addClass('hidden-element');
    });
}