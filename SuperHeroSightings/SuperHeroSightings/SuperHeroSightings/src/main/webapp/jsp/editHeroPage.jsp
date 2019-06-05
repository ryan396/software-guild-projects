<%-- 
    Document   : addlocationPage
    Created on : May 30, 2019, 1:34:18 PM
    Author     : rianu
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>Edit Hero Page</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">  
        <link href="https://fonts.googleapis.com/css?family=Karla&display=swap" rel="stylesheet"> 
    </head>
    <body>
        <div class="col-md-6">
            <h2>Edit Hero</h2>
            <hr>
            <sf:form class="form-horizontal" 
                     modelAttribute="hero" role="form" method="POST" 
                     action="editHero">
                <div class="form-group">
                    <label for="add-hero-name" class="col-md-4 control-label">Name:</label>
                    <div class="col-md-8">
                        <sf:input type="text" size="50" class="form-control" path="heroName" placeholder="Name" required="required"/>
                        <sf:errors path="heroName" cssclass="error"></sf:errors>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-description" class="col-md-4 control-label">Description:</label>
                        <div class="col-md-8">
                        <sf:input type="text" size="50" class="form-control" path="description" placeholder="Description" required="required"/>
                        <sf:errors path="description" cssclass="error"></sf:errors>
                        <sf:hidden path="heroId"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-powers" class="col-md-4 control-label">Powers:</label>
                        <div class="col-md-8">
                            <div class="form-check">
                                <select class="selectpicker" multiple data-live-search="true" name="powerList" required="required">
                                <c:forEach items="${powerList}" var="currentPower">
                                    <option value="${currentPower.powerId}">${currentPower.powerDescription}</option>
                                </c:forEach>
                            </select>
                        </div>   
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-organizations" class="col-md-4 control-label">Organizations:</label>
                    <div class="col-md-8">
                        <div class="form-check">
                            <select class="selectpicker" multiple data-live-search="true" name="organizationList" required="required">
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
                                    Edit Hero
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </sf:form>
        </div> 
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
