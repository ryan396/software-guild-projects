<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Hero Information</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">  
        <link href="${pageContext.request.contextPath}/css/superhero.css" rel="stylesheet"> 
    </head>
    <body>
        <div class="container">
            <h2>Hero Information</h2>
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
                    <a href="${pageContext.request.contextPath}/displayAddHeroPage" type="button" id="add-Hero-button" class="btn btn-default">
                        Add Hero
                    </a>
                </div>
                <form role="form" id="search-form" method="POST" 
                      action="search">
                    <div class="col-md-3">
                        <button type="submit" id="search-button" class="btn btn-default">
                            Search By Organization
                        </button>
                    </div>
                    <div class="col-md-3">
                        <div class="col-md-8">
                            <select id="searchCategory" name="organizationId" class="bootstrap-select btn btn-default"> 
                                <option value="0" selected>All</option>
                                <c:forEach items="${organizationList}" var="currentOrganization">
                                    <option value="${currentOrganization.organizationId}">${currentOrganization.organizationName}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </form>
                        <br>
                        <br>
                  <hr>
                <div class="col-md-12">
                    <table id="displayTable" class="table table-hover">
                        <tr>
                            <th width="35%">Hero Name</th>
                            <th width="35%">Description</th>
                            <th width="10%"></th>
                            <th width="10%"></th>
                        </tr>
                        <c:forEach var="currentHero" items="${heroList}">
                            <tr>
                                <td>
                                    <a href ="displayHeroDetails?heroId=${currentHero.heroId}">
                                        <c:out value="${currentHero.heroName}"/>
                                    </a>
                                </td>
                                <td>
                                    <c:out value="${currentHero.description}"/>
                                </td>
                                <td>
                                    <a href="displayEditHeroPage?heroId=${currentHero.heroId}">
                                        Edit
                                    </a>
                                </td>
                                <td>
                                    <a href="deleteHero?heroId=${currentHero.heroId}">
                                        Delete
                                    </a>
                                </td>
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

