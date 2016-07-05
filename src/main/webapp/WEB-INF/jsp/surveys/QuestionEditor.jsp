<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<html lang="en">

    <head>
        <title>Survey Editor</title>

        <link rel="stylesheet" type="text/css" href="<c:url value="/css/survey.css"/>"/>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" />
    </head>
    <body>

        <div class="form-container gray-background">
            <h1 class="text-center blue-font">Add options</h1>
            <h3 class="text-center gray-font">${question.getQuestionText()}</h3>
            <h5 class="text-center gray-font">Of type ${question.getType()}</h5>

            <div class="box">
                <sf:form method="POST" commandName="option" action="/survey/surveyedit/${question.getSurvey().getId()}/${question.getId()}">
                    <div class="form-group">
                        <label for="optionId"> Option: </label>
                        <sf:input cssClass="form-control" id="optionId" path="optionText" type="text" placeholder="Add option"/>
                    </div>
                    <div class="form-group">
                        <label for="isCorrect">Is option correct? </label>
                        <sf:checkbox path="isCorrect" id="isCorrect"></sf:checkbox>
                    </div>
                    <div class="form-group">
                        <input class="btn btn-primary" type="submit" VALUE="Add Option"/>
                    </div>
                </sf:form>

                <sf:form method="POST" commandName="option" action="/survey/surveyedit/predoptions/${question.getSurvey().getId()}/${question.getId()}">
                    <div class="form-group">
                        <sf:select path="optionText" cssClass="form-control" for="optionText" name="optionText" >
                            <sf:option value="pred1">Yes or No</sf:option>
                            <sf:option value="pred2">True or False</sf:option>
                            <sf:option value="pred3">Easy, Medium, Hard</sf:option>
                            <sf:option value="pred4">Bad, Good, Great</sf:option>
                        </sf:select>
                    </div>
                    <div class="form-group">
                        <input class="btn btn-primary" type="submit" VALUE="Add chosen options"/>
                    </div>
                    <a href="/survey/${question.getSurvey().getId()}">Go back to question</a>
                </sf:form>
            </div>
        </div>

        <div class="form-container">
            <c:choose>
                <c:when test="${not empty question.getOptions()}">
                    <table class="surveys">
                        <tr>
                            <td>
                                <b>Option:</b>
                            </td>
                            <td>
                                <b>Delete option:</b>
                            </td>
                            <c:choose>
                                <c:when test="${question.getSurvey().getIsTest()}">
                                    <td>
                                        <b>Option is correct:</b>
                                    </td>
                                </c:when>
                            </c:choose>
                        </tr>
                        <c:forEach var="option" items="${question.getOptions()}">
                            <td>
                                <p>${option.getOptionText()}</p>
                            </td>
                            <td>
                                <form method = "post" action = "/survey/surveyedit/delete/${option.getQuestion().getSurvey().getId()}/${option.getQuestion().getId()}/${option.getId()}">
                                    <input class="btn btn-default" type="submit" value="Delete">
                                </form>
                            </td>
                            <c:choose>
                                <c:when test="${question.getSurvey().getIsTest()}">
                                    <td>
                                        <p>
                                        <c:choose>
                                            <c:when test="${option.getIsCorrect()}">
                                                Correct
                                            </c:when>
                                            <c:otherwise>
                                                Incorrect
                                            </c:otherwise>
                                        </c:choose>
                                        </p>
                                    </td>
                                </c:when>
                            </c:choose>

                            </tr>
                        </c:forEach>
                    </table>
                </c:when>

                <%--If all tests are false, then do this--%>
                <c:otherwise>
                    <h3>No options!</h3>
                </c:otherwise>
            </c:choose>
        </div>
    </body>
</html>

