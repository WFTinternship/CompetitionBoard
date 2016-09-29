// return date value from calendar
var startDate;
var endDate;
function submitDateNameInForm() {
    startDate = document.getElementById("startDate").value;
    endDate = document.getElementById("endDate").value;
}

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

//-=-=-=-=-=-=-=-=-=-=--=-=-=-=-=-=-=-=-=-=-TOURNAMENT-=-=-=-=-=-=-=-=-=-=--=-=-=-=-=-=-=-=-=-=-

// Deletes selected tournament
function deleteSelectedTournament() {
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
        alert("Please, select the tournament...")
    }
}

// Updates selected tournament
function updateSelectedTournament() {
    $('input[name=tournamentNameId]:checked').parents('tr').find('td[data-updatable="true"]').attr('contenteditable', true);
}
$(document).ready(function () {
    $('#updateTournamentTable').find('td').blur(function () {
        var tr = $(this).parent();
        $.ajax({
            url: '/updateTournament',
            type: 'GET',
            data: {
                nameUpdate: $('[data-name="nameUpdate"]', tr).text().trim(),
                tournamentNameId: $('[name="tournamentNameId"]', tr).val(),
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


//-=-=-=-=-=-=-=-=-=-=--=-=-=-=-=-=-=-=-=-=-GROUPS-=-=-=-=-=-=-=-=-=-=--=-=-=-=-=-=-=-=-=-=-
// Assign selected member to group
function assignToGroup() {
    var elements;
    var current;
    var noneChecked = true;
    elements = document.getElementsByName("memberNameId");
    for (var i = 0, len = elements.length; i < len; ++i) {
        if (elements[i].checked) {
            noneChecked = false;
            current = elements[i].value;
            if (confirm("Are you sure you want to assign to group ? ") == true) {
                document.getElementById("assignToGroupBtn").submit();
            }
            break;
        }
    }
    if (noneChecked) {
        alert("Please, select the group...")
    }
}

// Deletes selected groups
function deleteSelectedGroup() {
    var elements;
    var current;
    var noneChecked = true;
    elements = document.getElementsByName("groupNameId");
    for (var i = 0, len = elements.length; i < len; ++i) {
        if (elements[i].checked) {
            noneChecked = false;
            current = elements[i].value;
            if (confirm("Are you sure you want to delete tournament ? ") == true) {
                document.getElementById("deleteGroupBtnId").submit();
            }
            break;
        }
    }
    if (noneChecked) {
        alert("Please, select the group...")
    }
}

// Updates selected tournament
function updateSelectedGroups() {
    $('input[name=groupNameId]:checked').parents('tr').find('td[data-updatable="true"]').attr('contenteditable', true);
}
$(document).ready(function () {
    $('#updateGroupTable').find('td').blur(function () {
        var tr = $(this).parent();
        $.ajax({
            url: '/updateGroup',
            type: 'GET',
            data: {
                groupIDSelected: $('[name="groupNameId"]', tr).val(),
                groupName: $('[data-name="groupName"]', tr).text().trim()
            },
            success: function (tournament) {
                $('#updateTableElement').text(tournament);
            }
        });
    });
});


//-=-=-=-=-=-=-=-=-=-=--=-=-=-=-=-=-=-=-=-=-MEMBER-=-=-=-=-=-=-=-=-=-=--=-=-=-=-=-=-=-=-=-=-

// Deletes selected MEMBERS
function deleteSelectedMembers() {
    var elements;
    var current;
    var noneChecked = true;
    elements = document.getElementsByName("memberNameId");
    for (var i = 0, len = elements.length; i < len; ++i) {
        if (elements[i].checked) {
            noneChecked = false;
            current = elements[i].value;
            if (confirm("Are you sure you want to delete member ? ") == true) {
                document.getElementById("deleteMemberBtnId").submit();
            }
            break;
        }
    }
    if (noneChecked) {
        alert("Please, select the member...")
    }
}

// Updates selected member
function updateSelectedMember() {
    $('input[name=memberNameId]:checked').parents('tr').find('td[data-updatable="true"]').attr('contenteditable', true);
}
$(document).ready(function () {
    $('#updateMemberTable').find('td').blur(function () {
        var tr = $(this).parent();
        $.ajax({
            url: '/updateMember',
            type: 'GET',
            data: {
                memberNameId: $('[name="memberNameId"]', tr).val(),
                memberName: $('[data-name="memberName"]', tr).text().trim(),
                memberSureName: $('[data-name="memberSureName"]', tr).text().trim(),
                memberPosition: $('[data-name="memberPosition"]', tr).text().trim(),
                memberEmail: $('[data-name="memberEmail"]', tr).text().trim(),
                memberInfo: $('[data-name="memberInfo"]', tr).text().trim()
            },
            success: function (tournament) {
                $('#updateTableElement').text(tournament);
            }
        });
    });
});

//-=-=-=-=-=-=-=-=-=-=--=-=-=-=-=-=-=-=-=-=-TEAM-=-=-=-=-=-=-=-=-=-=--=-=-=-=-=-=-=-=-=-=-

// Deletes selected TEAM
function deleteSelectedTeam() {
    var elements;
    var current;
    var noneChecked = true;
    elements = document.getElementsByName("teamNameId");
    for (var i = 0, len = elements.length; i < len; ++i) {
        if (elements[i].checked) {
            noneChecked = false;
            current = elements[i].value;
            if (confirm("Are you sure you want to delete team ? ") == true) {
                document.getElementById("deleteTeamBtnId").submit();
            }
            break;
        }
    }
    if (noneChecked) {
        alert("Please, select the team...")
    }
}

// Updates selected team
function updateSelectedTeam() {
    $('input[name=teamNameId]:checked').parents('tr').find('td[data-updatable="true"]').attr('contenteditable', true);
}
$(document).ready(function () {
    $('#updateTeamTable').find('td').blur(function () {
        var tr = $(this).parent();
        $.ajax({
            url: '/updateTeam',
            type: 'GET',
            data: {
                teamNameId: $('[name="teamNameId"]', tr).val(),
                teamName: $('[data-name="teamName"]', tr).text().trim(),
                teamInfo: $('[data-name="teamInfo"]', tr).text().trim()
            },
            success: function (tournament) {
                $('#updateTableElement').text(tournament);
            }
        });
    });
});

//-=-=-=-=-=-=-=-=-=-=--=-=-=-=-=-=-=-=-=-=-MATCH-=-=-=-=-=-=-=-=-=-=--=-=-=-=-=-=-=-=-=-=-

// Deletes selected match
function deleteSelectedMatch() {
    var elements;
    var current;
    var noneChecked = true;
    elements = document.getElementsByName("matchNameId");
    for (var i = 0, len = elements.length; i < len; ++i) {
        if (elements[i].checked) {
            noneChecked = false;
            current = elements[i].value;
            if (confirm("Are you sure you want to delete match ? ") == true) {
                document.getElementById("deleteMatchBtnId").submit();
            }
            break;
        }
    }
    if (noneChecked) {
        alert("Please, select the match...")
    }
}

//Updates selected match
function updateSelectedMatch() {
    $('input[name=matchNameId]:checked').parents('tr').find('td[data-updatable="true"]').attr('contenteditable', true);
}
$(document).ready(function () {
    $('#updateMatchTable').find('td').blur(function () {
        var tr = $(this).parent();
        $.ajax({
            url: '/updateMatch',
            type: 'GET',
            data: {
                matchID: $('[data-name="matchId"]', tr).text().trim(),
                participantOneScore: $('[data-name="participantOneScore"]', tr).text().trim(),
                participantTwoScore: $('[data-name="participantTwoScore"]', tr).text().trim(),
                matchScore: $('[data-name="matchScore"]', tr).text().trim()
            },
            success: function (tournament) {
                $('#updateTableElement').text(tournament);
            }
        });
    });
});
