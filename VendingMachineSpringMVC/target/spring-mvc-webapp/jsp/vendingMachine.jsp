<%-- 
    Document   : vendingMachine
    Created on : Apr 29, 2019, 10:33:14 AM
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
        <title>Vending Machine</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/VendingMachine.css" rel="stylesheet">
    </head>
    <body>
    <div class="container" id="main-container">

      <div class="row" id="top-box">
        <h1>Vending Machine</h1>
        <hr>
        <ul class="list-group" id="errorMessages"></ul>
      </div>
      <div class="row" id="items-row">
        <div class="col-md-8" id="item-container">
            <c:forEach var="currentItem" items="${itemList}">
                <a href="${pageContext.request.contextPath}/pickItem/${currentItem.itemId}" id="item-box">
                <div class='col-md-3 text-center item-class'>
                    <c:out value="${currentItem.itemId}"/>
                    <br>
                    <c:out value="${currentItem.name}"/>
                    <br>
                    $<c:out value="${currentItem.price}"/>
                    <br>
                    Quantity Left: <c:out value="${currentItem.quantity}"/>
                     
                </div>
                </a>
            </c:forEach>
        </div>
        <div class="col-md-4" id="right-col">

          <div class="row" id="add-money-form">
            <form class="form-horizontal" role="form" id="money-form">
              <h4>Total $ In</h4>
              <br>
              <div class="form-group" id="money-area">
                <div class="panel panel-default" id="money-panel-main">
                    <div id="money-panel"class="panel-body">
                        <c:out value="${currentBalance}"/>
                    </div>
                </div>
              </div>
              <div id="button-box-1" class="form-group">
                <div class="col-md-4">
                  <a href="${pageContext.request.contextPath}/addMoney/dollar" id="money-button" class="btn btn-default">Add Dollar</a>
                </div>
                <div class="col-md-4">
                   <a href="${pageContext.request.contextPath}/addMoney/quarter" id="money-button" class="btn btn-default">Add Quarter</a>
                </div>
              </div>
              <div id="button-box-2" class="form-group">
                <div class="col-md-4">
                   <a href="${pageContext.request.contextPath}/addMoney/dime" id="money-button" class="btn btn-default" >Add Dime</a>
                </div>
                <div class="col-md-4">
                   <a href="${pageContext.request.contextPath}/addMoney/nickel" id="money-button" class="btn btn-default">Add Nickel</a>
                </div>
              </div>
            </form>
            <hr>
            <form class="form-horizontal" role="form" id="messages-form">
              <h4>Messages:</h4>
              <br>
              <div class="form-group" id="message-area">
                <div class="panel panel-default" id="message-panel">
                  <div class="panel-body" id="messages-box" value="${message}">${message}</div>
                </div>
              </div>
              <div class="row" id="item-row">
                <div class="form-group">
                  <label for="item-input" class="col-md-4 control-label">
                    <h4>Item:</h4>
                  </label>

                  <div class="col-md-6">
                    <input type="text" value="${itemMessage}" class="form-control" id="item-input" required/>
                  </div>
                </div>
              </div>
              <br>
              <div class="form-group">
                  <a href="${pageContext.request.contextPath}/purchaseItem" id="purchase-button" class="btn btn-default">
                  Make Purchase</a>
              </div>
            </form>
            <hr>
            <form class="form-horizontal" role="form" id="change-form">
              <h4>Change</h4>
              <br>
              <div class="form-group" id="change-area">
                <div class="panel panel-default" id="change-panel">
                  <div id="change-box" class="panel-body"> <c:out value="${changeDue}"/></div>
                </div>
              </div>
              <div class="form-group">
                  <a href="${pageContext.request.contextPath}/makeChange" id="make-change-button" class="btn btn-default">Change Return</a>
              </div>
            </form>
          </div>
        </div>

      </div>
    </div>



  
          
       
    <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
