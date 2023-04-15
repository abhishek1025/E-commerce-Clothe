const cartShowBtn = document.querySelector('#cart-display-btn');
const cartHideBtn = document.querySelector('#cart-hide-btn');
const cartPreviewBg = document.querySelector('#cart-preview-bg');
const cartPreviewSec = document.querySelector('#cart-preview-sec');

cartShowBtn.onclick = () => {
    cartPreviewBg.style.height = "100vh";
    cartPreviewSec.style.right = 0;
}

cartHideBtn.onclick = () => {
    cartPreviewBg.style.height = "0vh";
    cartPreviewSec.style.right = "-300px";
}