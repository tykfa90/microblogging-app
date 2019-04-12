console.log("ajax request");
$.ajax({
    method: "post",
    url: "http://localhost:8080/login",
    success: function () {
        console.log("success");
        $("#login-view").hide();
        $("#register-view").hide();
        $("#main-view").show();
    },
    error: function () {
        console.log("error");
        $("#login-view").show();
        $("#register-view").hide();
        $("#main-view").hide();
    }
});

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
        url: "http://localhost:8080/account",
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
        url: "http://localhost:8080/logout",
        success: function () {
            alert("Wylogowano");
            $("#login-view").show();
            $("#register-view").hide();
            $("#main-view").hide();
        }
    })
});

$("#button-login").click(function () {
    console.log("abc");
    const email = $("#username").val();
    const password = $("#password").val();

    $.ajax({
        method: "post",
        url: "http://localhost:8080/login",
        headers: {
            "Authorization": "Basic " + btoa(email + ":" + password)
        },
        contentType: "application/json",
        success: function () {
            $("#login-view").hide();
            $("#register-view").hide();
            $("#main-view").show();
            console.log("Signed in");
        },
        error: function () {
            alert("Error: \n Make sure your e-mail and password are correct");
            console.log("Error while signing in")
        }
    })
});

$("#button-redirect-register").click(function () {
    $("#login-view").hide();
    $("#register-view").show();
    $("#main-view").hide();
});
