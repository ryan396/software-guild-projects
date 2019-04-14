//home.js
//ready function says that when webpage is opened, run this function
//$symbol is an alias for jquery function. can replace with call to jquery
//such as jQuery
$(document).ready(function() {
  loadItems();

  $("#add-dollar-button").click(function(event){
    var moneyIn = parseFloat($("#money-panel").text());
    moneyIn += 1.00;
    $("#money-panel").empty();
    $("#money-panel").append(moneyIn.toFixed(2).toString());

  });

  $("#add-quarter-button").click(function(event){
    var moneyIn = parseFloat($("#money-panel").text());
    moneyIn += .25;
    $("#money-panel").empty();
    $("#money-panel").append(moneyIn.toFixed(2).toString());
  });

  $("#add-dime-button").click(function(event){
    var moneyIn = parseFloat($("#money-panel").text());
    moneyIn += .10;
    $("#money-panel").empty();
    $("#money-panel").append(moneyIn.toFixed(2).toString());
  });

  $("#add-nickel-button").click(function(event){
    var moneyIn = parseFloat($("#money-panel").text());
    moneyIn += .05;
    $("#money-panel").empty();
    $("#money-panel").append(moneyIn.toFixed(2).toString());
  });

  $("#purchase-button").click(function(event){
    purchaseItem();
  });

  $("#make-change-button").click(function(event){
    makeChange();
  });

});


function loadItems() {

  $.ajax({
    type: "GET",
    url: "http://localhost:8080/items",
    success: function(itemArray) {
      $.each(itemArray, function(index, item) {
        var id = item.id;
        var name = item.name;
        var price = item.price;
        var quantity = item.quantity;

        var row = "<div class='col-md-3 text-center item-class' onclick='pickItem(" + id + ")'>";
        //row += "<a onclick='pickItem(" + id + ")' style='color:black'>";
        row += id +"<br />";
        row += name +"<br />";
        row += "$" + price +"<br />";
        row += "Quantity Left: " + quantity +"<br />";
        row += "</div>";

        $("#item-container").append(row);
      });
    },
    error: function() {
      $("#errorMessages")
        .append($("<li>")
          .attr({
            class: "list-group-item list-group-item-danger"
          })
          .text("Error calling web service. Please try again later."));
    }
  });
}

function purchaseItem() {
  $("#messages-box").empty();
  $("#change-box").empty();
  if($("#item-input").val() == "") {
    $("#messages-box").append("Please select an item");
    return false;
  }

  $.ajax({
    type: "GET",
    url: "http://localhost:8080/money/" + $("#money-panel").text() + "/item/"
    + $("#item-input").val(),
    success: function(data, status) {
      $("#change-box").append("Quarters: " + data.quarters);
      $("#change-box").append(" Dimes: " + data.dimes);
      $("#change-box").append(" Nickels: " + data.nickels);
      $("#change-box").append(" Pennies: " + data.pennies);
      $("#messages-box").append("THANK YOU!!!");

    },
    error: function (xhr) {
      var jsonResponse = JSON.parse(xhr.responseText);
      $("#messages-box").append(jsonResponse.message);
    }
  });
  clearItems();
  $("#money-panel").empty();
  $("#money-panel").append("0.00");
  loadItems();
}

  function clearItems() {
    $("#item-container").empty();
  }


function pickItem(id) {
  $("#messages-box").empty();
  $("#item-input").val(id);
}

function makeChange() {
  $("#messages-box").empty();
  $("#change-box").empty();
  var money = parseFloat($("#money-panel").text());
  var moneyInPennies = parseInt(money*100);
  console.log(moneyInPennies);
  var quarters = 0;
  var dimes = 0;
  var nickels = 0;
  var pennies = 0;

  while(moneyInPennies > 0) {
    if (moneyInPennies >= 25) {
      moneyInPennies = moneyInPennies - 25;
      quarters++;
    } else if (moneyInPennies >= 10 && moneyInPennies < 25) {
      moneyInPennies = moneyInPennies - 10;
      dimes++;
    } else if (moneyInPennies >= 5 && moneyInPennies < 10) {
      moneyInPennies = moneyInPennies - 5;
      nickels++;
    } else if (moneyInPennies >= 1 && moneyInPennies < 5) {
      moneyInPennies = moneyInPennies - 1;
      pennies++;
    }
  }
  $("#money-panel").empty();
  $("#money-panel").append("0.00");
  $("#change-box").append("Quarters: " + quarters);
  $("#change-box").append(" Dimes: " + dimes);
  $("#change-box").append(" Nickels: " + nickels);
  $("#change-box").append(" Pennies: " + pennies);

}
