<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<html lang="en">

<head>
    <title>Viewing A Result</title>

    <link rel="stylesheet" type="text/css" href="<c:url value="/css/survey.css"/>" />
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" />
</head>
<body>
<div class="container gray-background">
    <h1 class="text-center blue-font">Results</h1>
    <h2 class="text-center blue-font">Test: ${survey.getName()}</h2>
    <c:choose>
        <c:when test="${survey.getIsTest()}">
            <p class="text-center">You scored: ${sumPickedWeight/survey.getTotalWeight()*100}%</p>
        </c:when>
        <c:otherwise>
            <p class="text-center">Survey Submitted</p>
        </c:otherwise>
    </c:choose>
    <a href="/">Home</a>
</div>
</body>
</html>