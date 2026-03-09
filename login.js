document.getElementById("loginForm").addEventListener("submit",function(e){

e.preventDefault();

let username=document.getElementById("username").value;
let password=document.getElementById("password").value;
let role=document.getElementById("role").value;

let message=document.getElementById("message");

if(role==="admin"){

if(username==="tejaswini" && password==="admin123"){

window.open("admin.html","_blank");

}else{

message.innerText="Invalid Admin Login";
message.style.color="red";

}

}

else if(role==="user"){

let users=JSON.parse(localStorage.getItem("users"))||[];

let found=users.find(function(user){
return user.username===username && user.password===password;
});

if(found){

window.open("booking.html","_blank");

}else{

message.innerText="Invalid User Login";
message.style.color="red";

}

}

});