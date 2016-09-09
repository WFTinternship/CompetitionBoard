var searchStr;
function submitSearchNameInForm() {
    searchStr = document.getElementById("searchStr").value;
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
$(document).ready(function () {
    showMenuItem();
});

// Show/hidden menu items
function showMenuItem() {
    if ($('#login-status').val() !== '') {
        $('.visible-when-logged-in').each(function () {
            $(this).removeClass('hidden-element');
        });
        $('.hidden-when-logged-in').each(function () {
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
    $('.visible-element').each(function () {
        $(this).removeClass('visible-element').addClass('hidden-element');
    });
}
//Gets radioBtn value
var deletedElementValue;
function getCheckedElementValue() {
    deletedElementValue = document.getElementsByName("tournament").value;
    return deletedElementValue;
}

//Edit table row
// $('button').click(function () {
//     var $div = $('div'), isEditable = $div.is('.edit-td');
//     $('div').prop('contenteditable', !isEditable).toggleClass('edit-td')
// })


// UpdateTableCell
$(document).ready(function() {
    $('#updateTournamentTable td').blur(function() {
        $.ajax({
            url : '/updateTournament',
            type: 'POST',
            data : {
                nameUpdate : $('#nameUpdate').text().trim(),
                startDateUpdate : $('#startDateUpdate').text().trim(),
                endDateUpdate : $('#endDateUpdate').text().trim(),
                locationUpdate : $('#locationUpdate').text().trim(),
                formatNotUpdate : $('#formatNotUpdate').text().trim(),
                managerNotUpdate : $('#managerNotUpdate').text().trim(),
                idNotUpdated : $('#idNotUpdated').text().trim()
            },
            success : function(tournament) {
                $('#updateTableElement').text(tournament);
            },
            error: function (error) {
                // throw new Error(error);
                console.error(error);
            }
        });
    });
});




