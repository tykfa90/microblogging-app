$("#button-register").click(function () {
    const name = $("#uniqueName").val();
    const email = $("#email").val();
    const password = $("#inputPassword").val();
    const confirmPassword = $("#inputPasswordConfirm").val();
    if (password !== confirmPassword){
        alert("Passwords are not equals!");
    }
    const account = {
        login: name,
        password: password,
        email: email,
        role: "USER",
        status: "ACTIVE"
    }

    $.ajax({
        method: "post",
        url: "http://localhost:8080/accounts",
        data: JSON.stringify(account),
        contentType: "application/json",
        success: function () {
            console.log("Account created");
        }
    })
});
