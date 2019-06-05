/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function mapIt() {
    var latitude = Number(document.getElementById('latitude').innerHTML);
    var longitude = Number(document.getElementById('longitude').innerHTML);
    var loc = {lat: latitude, lng: longitude};
    var map = new google.maps.Map(
            document.getElementById('map'), {zoom: 5, center: loc});
    var marker = new google.maps.Marker({position: loc, map: map});
}
  