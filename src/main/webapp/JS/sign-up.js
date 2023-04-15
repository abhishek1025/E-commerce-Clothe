
    const signUpForm = document.querySelector("#sign-up-form")

    const imageInputBtn = document.querySelector("#imageInputBtn");
    const imageInput = document.querySelector("#imageInput");
    const viewImg = document.querySelector("#view-image")

    const passswordInputEl = document.querySelector("#password")
    const confirmPassswordInputEl = document.querySelector("#confirmPassword")

    signUpForm.onsubmit = (e) => {
      e.preventDefault();

      if (!imageInput.files[0]) {
        alert("Please Upload Profile Image");
        return;
      }

      if (passswordInputEl.value !== confirmPassswordInputEl.value) {
        alert("Passwords don't match, please check it");
        return;
      }

      signUpForm.submit();
    }

    imageInputBtn.onclick = () => imageInput.click();

    imageInput.onchange = () => {
      const selectedImg = imageInput.files[0];
      const imgUrl = URL.createObjectURL(selectedImg);

      viewImg.innerHTML = `<img src=${imgUrl} alt=${selectedImg.name}>`;
    };
