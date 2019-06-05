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
            <div class="col-md-6">
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
                    Latitude: <span id="latitude"><c:out value="${location.latitude}"/></span>
                </p>
                <p>
                    Longitude: <span id="longitude"><c:out value="${location.longitude}"/></span>
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
             
            <div class="col-md-6" id="map" style="width:100%; height:400px; margin-top: 20px"></div>
        </div>

        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/superhero.js"></script>
        <script async defer
                src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCxlUfGtOH3OxIYisUYXVkdqOkZccNBPx0&callback=mapIt">
        </script>
    </body>
</html>
