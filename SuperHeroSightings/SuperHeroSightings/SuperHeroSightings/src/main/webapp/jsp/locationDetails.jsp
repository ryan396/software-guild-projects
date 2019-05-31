<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Location Details</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
    </head>
    <body>
        <div class="container">
            <h1>Location Details</h1>
            <hr/>
            <p>
                Name: <c:out value="${location.locationName}"/>
            </p>
            <p>
                Street: <c:out value="${location.street}"/>
            </p>
            <p>
                City: <c:out value="${location.city}"/>
            </p>
            <p>
                Zip Code: <c:out value="${location.zipCode}"/>
            </p>
            <p>
                Latitude: <c:out value="${location.latitude}"/>
            </p>
            <p>
                Longitude: <c:out value="${location.longitude}"/>
            </p>
            <br>
            <a type="button" id="cancel-o-details-button" class="btn btn-default" href="${pageContext.request.contextPath}/displayLocationPage">
                Back
            </a>
        </div>

        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>