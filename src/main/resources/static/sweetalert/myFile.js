function checkEmail(check) {
    var email = $("#email").val();
    var checkEmail = typeof email.checkValidity === 'function' ? email.checkValidity() : /\S+@\S+\.\S+/.test(email);
    if (!checkEmail) {
        Swal.fire({
            title: 'your email is not match format!',
            icon: 'warning',
            showCancelButton: true,
            showConfirmButton: false,
            cancelButtonColor: '#dd2222',
            cancelButtonText: 'Ok'
        });
        check = false;
    }
    return check;
}

function checkPassword(check) {
    var password = $("#password").val();
    var repeatPassword = $("#repeatPassword").val();
    if (password.length < 6) {
        Swal.fire({
            title: 'Your password must be have at least 6 characters',
            icon: 'warning',
            showCancelButton: true,
            showConfirmButton: false,
            cancelButtonColor: '#ddb822',
            cancelButtonText: 'Ok'
        });
        check = false;
    }
    else if (password != repeatPassword) {
        Swal.fire({
            title: 'your password is not match format!',
            icon: 'warning',
            showCancelButton: true,
            showConfirmButton: false,
            cancelButtonColor: '#ddb822',
            cancelButtonText: 'Ok'
        });
        check = false;
    }
    return check;
}

function getFormData() {
    var data = {};
    var formData = $("#formSubmit").serializeArray();
    $.each(formData, function (i, v) {
        data[""+v.name+""] = v.value;
    })
    return data;
}

function checkAllFieldFilled(check) {
    var formData = $("#formSubmit").serializeArray();
    $.each(formData, function(i, v) {
        if (v.value == "") {
            Swal.fire({
                title: 'You must enter all the fields',
                icon: 'warning',
                showCancelButton: true,
                showConfirmButton: false,
                cancelButtonColor: '#ddb822',
                cancelButtonText: 'Ok'
            })
            check = false;
        }
    });
    return check;
}

function heartOnClick(id) {
    var heartIcon = document.getElementById(id);
    if (heartIcon.classList.contains("active")) {
        console.log("remove");
        $("#"+id).css('background', '');
        heartIcon.classList.remove("active");
    } else {
        console.log("add");
        $("#"+id).css('background', '#faa7a7');
        heartIcon.classList.add("active");
    }
}

function socialLogin() {
    Swal.fire({
        title: 'Sorry! Social Login is not active',
        icon: 'warning',
        showCancelButton: true,
        showConfirmButton: false,
        cancelButtonColor: '#ddb822',
        cancelButtonText: 'Ok'
    })
}

function checkLogin() {
    Swal.fire({
        title: 'You have to login to buy',
        text: 'Login right now',
        icon: 'warning',
        showCancelButton: true,
        showConfirmButton: true,
        confirmButtonColor: '#28a745e0',
        cancelButtonColor: '#dd2222',
        confirmButtonText: 'Right Now',
        cancelButtonText: 'Not Now'
    }).then((result) => {
        if (result.isConfirmed) {
            window.location.href = "/login"
        }
    })
}

function alertUsernameExist() {
    Swal.fire({
        title: 'Username is used. Please use other username!',
        icon: 'warning',
        showCancelButton: true,
        showConfirmButton: false,
        cancelButtonColor: '#ddb822',
        cancelButtonText: 'Ok'
    })
}

function alertEmailExist() {
    Swal.fire({
        title: 'Email is used. Please use other email!',
        icon: 'warning',
        showCancelButton: true,
        showConfirmButton: false,
        cancelButtonColor: '#ddb822',
        cancelButtonText: 'Ok'
    })
}

function alertRegisterSuccessfully(username, password) {
    var textInfo = "username: " + username + "\n" + "password: " + password;
    Swal.fire({
        title: 'Register Successfully!',
        text: '' + textInfo,
        icon: 'success',
        confirmButtonColor: '#28a745e0',
        confirmButtonText: 'Ok'
    })
}


