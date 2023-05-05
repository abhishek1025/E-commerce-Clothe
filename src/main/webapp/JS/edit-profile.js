 const profileImgImput = document.querySelector("#profileImgInput");
 const viewImgDiv = document.querySelector("#view-pp-img");

	profileImgInput.onchange = () => {

	  const selectedImg = profileImgImput.files[0];
	  const imgUrl = URL.createObjectURL(selectedImg);
            

            viewImgDiv.innerHTML = `<img src=${imgUrl} alt=${selectedImg.name}>`;
            
            console.log(viewImgDiv)

        }
        	        
            
          
        
