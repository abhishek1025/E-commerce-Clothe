const newPasswordEl = document.querySelector("#newPassword");
const confirmPasswordEl = document.querySelector("#confirmPassword");

const formEl = document.querySelector("#changePasswordForm");

formEl.onsubmit = (e) => {

    e.preventDefault();

    console.log(confirmPasswordEl.value, newPasswordEl.value)

    if (newPasswordEl.value !== confirmPasswordEl.value) {
        return alert("New Passwords don't match");
    }

    return formEl.submit();
}