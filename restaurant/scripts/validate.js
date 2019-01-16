function validateEmail(){
  var mailFormat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
  var email = document.forms["myForm"]["email"].value;
  var phone = document.forms["myForm"]["phone"].value;
  var phoneFormat = /\(?([0-9]{3})\)?([ .-]?)([0-9]{3})\2([0-9]{4})/;

  if (email.match(mailFormat)) {

 } else {
   alert("You have entered an invalid email address!");
   return false;
 }

 if (phone.match(phoneFormat)){

 } else {
   alert("You entered a invalid phone number");
   return false;
 }

 message = alert("Form is valid");

 return false;
}
