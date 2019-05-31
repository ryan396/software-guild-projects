<%-- 
    Document   : addlocationPage
    Created on : May 30, 2019, 1:34:18 PM
    Author     : rianu
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- Directive for Spring Form tag libraries -->
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>Add Location Page</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">  
    </head>
    <body>
        <div class="col-md-6">
            <h2>Add New Location</h2>
            <hr>
            <sf:form class="form-horizontal" modelAttribute="location"
                     role="form" method="POST" 
                     action="editLocation">
                <div class="form-group">
                    <label for="add-location-name" class="col-md-4 control-label">Name:</label>
                    <div class="col-md-8">
                        <sf:input type="text" class="form-control" path="locationName" placeholder="Name"/>
                        <sf:errors path="locationName" cssclass="error"></sf:errors>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-street" class="col-md-4 control-label">Street:</label>
                        <div class="col-md-8">
                        <sf:input type="text" class="form-control" path="street" placeholder="Street"/>
                        <sf:errors path="street" cssclass="error"></sf:errors>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-city" class="col-md-4 control-label">City:</label>
                        <div class="col-md-8">
                        <sf:input type="text" class="form-control" path="city" placeholder="City"/>
                        <sf:errors path="city" cssclass="error"></sf:errors>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-zip-code" class="col-md-4 control-label">Zip Code:</label>
                        <div class="col-md-8">
                        <sf:input type="text" class="form-control" path="zipCode" placeholder="Zip Code"/>
                        <sf:errors path="zipCode" cssclass="error"></sf:errors>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-latitude" class="col-md-4 control-label">Latitude:</label>
                        <div class="col-md-8">
                        <sf:input type="text" class="form-control" path="latitude" placeholder="Latitude"/>
                        <sf:errors path="latitude" cssclass="error"></sf:errors>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-longitude" class="col-md-4 control-label">Longitude:</label>
                        <div class="col-md-8">
                        <sf:input type="text" class="form-control" path="longitude" placeholder="Longitude"/>
                        <sf:errors path="longitude" cssclass="error"></sf:errors>
                        <sf:hidden path="locationId"/>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-4">
                            <div class="form-group">
                                <div class="col-md-offset-6 col-md-2">
                                    <a type="button" id="cancel-add-button" class="btn btn-default" href="${pageContext.request.contextPath}/displayLocationPage">
                                    Cancel
                                </a>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="form-group">
                            <div class="col-md-offset-12 col-md-2">
                                <button type="submit" id="add-location-button" class="btn btn-default">
                                    Edit Location
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
