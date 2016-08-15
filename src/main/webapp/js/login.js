$('.form').find('input, textarea').on('keyup blur focus', function (e) {

    var $this = $(this),
        label = $this.prev('label');

    if (e.type === 'keyup') {
        if ($this.val() === '') {
            label.removeClass('active highlight');
        } else {
            label.addClass('active highlight');
        }
    } else if (e.type === 'blur') {
        if ($this.val() === '') {
            label.removeClass('active highlight');
        } else {
            label.removeClass('highlight');
        }
    } else if (e.type === 'focus') {

        if ($this.val() === '') {
            label.removeClass('highlight');
        }
        else if ($this.val() !== '') {
            label.addClass('highlight');
        }
    }
});

//Select active tab
$('.tab a').on('click', function (e) {
    e.preventDefault();
    loadTab(this);
});

$(document).ready(function () {
    // debugger;
    loadTab($('div.form').find('li.active>a'));
});

function loadTab(param) {
    $(param).parent().addClass('active');
    $(param).parent().siblings().removeClass('active');

    target = $(param).attr('href');

    $('.tab-content > div').not(target).hide();
    $(target).fadeIn(600);
}