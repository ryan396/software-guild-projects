<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Calculator!</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/calculator.css" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Karla&display=swap" rel="stylesheet">
        <meta name="viewport" content="width=device-width, initial-scale=1">
    </head>
    <body>
        <div class="container">
            <h1>Calculator</h1>
            <hr/>
            <h4>
                Hello. This is a calculator application. Please select two numbers
                and a mathematical operator (addition, subtraction, division, or multiplication).
                Previous calculations will appear at the bottom.
            </h4>
            <br>
            <div class="col-md-6">
                <form class="form-horizontal"
                      role="form" method="POST"
                      action="calculate">
                    <div class="form-group">
                        <label for="operand1" class="col-md-3 control-label">First Number:</label>
                        <div class="col-md-3">
                            <input id="num" type="number" class="form-control" name="operand1" required/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="operand2" class="col-md-3 control-label">Second Number:</label>
                        <div class="col-md-3">
                            <input id="num" type="number" class="form-control" name="operand2" required/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="operator" class="col-md-3 control-label">Operator:</label>
                        <div class="col-md-3">
                            <select id="num" name="operator" class="bootstrap-select btn btn-default"> 
                                <option value="+">Add</option>
                                <option value="-">Subtract</option>
                                <option value="/">Divide</option>
                                <option value="*">Multiply</option>
                            </select>

                        </div>
                    </div>
                    <br>
                    <div class="form-group">
                        <div class="col-md-offset-3 col-md-8">
                            <input type="submit" class="btn btn-default" value="Calculate"/>
                        </div>
                    </div>
                </form>
                <div class="col-md-6" id="calcDiv">
                    <table id="calculations" class="table table-hover" >
                        <thead>
                            <tr>
                                <th width="30%">Previous Calculations</th>
                            </tr>
                        </thead>
                        <tbody id="calculationContent">
                            <!-- data will go here !-->
                        </tbody>
                    </table>       
                </div>
            </div>

            <!-- Placed at the end of the document so the pages load faster -->
            <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
            <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
            <script src="${pageContext.request.contextPath}/js/calculator.js"></script>

    </body>
</html>

