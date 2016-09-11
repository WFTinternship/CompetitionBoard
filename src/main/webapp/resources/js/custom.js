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

function submitTournamentName() {
    document.getElementById("hrefTournamentName").submit();
}


//Deletes selected tournament
function deleteCheckedElement() {
    var elements;
    var current;

    elements = document.getElementsByName("tournamentNameId");
    for (var i = 0, len = elements.length; i < len; ++i) {
        if (elements[i].checked) {
            current = elements[i].value;
            if (confirm("Are you sure you want to delete tournament ? ") == true) {
                //alert(elements[i].value);
                document.getElementById("deleteBtnId").submit();
            } else {
                //alert(elements[i].value);
            }
        }
    }
}


//ToDo
//Updates selected tournament
function updateCheckedElement(x) {
    var elements;
    var current;

    elements = document.getElementsByName("tournamentNameId");
    for (var i = 0, len = elements.length; i < len; ++i) {
        if (elements[i].checked) {
            current = elements[i].value;
            //alert(current.getElementById("nameUpdate"));
            alert(current.parent);


            //document.getElementById("deleteBtnId").submit();
        } else {
            //alert(elements[i].value);
        }
    }

}





function editCustom() {
    $('button').click(function () {
        var $div = $('div'), isEditable = $div.is('.edit-td');
        $('div').prop('contenteditable', !isEditable).toggleClass('edit-td')
    })
}


$(document).ready(function () {
    $('#updateTournamentTable').find('td').blur(function () {
        $.ajax({
            url: '/updateTournament',
            type: 'GET',
            data: {
                nameUpdate: $('#nameUpdate').text().trim(),
                startDateUpdate: $('#startDateUpdate').text().trim(),
                endDateUpdate: $('#endDateUpdate').text().trim(),
                locationUpdate: $('#locationUpdate').text().trim(),
                formatNotUpdate: $('#formatNotUpdate').text().trim(),
                managerNotUpdate: $('#managerNotUpdate').text().trim(),
                idNotUpdated: $('#idNotUpdated').text().trim()
            },
            success: function (tournament) {
                $('#updateTableElement').text(tournament);
            }
        });
    });
});
