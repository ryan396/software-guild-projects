<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>SuperHero Sightings</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet"> 
        <link href="${pageContext.request.contextPath}/css/superhero.css" rel="stylesheet"> 
        <link href="https://fonts.googleapis.com/css?family=Karla&display=swap" rel="stylesheet">        
    </head>
    <body>
        <div class="container">
            <h1>Add User Form</h1>
            <hr>
            <form class="form-horizontal" method="POST" action="addUser">
                <div class="form-group">
                    <label for="username" class="col-md-1 control-label">Username: </label>
                    <div class="col-md-6">
                        <input type="text" class="form-control"
                               name="username"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="password" class="col-md-1 control-label">Password: </label>
                    <div class="col-md-6">
                        <input type="password" class="form-control" 
                               name="password"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-6 checkbox">
                        <label><input type="checkbox" 
                                      name="isAdmin" value="yes"/>Is Admin?</label>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-4">
                        <div class="form-group">
                            <div class="col-md-offset-6 col-md-2">
                                <a type="button" id="cancel-add-button" class="btn btn-default" href="${pageContext.request.contextPath}/displayUserList">
                                    Cancel
                                </a>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="form-group">
                            <input class="btn btn-default" type="submit" value="Add User"/><br/>
                        </div>
                    </div>
                </div>
        </div>
    </form>
</div>
<!-- Placed at the end of the document so the pages load faster -->
<script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

</body>
</html>