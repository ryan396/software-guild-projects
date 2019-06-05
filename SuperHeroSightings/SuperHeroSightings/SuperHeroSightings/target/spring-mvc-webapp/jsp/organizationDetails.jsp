<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Organization Details</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">   
        <link href="https://fonts.googleapis.com/css?family=Karla&display=swap" rel="stylesheet"> 
    </head>
    <body>
        <div class="container">
            <h1>Organization Details</h1>
            <hr/>
            <p>
                Name: <c:out value="${organization.organizationName}"/>
            </p>
            <p>
                Description: <c:out value="${organization.description}"/>
            </p>
            <p>
                Street: <c:out value="${organization.street}"/>
            </p>
            <p>
                City: <c:out value="${organization.city}"/>
            </p>
            <p>
                Zip Code: <c:out value="${organization.zipCode}"/>
            </p>
            <p>
                Phone: <c:out value="${organization.phone}"/>
            </p>
            <p>
                Email: <c:out value="${organization.email}"/>
            </p>
            <hr>
            <h4>
                Current Members:
            </h4>
            <c:forEach var="currentHero" items="${heroList}">
                <ul>
                    <li>
                        <c:out value="${currentHero.heroName}"/>
                    </li>
                </ul>
            </c:forEach>
            <br>
            <a type="button" id="cancel-o-details-button" class="btn btn-default" href="${pageContext.request.contextPath}/displayOrganizationPage">
                Back
            </a>
        </div>

        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>