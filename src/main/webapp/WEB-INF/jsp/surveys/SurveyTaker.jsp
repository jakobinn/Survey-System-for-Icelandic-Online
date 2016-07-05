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

        <h1>${survey.getName()}</h1>
        <p>by ${survey.getAuthor()}</p>

        <c:choose>
            <%--If the model has an attribute with the name `surveys`--%>
            <c:when test="${not empty questions}">
                <sf:form method="POST" commandName="ResultWrapper" action="/survey/take/${survey.getId()}">
                    <table class="surveys">
                        <c:forEach var="question" items="${questions}" varStatus="questionCounter">
                            <tr>
                                <td>
                                    <p>${question.getQuestionText()}<p>
                                </td>
                                <td>
                                    ${question.type}
                                </td>
                                <td>
                                    <c:choose>
                                        <%--Dropdown options--%>
                                        <c:when test="${question.getType() == 'dropdown'}">
                                            <sf:select path="idHolders[${questionCounter.index}].optionIds[0]">
                                                <option value="Select answer">${"Select answer"}</option>
                                                <c:forEach var="option" items="${question.getOptions()}" varStatus="optionCounter">
                                                    <p>${option.getId()}</p>
                                                    <sf:option value="${option.getId()}">${option.getOptionText()}</sf:option>
                                                </c:forEach>
                                            </sf:select>
                                        </c:when>

                                        <%--Multiple answer question with radio buttons or checkbox --%>
                                        <c:when test="${question.getType() == 'checkbox'}">
                                            <c:forEach var="option" items="${question.getOptions()}" varStatus="optionCounter">
                                                    <sf:checkbox path="idHolders[${questionCounter.index}].optionIds[${optionCounter.index}]" value="${option.getId()}" />
                                                    ${option.getOptionText()}
                                                <br>
                                            </c:forEach>
                                        </c:when>
                                        <c:when test="${question.getType() == 'radio'}">
                                            <c:forEach var="option" items="${question.getOptions()}" varStatus="optionCounter">
                                                <label>
                                                    <sf:radiobutton path="idHolders[${questionCounter.index}].optionIds[${optionCounter.index}]" value = "${option.getId()}" />${option.getOptionText()}
                                                </label>
                                            </c:forEach>
                                        </c:when>

                                        <%--Input option--%>
                                        <c:when test="${question.getType() == 'input'}">
                                            <sf:input path="idHolders[${questionCounter.index}].text" placeholder="Enter answer" type="text" name="${option.getId()}" />
                                        </c:when>
                                        <c:otherwise>
                                            <p> No options </p>
                                        </c:otherwise>
                                    </c:choose>
                                    <sf:hidden path="idHolders[${questionCounter.index}].questionId" value="${question.getId()}"/>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                    <input type="submit" VALUE="Post your answers!"/>
                </sf:form>
            </c:when>

            <%--If all tests are false, then do this--%>
            <c:otherwise>
                <h3>No questions!</h3>
            </c:otherwise>
        </c:choose>
    </body>
</html>
