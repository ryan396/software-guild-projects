<%-- 
    Document   : addlocationPage
    Created on : May 30, 2019, 1:34:18 PM
    Author     : rianu
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
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
            <form class="form-horizontal" 
                  role="form" method="POST" 
                  action="addLocation">
                <div class="form-group">
                    <label for="add-location-name" class="col-md-4 control-label">Name:</label>
                    <div class="col-md-8">
                        <input type="text" class="form-control" name="locationName" placeholder="Name"/>
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
                    <label for="add-latitude" class="col-md-4 control-label">Latitude:</label>
                    <div class="col-md-8">
                        <input type="text" class="form-control" name="latitude" placeholder="Latitude"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-longitude" class="col-md-4 control-label">Longitude:</label>
                    <div class="col-md-8">
                        <input type="text" class="form-control" name="longitude" placeholder="Longitude"/>
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
                                    Add Location
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
