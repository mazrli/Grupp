function validate(){
    var user = document.getElementById("user").value;
    var password = document.getElementById("password").value;
    if (user == "Admin" && password== "user"){
        
        alert("Login is ok");
        return false;

    }
    else if (user != "Admin" && password == "user"){
        alert("No such user");
        return false;
        
        
    }

    else if (user != "Admin" && password == "user"){
        alert("Incorrect password");
        return false;
    }
    else{
        alert("Login is false and Not Regisstered")
        return true;
    }



}

