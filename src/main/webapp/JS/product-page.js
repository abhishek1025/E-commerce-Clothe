let lowerSlider = document.querySelector('#lower'),
    upperSlider = document.querySelector('#upper'),
    lowerVal = parseInt(lowerSlider.value),
    upperVal = parseInt(upperSlider.value);

upperSlider.oninput = function () {
    lowerVal = parseInt(lowerSlider.value);
    upperVal = parseInt(upperSlider.value);

    if (upperVal < lowerVal + 300) {
        lowerSlider.value = upperVal - 300;

        if (lowerVal == lowerSlider.min) {
            upperSlider.value = 300;
        }
    }

    document.querySelector('#priceFrom').innerHTML = `NPR ${lowerVal}`;
    document.querySelector('#priceTo').innerHTML = `NPR ${upperVal}`;
};


lowerSlider.oninput = function () {
    lowerVal = parseInt(lowerSlider.value);
    upperVal = parseInt(upperSlider.value);

    if (lowerVal > upperVal - 300) {
        upperSlider.value = lowerVal + 300;

        if (upperVal == upperSlider.max) {
            lowerSlider.value = parseInt(upperSlider.max) - 300;
        }

    }

    document.querySelector('#priceFrom').innerHTML = `NPR ${lowerVal}`;
    document.querySelector('#priceTo').innerHTML = `NPR ${upperVal}`;
};



let lowerSliderRating = document.querySelector('#lowerRating'),
    upperSliderRating = document.querySelector('#upperRating'),
    lowerRatingVal = parseFloat(lowerSliderRating.value),
    upperRatingVal = parseFloat(upperSliderRating.value);

upperSliderRating.oninput = function () {
    lowerRatingVal = parseFloat(lowerSliderRating.value);
    upperRatingVal = parseFloat(upperSliderRating.value);

    if (upperRatingVal < lowerRatingVal + 1) {
        lowerSliderRating.value = upperRatingVal - 1;

        if (lowerRatingVal == lowerSliderRating.min) {
            upperSliderRating.value = 1;
        }
    }


    document.querySelector('#ratingFrom').innerHTML = `<i class="fa-solid fa-star rating-icon"></i> ${lowerRatingVal} `;
    document.querySelector('#ratingTo').innerHTML = `<i class="fa-solid fa-star rating-icon"></i> ${upperRatingVal} `;
};


lowerSliderRating.oninput = function () {
    lowerRatingVal = parseFloat(lowerSliderRating.value);
    upperRatingVal = parseFloat(upperSliderRating.value);

    if (lowerRatingVal > upperRatingVal - 1) {
        upperSliderRating.value = lowerRatingVal + 1;

        if (upperRatingVal == upperSliderRating.max) {
            lowerSlider.value = parseFloat(upperSliderRating.max) - 1;
        }

    }

    document.querySelector('#ratingFrom').innerHTML = `<i class="fa-solid fa-star rating-icon"></i> ${lowerRatingVal} `;
    document.querySelector('#ratingTo').innerHTML = `<i class="fa-solid fa-star rating-icon"></i> ${upperRatingVal} `;
};



/*const addToCartBtnEl = document.querySelector("#addToCartBtn")

if(addToCartBtnEl !== null){
	console.log('hi111')

	addToCartBtnEl.onclick = () => {
		alert("Please sign in first to add the product in cart")
		console.log('hi 2')
	}
	
}*/

function displayMsg() {
	alert("Please sign in first to add the product in cart")
}



