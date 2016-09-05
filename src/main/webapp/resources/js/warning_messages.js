function dangerMsg() {
    var type = BootstrapDialog.TYPE_DANGER;
    BootstrapDialog.show({
        type: type,
        size: BootstrapDialog.SIZE_LARGE,
        message: 'Are you sure delete?',
        buttons: [{
            label: 'Button 1'
        }, {
            label: 'Button 2',
            cssClass: 'btn-primary',
            action: function () {
                alert('Hi Orange!');
            }
        }, {
            icon: 'glyphicon glyphicon-ban-circle',
            label: 'Button 3',
            cssClass: 'btn-warning'
        }, {
            label: 'Close',
            action: function (dialogItself) {
                dialogItself.close();
            }
        }]
    });
}