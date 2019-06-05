<%-- 
    Document   : addPowerPage
    Created on : May 29, 2019, 8:36:43 PM
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
        <title>Add Power</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Karla&display=swap" rel="stylesheet"> 
    </head>
    <body>
        <div class="col-md-6">
            <h2>Edit Power</h2>
            <hr>
            <sf:form class="form-horizontal" 
                  role="form" modelAttribute="power" action="editPower" method="POST">
                <div class="form-group">
                    <label for="add-power-description" class="col-md-3 control-label">Power Description:</label>
                    <div class="col-md-8">
                        <sf:input type="text" class="form-control" path="powerDescription" placeholder="Power Description"/>
                        <sf:errors path="powerDescription" cssclass="error"></sf:errors>
                        <sf:hidden path="powerId"/>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-2">
                        <div class="form-group">
                            <div class="col-md-4 col-md-2">
                                <a type="button" id="cancel-add-power-button" class="btn btn-default" href="${pageContext.request.contextPath}/displayPowerPage">
                                    Cancel
                                </a>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-2">
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-2">
                                <button type="submit" id="add-power-button" class="btn btn-default">
                                    Submit
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
