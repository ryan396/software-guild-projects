<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Index Page</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet"> 
        <link href="${pageContext.request.contextPath}/css/superhero.css" rel="stylesheet"> 
        <link href="https://fonts.googleapis.com/css?family=Karla&display=swap" rel="stylesheet"> 
    </head>
    <body>
        <div class="container">
            <h1>Superhero Sightings</h1>
            <hr/>
            <div class="navbar">
                <ul id="navbar" class="nav nav-tabs">
                    <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/index">Home</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/displaySightingPage">Sightings</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/displayHeroPage">Hero Information</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/displayLocationPage">Location Information</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/displayOrganizationPage">Organizations</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/displayPowerPage">Hero Powers</a></li>
                     <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <li role="presentation">
                            <a href="${pageContext.request.contextPath}/displayUserList">
                                User Admin
                            </a>
                        </li>                        
                    </sec:authorize>
                </ul>    
            </div>
            <hr>
            <p>
                Welcome to the Super Hero Sightings Application!
                <br>
                <br>
                This application is brought to you by the Hero Education and Relationship Organization (HERO).
                <br>
                <br>
                We track heroes and villain sightings across planet Earth. Help us in our mission by recording your own sightings.
            </p>
            <hr>
            <h3>Latest Sightings</h3>
            <hr>
            <div class="col-md-12">
                <table id="displayTable" class="table table-hover">
                    <tr>
                        <th width="30%">Sighting Date</th>
                        <th width="30%">Location Name</th>
                        <th width="40%">Heroes Seen</th>
                    </tr>
                    <c:forEach var="currentSighting" items="${sightingList}">
                        <tr>
                            <td>
                                <c:out value="${currentSighting.date}"/>
                            </td> 
                            <td>
                                <c:out value="${currentSighting.location.locationName}"/>
                            </td>
                            <td> 
                                |
                                <c:forEach var="currentHero" items="${currentSighting.heroes}">
                                   <c:out value="${currentHero.heroName}"/> |
                                </c:forEach>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/superhero.js"></script>

    </body>
</html>

