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

// Show/hidden menu items
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
// Show/hidden menu items
function showMenuItemReverse() {
    $('.visible-element').each(function() {
        $(this).removeClass('visible-element').addClass('hidden-element');
    });
}


//Edit table row
$('button').click(function(){
    var $div=$('div'), isEditable=$div.is('.edit-td');
    $('div').prop('contenteditable',!isEditable).toggleClass('edit-td')
})


//Checkbox select one
$('.myCheckbox').click(function() {
    $(this).siblings('input:checkbox').prop('checked', false);
});
