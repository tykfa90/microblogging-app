$("#button-register").click(function () {
    const name = $("#input-displayname").val();
    const email = $("#input-email").val();
    const password = $("#input-password").val();
    const confirmPassword = $("#input-passwordconfirm").val();
    if (password !== confirmPassword){
        alert("Passwords are not equals!");
    }
    const account = {
        username: email,
        password: password,
        displayName: name,
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
