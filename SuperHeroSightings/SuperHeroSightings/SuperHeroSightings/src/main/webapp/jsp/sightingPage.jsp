<%-- 
    Document   : SightingPage
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
        <title>Sightings</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet"> 
        <link href="${pageContext.request.contextPath}/css/superhero.css" rel="stylesheet"> 

    </head>
    <body>
        <div class="container">
            <h2>Sightings</h2>
            <hr>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation"><a href="${pageContext.request.contextPath}/index">Home</a></li>
                    <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/displaySightingPage">Sightings</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/displayHeroPage">Hero Information</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/displayLocationPage">Location Information</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/displayPowerPage">Hero Powers</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/displayLoginPage">Login</a></li>
                </ul>    
            </div>
            <hr>
            <div id="topBar" class="row">
                <div class="col-md-3">
                    <a href="${pageContext.request.contextPath}/displayAddSightingPage" type="button" id="add-sighting-button" class="btn btn-default">
                        Record Sighting
                    </a>
                </div>
                <form role="form" id="search-form" method="POST" 
                      action="searchByDate">
                    <div class="col-md-2">
                        <button type="submit" id="search-button" class="btn btn-default">
                            Search by Date
                        </button>
                    </div>
                    <div class="col-md-2">
                        <input type="date" class="form-control" name="date" id="date-input" placeholder="" required/>
                    </div>
                     <div class="col-md-3">
                        <a href="${pageContext.request.contextPath}/displaySightingPage" id="reset-button" class="btn btn-default">
                            Reset Search
                        </a>
                    </div>
                </form>
            </div>
            <hr>
            <div class="col-md-12">
                <table id="displayTable" class="table table-hover">
                    <tr>
                        <th width="30%">Sighting Date</th>
                        <th width="30%">Location Name</th>
                        <th width="10%"></th>
                        <th width="10%"></th>
                    </tr>
                    <c:forEach var="currentSighting" items="${sightingList}">
                        <tr>
                            <td>
                                <a href="displaySightingDetails?sightingId=${currentSighting.sightingId}">
                                    <c:out value="${currentSighting.date}"/>
                                </a>
                            </td> 
                            <td>
                                <c:out value="${currentSighting.location.locationName}"/>
                            </td>
                            <td>
                                <a href="displayEditSightingPage?sightingId=${currentSighting.sightingId}">
                                    Edit
                                </a>
                            </td>
                            <td>
                                <a href="deleteSighting?sightingId=${currentSighting.sightingId}">
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
        <script src="${pageContext.request.contextPath}/js/superhero.js"></script>
    </body>
</html>
