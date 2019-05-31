<%-- 
    Document   : LocationPage
    Created on : May 29, 2019, 7:42:16 PM
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
        <title>Location Information</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet"> 

    </head>
    <body>
        <div class="container">
            <h2>Location Information</h2>
            <hr>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/index.jsp">Home</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/displaySightingPage">Record A Sighting</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/displayHeroPage">Hero Information</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/displayLocationPage">Location Information</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/displayOrganizationPage">Organizations</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/displayPowerPage">Hero Powers</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/displayLoginPage">Login</a></li>
                </ul>    
            </div>
            <hr>
            <div id="topBar" class="row">
                <div class="col-md-3">
                    <a href="${pageContext.request.contextPath}/displayAddLocationPage" type="button" id="add-location-button" class="btn btn-default">
                        Add Location
                    </a>
                </div>
            </div>
            <hr>
            <div class="col-md-12">
                <table id="locationTable" class="table table-hover">
                    <tr>
                        <th width="30%">Location Name</th>
                        <th width="20%">City</th>
                        <th width="20%">Zip Code</th>
                        <th width="10%"></th>
                        <th width="10%"></th>
                    </tr>
                    <c:forEach var="currentLocation" items="${locationList}">
                        <tr>
                            <td>
                                <a href="displayLocationDetails?locationId=${currentLocation.locationId}">
                                    <c:out value="${currentLocation.locationName}"/>
                                </a>
                            </td>

                            <td>
                                <c:out value="${currentLocation.city}"/>
                            </td>
                            <td>
                                <c:out value="${currentLocation.zipCode}"/>
                            </td>
                            <td>
                                <a href="displayEditLocationPage?locationId=${currentLocation.locationId}">
                                    Edit
                                </a>
                            </td>
                            <td>
                                <a href="deleteLocation?locationId=${currentLocation.locationId}">
                                    Delete
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
