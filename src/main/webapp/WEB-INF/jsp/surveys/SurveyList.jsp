<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<html lang="en">

    <head>
        <title>Survey Creator</title>
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/survey.css"/>"/>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" />
    </head>
    <body>
        <h1>Available Surveys</h1>
        <p>Please, click on a survey you would like to take:</p>

        <%--View created for users to see a list of surveys that they can take,
        but do not have permission to create a survey--%>
        <div>
            <c:choose>
                <c:when test="${not empty surveys}">
                    <table class="surveys">
                        <c:forEach var="survey" items="${surveys}">
                            <tr>
                                <td>
                                    <a href="/survey/take/${survey.getId()}">${survey.getName()}</a>
                                </td>
                                <td>${survey.getAuthor()}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:when>
                <c:otherwise>
                    <h3>There are no surveys available</h3>
                    <a href="/survey">Click here to create a survey!</a>
                </c:otherwise>
            </c:choose>
        </div>
    </body>
</html>
