/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {

    setInterval(loadCalculations, 200);


});



function loadCalculations() {

    var megaRow = "";
    $.ajax({
        type: "GET",
        url: "calculations",
        success: function (calculationArray) {

            $.each(calculationArray.reverse().slice(0, 10), function (index, calculation) {

                var id = calculation.calculationId;
                var date = JSON.stringify(calculation.date);
                var result = calculation.result;
                var row = "<tr id=" + id + ">";
                row += '<td>' + calculation.result + '</td></tr>';
                megaRow += row;

                $("#calculationContent").html(megaRow);
            });
        }

    });
}
