$("#button-register").click(function () {
    const name = $("#input-displayname").val();
    const email = $("#input-email").val();
    const password = $("#input-password").val();
    const confirmPassword = $("#input-passwordconfirm").val();
    if (password !== confirmPassword) {
        alert("Passwords do not match!");
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
            window.location.href = "/login";
            console.log("Account created");
        },
        error: function () {
            console.log("Error while creating account")
        }
    })
});

$("#button-logout").click(function () {
    $.ajax({
        method: "get",
        url: "http://localhost:8080",
        success: function () {
            window.location.href = "/logout";
            console.log("Logged out");
        },
        error: function () {
            console.log("Error while logging out")
        }
    })
});
