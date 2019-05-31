<%-- 
    Document   : addOrganizationPage
    Created on : May 30, 2019, 1:34:18 PM
    Author     : rianu
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>JSP Page</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">  
    </head>
    <body>
        <div class="col-md-6">
            <h2>Add New Organization</h2>
            <hr>
            <form class="form-horizontal" 
                  role="form" method="POST" 
                  action="addOrganization">
                <div class="form-group">
                    <label for="add-organization-name" class="col-md-4 control-label">Name:</label>
                    <div class="col-md-8">
                        <input type="text" class="form-control" name="name" placeholder="Name"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-description" class="col-md-4 control-label">Description:</label>
                    <div class="col-md-8">
                        <input type="text" class="form-control" name="description" placeholder="Description"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-street" class="col-md-4 control-label">Street:</label>
                    <div class="col-md-8">
                        <input type="text" class="form-control" name="street" placeholder="Street"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-city" class="col-md-4 control-label">City:</label>
                    <div class="col-md-8">
                        <input type="text" class="form-control" name="city" placeholder="City"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-zip-code" class="col-md-4 control-label">Zip Code:</label>
                    <div class="col-md-8">
                        <input type="text" class="form-control" name="zipCode" placeholder="Zip Code"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-phone" class="col-md-4 control-label">Phone:</label>
                    <div class="col-md-8">
                        <input type="text" class="form-control" name="phone" placeholder="123-232-123"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-email" class="col-md-4 control-label">Email:</label>
                    <div class="col-md-8">
                        <input type="email" class="form-control" name="email" placeholder="Email"/>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-4">
                        <div class="form-group">
                            <div class="col-md-offset-6 col-md-2">
                                <a type="button" id="cancel-add-button" class="btn btn-default" href="${pageContext.request.contextPath}/displayOrganizationPage">
                                    Cancel
                                </a>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="form-group">
                            <div class="col-md-offset-12 col-md-2">
                                <button type="submit" id="add-organization-button" class="btn btn-default">
                                    Add Organization
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
