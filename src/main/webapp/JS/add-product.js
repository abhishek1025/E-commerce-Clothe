const productImg = document.querySelector('#productImage')

productImg.onchange = () => {
    
    const selectedImg = productImg.files[0];

    const imgUrl = URL.createObjectURL(selectedImg);

    document.querySelector('#displayProductImg').innerHTML = `<img src=${imgUrl} alt=${selectedImg.name}>`

}