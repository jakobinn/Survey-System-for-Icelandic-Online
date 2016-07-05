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
        <h1 class="text-center">Survey results</h1>
        <div class="form-container">
            <c:choose>
                <c:when test="${not empty questions}">
                    <table class="surveys">
                        <tr>
                            <td>
                                Question:
                            </td>
                            <td>
                                Total times answered:
                            </td>
                            <td>
                                options:% times picked
                            </td>
                        </tr>
                        <c:forEach var="question" items="${questions}" varStatus="questionCounter">
                            <tr>
                                <td>
                                    <p>${question.getQuestionText()}</p>
                                </td>
                                <td>
                                    <p>${totalAnswersPerQuestion.get(questioncounter.status)}</p>
                                </td>
                                <c:choose>
                                    <c:when test="${question.getType() == 'input'}">
                                        <td>
                                        <a href="/results/view/${question.getSurvey().getId()}/${question.getId()}">View responses</a>
                                        </td>
                                    </c:when>
                                    <c:when test="${not empty question.getOptionCounts()}">
                                        <c:forEach var="optionCount" items="${question.getOptionCounts()}">
                                            <td>
                                                ${optionCount.key}: ${optionCount.value/question.getTimesAnswered()*100}%
                                            </td>
                                        </c:forEach>
                                    </c:when>
                                    <c:otherwise>

                                    </c:otherwise>
                                </c:choose>
                            </tr>
                        </c:forEach>
                    </table>
                </c:when>
                <c:otherwise>
                    <p>Nothing to display</p>
                </c:otherwise>
            </c:choose>
        </div>
    </body>
</html>