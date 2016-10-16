<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Tag Example</title>

    <!-- Bootstrap Core CSS -->
    <%--<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css"/>">--%>

    <%--<!-- Custom CSS -->--%>
    <link rel="stylesheet" href="<c:url value="/resources/css/blog-home.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/creative.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/custom.css"/>">


    <![endif]-->
</head>
<body>

<table class="table">
    <tr class="thCustom">

    <%--<th>No</th>--%>
        <th>Check</th>
        <th>No</th>
        <th>Name</th>
        <th width="5%">StartDate</th>
        <th width="5%">EndDate</th>
        <th>Location</th>
        <th width="35%">Description</th>
        <th>Format</th>
        <th>Owner</th>
    </tr>
    <c:forEach items="${tournamentList}" var="tournament" >
    <tr>
        <td >
            <input type="radio" id="${tournament}" class="checkbox-custom" name="tournamentNameId" value="" required/>
        </td>
        <td><c:out value="${tournament.tournamentName}"/></td>
        <td><c:out value="${tournament.startDate}"/></td>
        <td><c:out value="${tournament.endDate}"/></td>
        <td><c:out value="${tournament.location}"/></td>
        <td><c:out value="${tournament.tournamentDescription}"/></td>
        <td><c:out value="${tournament.tournamentFormatId}"/></td>
        <td><c:out value="${tournament.managerId}"/></td>
    </tr>
    </c:forEach>
</table>

</body>
</html>