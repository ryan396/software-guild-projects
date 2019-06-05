<%-- 
    Document   : addPowerPage
    Created on : May 29, 2019, 8:36:43 PM
    Author     : rianu
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
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
            <h2>Add New Power</h2>
            <hr>
            <form class="form-horizontal" 
                  role="form" method="POST" 
                  action="addPower">
                <div class="form-group">
                    <label for="add-power-description" class="col-md-4 control-label">Power Description:</label>
                    <div class="col-md-8">
                        <input type="text" size="50" class="form-control" name="powerDescription" placeholder="Power Description" required/>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group">
                            <div class="col-md-offset-8 col-md-2">
                                <a type="button" id="cancel-add-power-button" class="btn btn-default" href="${pageContext.request.contextPath}/displayPowerPage">
                                    Cancel
                                </a>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="form-group">
                            <div class="col-md-offset-6 col-md-2">
                                <button type="submit" id="add-power-button" class="btn btn-default">
                                    Submit
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
