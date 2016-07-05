<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en">

    <head>
        <title>Icelandic Online</title>
    </head>
    <body>
        <h1>Welcome!</h1>
        <p>Do you want to create a survey or take a survey? Then you're on the right place.</p>
        <ul>
            <li>
                <a href="/survey">Click here to create or edit a survey</a>
            </li>
            <li>
                <a href="/survey/take">Click here to take a survey</a>
            </li>
            <li>
                <a href="/results/view">Click here to view Results</a>
            </li>
        </ul>
    </body>
    <footer>Class HBV501G, University of Iceland, Fall 2015</footer>
</html>
