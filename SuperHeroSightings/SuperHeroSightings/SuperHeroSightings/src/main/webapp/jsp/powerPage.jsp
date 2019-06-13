<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Hero Powers</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">      
        <link href="${pageContext.request.contextPath}/css/superhero.css" rel="stylesheet"> 
        <link href="https://fonts.googleapis.com/css?family=Karla&display=swap" rel="stylesheet"> 
    </head>
    <body>
        <div class="container">
            <h2>Hero Powers</h2>
            <hr>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation"><a href="${pageContext.request.contextPath}/index">Home</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/displaySightingPage">Sightings</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/displayHeroPage">Hero Information</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/displayLocationPage">Location Information</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/displayOrganizationPage">Organizations</a></li>
                    <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/displayPowerPage">Hero Powers</a></li>
                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <li role="presentation">
                            <a href="${pageContext.request.contextPath}/displayUserList">
                                User Admin
                            </a>
                        </li>                        
                    </sec:authorize>
                </ul>    
            </div>
            <c:if test="${pageContext.request.userPrincipal.name != null}">
                <p>Hello : ${pageContext.request.userPrincipal.name}
                    | <a href="<c:url value="/j_spring_security_logout" />" > Logout</a>
                </p>
            </c:if>
            <hr>
            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <div id="topBar" class="row">
                    <div class="col-md-3">
                        <a href="${pageContext.request.contextPath}/displayAddPowerPage" type="button" id="add-power-button" class="btn btn-default">
                            Add Power
                        </a>
                    </div>
                </div>
                <hr>
            </sec:authorize>
            <div class="col-md-12">
                <table id="displayTable" class="table table-hover">
                    <tr>
                        <th width="35%">Power Name</th>
                            <sec:authorize access="hasRole('ROLE_ADMIN')">
                            <th width="10%"></th>
                            <th width="10%"></th>
                            </sec:authorize>
                    </tr>
                    <c:forEach var="currentPower" items="${powerList}">
                        <tr>
                            <td>
                                <c:out value="${currentPower.powerDescription}"/>
                            </td>
                            <sec:authorize access="hasRole('ROLE_ADMIN')">
                                <td>
                                    <a href="displayEditPowerPage?powerId=${currentPower.powerId}">
                                        Edit
                                    </a>
                                </td>
                            </sec:authorize>
                            <sec:authorize access="hasRole('ROLE_ADMIN')">
                                <td>
                                    <a href="deletePower?powerId=${currentPower.powerId}">
                                        Delete
                                    </a>
                                </td>
                            </sec:authorize>
                        </tr>
                    </c:forEach>

                </table>
            </div>

        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>

