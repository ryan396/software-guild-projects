<%-- 
    Document   : addPowerPage
    Created on : May 29, 2019, 8:36:43 PM
    Author     : rianu
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>Record Sighting</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Karla&display=swap" rel="stylesheet"> 
    </head>
    <body>
        <div class="col-md-6">
            <h2>Record Sighting</h2>
            <hr>
            <form class="form-horizontal" 
                  role="form" method="POST" 
                  action="addSighting">
                <div class="form-group">
                    <label for="add-date" class="col-md-4 control-label">Date:</label>
                    <div class="col-md-8">
                        <input type="date" class="form-control" name="date" placeholder="" required/>
                    </div>
                </div>
                   <div class="form-group">
                    <label for="add-location" class="col-md-4 control-label">Location:</label>
                    <div class="col-md-8">
                        <div class="form-check">
                            <select class="selectpicker" data-live-search="true" name="locationList" required>
                                <c:forEach items="${locationList}" var="currentLocation">
                                    <option value="${currentLocation.locationId}">${currentLocation.locationName}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>
                 <div class="form-group">
                    <label for="add-heroes" class="col-md-4 control-label">Heroes Seen:</label>
                    <div class="col-md-8">
                        <div class="form-check">
                            <select class="selectpicker" multiple data-live-search="true" name="heroList" required>
                                <c:forEach items="${heroList}" var="currentHero">
                                    <option value="${currentHero.heroId}">${currentHero.heroName}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group">
                            <div class="col-md-offset-8 col-md-2">
                                <a type="button" id="cancel-add-s-button" class="btn btn-default" href="${pageContext.request.contextPath}/displaySightingPage">
                                    Cancel
                                </a>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="form-group">
                            <div class="col-md-offset-6 col-md-2">
                                <button type="submit" id="add-sr-button" class="btn btn-default">
                                    Submit
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
