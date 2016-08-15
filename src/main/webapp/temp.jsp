<!DOCTYPE html>

<html>
<head>
    <title>TestForm</title>
</head>
<link href="aaa/css/style.css" rel="stylesheet"/>
<link href="aaa/css/uniform.css" rel="stylesheet"/>

<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.5.2/jquery.min.js"></script>
<%--<script type="text/javascript" src="js/jquery-3.1.0.js"></script>--%>
<script type="text/javascript" src="aaa/js/jquery.tools.js"></script>
<script type="text/javascript" src="aaa/js/jquery.uniform.min.js"></script>
<script type="text/javascript" src="aaa/js/main.js"></script>


<body>
<div class="TTWForm-container">
    <div class="TTWForm-wrapper">

        <form action="add" class="TTWForm clearfix" method="post" novalidate="">

            <div id="field1-container" class="field f_100">
                <label for="field1">Name</label>
                <input name="nameTournament" id="field1" required="required" type="text">
            </div>

            <div class="field f_100">
                <label for="field1">Location</label>
                <input name="location" required="required" type="text">
            </div>


            <div id="field3-container" class="field f_100">
                <label for="field3">
                    Select
                </label>
                <select name="field3" id="field3" required="required">
                    <option id="field3-1" value="Option 1">
                        Option 1
                    </option>
                    <option id="field3-2" value="Option 2">
                        Option 2
                    </option>
                    <option id="field3-3" value="Option 3">
                        Option 3
                    </option>
                </select>
            </div>

            <div id="form-submit" class="field f_100 clearfix submit">
                <input type="submit" value="Submit" >
            </div>
        </form>
    </div>
</div>

</body>
</html>