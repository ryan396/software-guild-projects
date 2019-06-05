<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Hero Details</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">  
        <link href="https://fonts.googleapis.com/css?family=Karla&display=swap" rel="stylesheet"> 
    </head>
    <body>
        <div class="container">
            <h1>Hero Details</h1>
            <hr/>
            <p>
                Name: <c:out value="${hero.heroName}"/>
            </p>
            <p>
                Description: <c:out value="${hero.description}"/>
            </p>
            <hr>
            <h4>
                Super Powers:
            </h4>
            <c:forEach var="currentPower" items="${powerList}">
                <ul>
                    <li>
                        <c:out value="${currentPower.powerDescription}"/>
                    </li>
                </ul>
            </c:forEach>
            <br>
            <hr>
            <h4>
                Current Organizations:
            </h4>
            <c:forEach var="currentOrganization" items="${organizationList}">
                <ul>
                    <li>
                        <c:out value="${currentOrganization.organizationName}"/>
                    </li>
                </ul>
            </c:forEach>
            <br>
             <br>
            <hr>
            <h4>
                Locations seen:
            </h4>
            <c:forEach var="currentLocation" items="${locationList}">
                <ul>
                    <li>
                        <c:out value="${currentLocation.locationName}"/>
                    </li>
                </ul>
            </c:forEach>
            <br>
            <a type="button" id="cancel-h-details-button" class="btn btn-default" href="${pageContext.request.contextPath}/displayHeroPage">
                Back
            </a>
        </div>

        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>