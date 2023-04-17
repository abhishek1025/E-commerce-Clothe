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

const showSearchBoxBtn = document.querySelector("#show-search-box-btn")
const searchBox = document.querySelector("#search-box")
const searchFormSubmitBtn = document.querySelector("#search-form-submit-btn")

showSearchBoxBtn.onclick = () => {
    searchBox.style.width = "370px";
    searchBox.style.visibility = "visible";
    showSearchBoxBtn.style.display = "none";
}


const searchByEl = document.querySelector("#searchBy");
const searchBoxEl = document.querySelector("#searchBox");

const changeInputType = (element) => {
  if(element.value === "productPrice"){
  		searchBoxEl.type = "number";
  			searchBoxEl.min = 0;
  				searchBoxEl.max = 5000;
  			} else{
  				searchBoxEl.type = "text";
  			}
  		}
 
  		searchByEl.onchange = (e) => {
  			changeInputType(e.target);
  		}
  		
  		changeInputType(searchByEl);    
  		