function submitForm() {
    var searchStr = document.getElementById("searchStr").value;

    if (searchStr != null) {
        document.forms["loginForm"].submit();
    }
}