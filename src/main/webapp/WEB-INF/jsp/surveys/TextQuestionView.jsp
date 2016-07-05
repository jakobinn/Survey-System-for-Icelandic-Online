<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<html lang="en">

<head>
    <title>Viewing text question responses</title>

    <link rel="stylesheet" type="text/css" href="<c:url value="/css/survey.css"/>" />
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" />
</head>
<body>
<div class="container gray-background">
<c:choose>
    <c:when test="${not empty question.getResults()}">
        <h2 class="text-center blue-font">Responses for ${question.getQuestionText()}</h2>
        <c:forEach var="result" items="${question.getResults()}">
            <p>${result.getText()}</p>
        </c:forEach>
    </c:when>
    <c:otherwise>
        <p class="text-center">No responses.</p>
    </c:otherwise>
</c:choose>
</div>
</body>
</html>