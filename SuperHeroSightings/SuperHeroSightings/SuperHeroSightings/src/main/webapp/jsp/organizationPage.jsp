<%-- 
    Document   : organizationPage
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
        <title>Organizations</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet"> 
        <link href="${pageContext.request.contextPath}/css/superhero.css" rel="stylesheet"> 

    </head>
    <body>
        <div class="container">
            <h2>Organizations</h2>
            <hr>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/index.jsp">Home</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/displaySightingPage">Sightings</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/displayHeroPage">Hero Information</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/displayLocationPage">Location Information</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/displayOrganizationPage">Organizations</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/displayPowerPage">Hero Powers</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/displayLoginPage">Login</a></li>
                </ul>    
            </div>
            <hr>
            <div id="topBar" class="row">
                <div class="col-md-3">
                    <a href="${pageContext.request.contextPath}/displayAddOrganizationPage" type="button" id="add-organization-button" class="btn btn-default">
                        Add Organization
                    </a>
                </div>
            </div>
            <hr>
            <div class="col-md-12">
                <table id="displayTable" class="table table-hover">
                    <tr>
                        <th width="30%">Organization Name</th>
                        <th width="20%">City</th>
                        <th width="20%">Zip Code</th>
                        <th width="10%"></th>
                        <th width="10%"></th>
                    </tr>
                    <c:forEach var="currentOrganization" items="${organizationList}">
                        <tr>
                            <td>
                                <a href="displayOrganizationDetails?organizationId=${currentOrganization.organizationId}">
                                    <c:out value="${currentOrganization.organizationName}"/>
                                </a>
                            </td>

                            <td>
                                <c:out value="${currentOrganization.city}"/>
                            </td>
                            <td>
                                <c:out value="${currentOrganization.zipCode}"/>
                            </td>
                            <td>
                                <a href="displayEditOrganizationPage?organizationId=${currentOrganization.organizationId}">
                                    Edit
                                </a>
                            </td>
                            <td>
                                <a href="deleteOrganization?organizationId=${currentOrganization.organizationId}">
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
    </body>
</html>
