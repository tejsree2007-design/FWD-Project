document.getElementById("signupForm").addEventListener("submit",function(e){

e.preventDefault();

let username=document.getElementById("username").value;
let password=document.getElementById("password").value;

let users=JSON.parse(localStorage.getItem("users"))||[];

users.push({username,password});

localStorage.setItem("users",JSON.stringify(users));

document.getElementById("message").innerText="Signup successful. Go to login page.";

});