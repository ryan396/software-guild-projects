<%-- 
    Document   : addlocationPage
    Created on : May 30, 2019, 1:34:18 PM
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
        <title>Add Hero</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">  
        <link href="https://fonts.googleapis.com/css?family=Karla&display=swap" rel="stylesheet"> 
    </head>
    <body>
        <div class="col-md-6">
            <h2>Add New Hero</h2>
            <hr>
            <form class="form-horizontal" 
                  role="form" method="POST" 
                  action="addHero">
                <div class="form-group">
                    <label for="add-hero-name" class="col-md-4 control-label">Name:</label>
                    <div class="col-md-8">
                        <input type="text" size="50" class="form-control" name="heroName" placeholder="Name" required/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-description" class="col-md-4 control-label">Description:</label>
                    <div class="col-md-8">
                        <input type="text" size="250" class="form-control" name="description" placeholder="Description" required/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-powers" class="col-md-4 control-label">Powers:</label>
                    <div class="col-md-8">
                        <div class="form-check">
                            <select class="selectpicker" multiple data-live-search="true" name="powerList" required>
                                <c:forEach items="${powerList}" var="currentPower">
                                    <option value="${currentPower.powerId}">${currentPower.powerDescription}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-organizations" class="col-md-4 control-label">Organization:</label>
                    <div class="col-md-8">
                        <div class="form-check">
                            <select class="selectpicker" multiple data-live-search="true" name="organizationList">
                                <c:forEach items="${organizationList}" var="currentOrganization">
                                    <option value="${currentOrganization.organizationId}">
                                        ${currentOrganization.organizationName}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-4">
                        <div class="form-group">
                            <div class="col-md-offset-6 col-md-2">
                                <a type="button" id="cancel-add-button" class="btn btn-default" href="${pageContext.request.contextPath}/displayHeroPage">
                                    Cancel
                                </a>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="form-group">
                            <div class="col-md-offset-12 col-md-2">
                                <button type="submit" id="add-hero-button" class="btn btn-default">
                                    Add Hero
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
