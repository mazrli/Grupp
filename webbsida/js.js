function setFormMessage(formElement, typ, message){
    const messageElement = formElement.querySelector(".form__message");

    messageElement.textContent = message;


}







document.addEventListener("DOMContentLoaded", () => {
    const loginForm = document.querySelector("#login");
    const createAccountForm = document.querySelector("#createAccount");

    document.querySelector("#linkCreateAccount").addEventListener("click", e => {
        e.preventDefault();
        loginForm.classList.add("form--hidden");
        createAccountForm.classList.remove("form--hidden");
    });

    document.querySelector("#linkLogin").addEventListener("click", e => {
        e.preventDefault();
        loginForm.classList.remove("form--hidden");
        createAccountForm.classList.add("form--hidden");
    });
    loginForm.addEventListener("submit", e => {

        e.preventDefault();

        setFormMessage(loginForm, "error", "Invalid username/password")
    });
});


function validate(){
    var user = document.getElementById("user").value;
    var password = document.getElementById("password").value;
    if (user == "Admin" && password== "user"){
        
        window.location.href="./menu.html"
        
        return false;
        

    }
 
    else{
        alert("Login is false and Not Regisstered")
        return true;
        
    }



    

   



};












