document.getElementById("bookingForm").addEventListener("submit",function(event){

event.preventDefault();

let name=document.getElementById("name").value;
let email=document.getElementById("email").value;
let roomType=document.getElementById("roomType").value;
let checkin=document.getElementById("checkin").value;
let checkout=document.getElementById("checkout").value;

let message=document.getElementById("message");

if(new Date(checkout)<=new Date(checkin)){

message.innerText="Checkout date must be after check-in";
message.style.color="red";
return;

}

let booking={name,email,roomType,checkin,checkout};

let bookings=JSON.parse(localStorage.getItem("bookings"))||[];

bookings.push(booking);

localStorage.setItem("bookings",JSON.stringify(bookings));

message.innerText="Booking Successful!";
message.style.color="green";

document.getElementById("bookingForm").reset();

});