<%-- 
    Document   : sightingDetails
    Created on : Jun 3, 2019, 1:42:44 PM
    Author     : rianu
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>Sighting Details</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">  
    </head>
    <body>
        <div class="container">
            <h1>Sighting Details</h1>
            <hr/>
            <p>
                Sighting Date: <c:out value="${sighting.date}"/>
            </p>
            <p>
                Name: <c:out value="${location.locationName}"/>
            </p>
            <p>
                Street: <c:out value="${location.street}"/>
            </p>
            <p>
                City: <c:out value="${location.city}"/>
            </p>
            <p>
                Zip Code: <c:out value="${location.zipCode}"/>
            </p>
            <p>
                Latitude: <c:out value="${location.latitude}"/>
            </p>
            <p>
                Longitude: <c:out value="${location.longitude}"/>
            </p>
            <hr>
            <h4>
                Heroes Seen:
            </h4>
            <c:forEach var="currentHero" items="${heroList}">
                <ul>
                    <li>
                        <c:out value="${currentHero.heroName}"/>
                    </li>
                </ul>
            </c:forEach>
            <br>
            <a type="button" id="cancel-s-details-button" class="btn btn-default" href="${pageContext.request.contextPath}/displaySightingPage">
                Back
            </a>
        </div>

        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
