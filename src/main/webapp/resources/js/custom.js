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

// Select element by specific value
function selectElementValue() {
    var x = document.getElementById("formatId");
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
    $('.visible-element').each(function() {
        $(this).removeClass('visible-element').addClass('hidden-element');
    });
}

// Table edit activates
function editTableActivity() {
    var x;
    x = document.getElementById("edit-td").value;
    return x=true;

}

function editContent(){
    var editable_elements = document.querySelectorAll("[contenteditable=false]");
    document.getElementById("edit-td").setAttribute("contentEditable", true);
}


