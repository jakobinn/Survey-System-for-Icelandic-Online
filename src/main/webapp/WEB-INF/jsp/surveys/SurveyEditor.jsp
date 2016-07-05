<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<html lang="en">

<head>
    <title>Survey Editor</title>

    <link rel="stylesheet" type="text/css" href="<c:url value="/css/survey.css"/>" />
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" />
</head>
    <body>

        <div class="form-container gray-background">
            <h1 class="text-center blue-font">Add Questions</h1>
            <h3 class="text-center gray-font">${survey.getName()}</h3>
            <h5 class="text-center gray-font">By ${survey.getAuthor()}</h5>

            <div class="box">
                <sf:form method="POST" commandName="question" action="/survey/surveyedit/${survey.getId()}">

                    <div class="form-group">
                        <label for="questionId"> Question: </label>
                        <sf:input id="questionId" cssClass="form-control" path="questionText" type="text" placeholder="Add question"/>
                    </div>
                    <div class="form-group">
                        <label for="questionType">Question type:</label>
                        <sf:select id="questionType" cssClass="form-control" for="questionType" path="type" >
                            <sf:option value="dropdown">Drop down</sf:option>
                            <sf:option value="checkbox">Multiple answer</sf:option>
                            <sf:option value="input">Input box</sf:option>
                            <sf:option value="radio">Radio button</sf:option>
                        </sf:select>
                    </div>
                    <c:choose>
                        <c:when test="${survey.getIsTest()}">
                            <label for="weight">Question weight:</label>
                            <sf:input id="weight" cssClass="form-control" path="weight" type="text" placeholder="enter weight"></sf:input>
                        </c:when>
                    </c:choose>
                    <div class="form-group">
                        <input class="btn btn-primary" type="submit" VALUE="Add Question"/>
                    </div>
                    <a href="/survey">No more Questions</a><br>
                    <a href="/result/">View results</a>
                </sf:form>
            </div>
        </div>

        <div class="form-container">
            <c:choose>
                <%--If the model has an attribute with the name `surveys`--%>
                <c:when test="${not empty survey.getQuestions()}">
                    <table class="surveys">
                        <tr>
                            <td>
                                <b>Question:</b>
                            </td>
                            <td>
                                <b>Question type:</b>
                            </td>
                            <td>
                                <b>Delete:</b>
                            </td>
                        </tr>
                        <c:forEach var="question" items="${questions}">
                            <tr>
                                <td>
                                    <c:choose>
                                        <c:when test="${question.getType() == 'input'}">
                                            <p>${question.getQuestionText()}</p>
                                        </c:when>
                                        <c:otherwise>
                                            <a href="/survey/surveyedit/${question.getSurvey().getId()}/${question.getId()}">${question.getQuestionText()}</a>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>${question.type}</td>
                                <td>
                                    <form method = post action = "/survey/surveyedit/delete/${question.getSurvey().getId()}/${question.getId()}">
                                        <input class="btn btn-default" type="submit" value="Delete">
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:when>

                <%--If all tests are false, then do this--%>
                <c:otherwise>
                    <h3>No questions!</h3>
                </c:otherwise>
            </c:choose>
        </div>

    </body>
</html>