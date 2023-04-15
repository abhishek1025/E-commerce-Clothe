const increaseBtn = document.querySelectorAll(".increaseBtn");
const decreaseBtn = document.querySelector(".decreaseBtn");
const quantityEl = document.querySelectorAll(".productQuantity")


const increaseQuantity = (elementIndex) => {
    quantityEl[elementIndex].value = Number(quantityEl[elementIndex].value) + 1;
}

const decreaseQuantity = (elementIndex) => {

    const productQuantity = Number(quantityEl[elementIndex].value)

    if (productQuantity > 1) {
        quantityEl[elementIndex].value = productQuantity - 1;
    }
}