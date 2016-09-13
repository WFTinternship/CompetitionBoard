// return date value from calendar
var startDate;
var endDate;
function submitDateNameInForm() {
    startDate = document.getElementById("startDate").value;
    endDate = document.getElementById("endDate").value;
}

// Select tournament element by specific value
// function selecFormatIdValue() {
//     document.getElementById("formatId");
// }

// Select element by specific value
function tournamentSelectIdValue() {
    document.getElementById("tournamentSelectId");
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

//Deletes selected tournament
function deleteCheckedElement() {
    var elements;
    var current;
    var noneChecked = true;

    elements = document.getElementsByName("tournamentNameId");
    for (var i = 0, len = elements.length; i < len; ++i) {
        if (elements[i].checked) {
            noneChecked = false;
            current = elements[i].value;
            if (confirm("Are you sure you want to delete tournament ? ") == true) {
                document.getElementById("deleteBtnId").submit();
            }
            break;
        }
    }
    if (noneChecked) {
        alert("Axbers nshi mi ban")
    }
}

//Updates selected tournament
function updateCheckedElement() {
    $('input[name=tournamentNameId]:checked').parents('tr').find('td[data-updatable="true"]').attr('contenteditable', true);  
    // if (noneChecked) {
    //     alert("Axbers nshi mi ban")
    // }
}

$(document).ready(function () {
    $('#updateTournamentTable').find('td').blur(function () {
        var tr = $(this).parent();
        $.ajax({
            url: '/updateTournament',
            type: 'GET',
            data: {
                nameUpdate: $('[data-name="nameUpdate"]', tr).text().trim(),
                tournamentNameId: $('[data-name="tournamentNameId"]', tr).text().trim(),
                startDateUpdate: $('[data-name="startDateUpdate"]', tr).text().trim(),
                endDateUpdate: $('[data-name="endDateUpdate"]', tr).text().trim(),
                locationUpdate: $('[data-name="locationUpdate"]', tr).text().trim(),
                descriptionUpdate: $('[data-name="descriptionUpdate"]', tr).text().trim(),
                formatNotUpdate: $('[data-name="formatUpdateNot"]', tr).text().trim(),
                managerNotUpdate: $('[data-name="managerUpdateNot"]', tr).text().trim()
            },
            success: function (tournament) {
                $('#updateTableElement').text(tournament);
            }
        });
    });
});
