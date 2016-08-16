<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Add tournament form</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="css/custom/bootstrap.min.css" rel="stylesheet">
    <%--<link href="http://netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet">--%>
    <link href="css/style.css" rel="stylesheet">

    <script src="js/custom.js"></script>
    <script src="vendor/jquery/jquery.min.js"></script>
</head>

<body class="backgroundTournament">
<div class="container">

    <form action="add" class="form-horizontal" role="form" method="get">
        <h2>New Tournament</h2>
        <hr>

        <%--Tournament Name--%>
        <div class="form-group">
            <label for="name" class="col-sm-3 control-label">Name</label>
            <div class="col-sm-9">
                <input type="text" id="name" name="name" placeholder="Tournament Name"
                       class="form-control" autofocus required >
            </div>
        </div>

        <%--StartDate--%>
        <div class="form-group">
            <label for="startDate" class="col-sm-3 control-label">Start Time</label>
            <div class="col-sm-9">
                <input type="text" id="startDate" placeholder="Start Time" class="form-control "pattern="^[ 0-9]+$">
                <span class="help-block">eg.: 2020-07-10 10:00:00 </span>
            </div>
        </div>

        <%--EndDate--%>
        <div class="form-group">
            <label for="endDate" class="col-sm-3 control-label">End time</label>
            <div class="col-sm-9">
                <input type="text" id="endDate" placeholder="End time" class="form-control">
                <span class="help-block">eg.: 2020-07-10 10:00:00 </span>
            </div>
        </div>

        <%--Location--%>
        <div class="form-group">
            <label for="location" class="col-sm-3 control-label">Location</label>
            <div class="col-sm-9">
                <input type="text" id="location" name="location" placeholder="Location" class="form-control" required>
            </div>
        </div>

        <%--Description--%>
        <div class="form-group">
            <label  class="col-sm-3 control-label">Description</label>
            <div class="col-sm-9">
                <textarea rows="5" cols="20" name="tournament_description" id="field2" class="form-control" required="required"></textarea>
            </div>
        </div>

        <%--TournamentFormat--%>
        <div class="form-group">
            <label for="formatId" class="col-sm-3 control-label">Format</label>
            <div class="col-sm-9">
                <select id="formatId" name="format" class="form-control" onchange="selectElement()">
                    <%--<option value="0" selected="selected">Select format</option>--%>
                    <option value="1">Round_Robin</option>
                    <option value="2">Olympic</option>
                </select>
            </div>
        </div>
        <!-- /.form-group -->
        <div class="form-group">
            <div class="col-sm-9 col-sm-offset-3">
                <input type="submit" class="btn btn-danger" value="Save and continue">
            </div>
        </div>
    </form>
    <!-- /form -->
</div>
<!-- ./container -->
</body>
</html>