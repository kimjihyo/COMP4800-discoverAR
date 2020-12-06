import React from 'react';
import './newjourney.css'

const NewJourneyTab = () => {

    let selectedFile = null,
        imageB64 = null;


    const fileSelected = (event) => {
        selectedFile = event.target.files[0];
        
        if (selectedFile) {
            const reader = new FileReader();
            reader.readAsDataURL(selectedFile);
            reader.onload = function() {
                imageB64 = reader.result;
                document.getElementById("preview").src = imageB64;
            }
        }
    }

    const closeTab = () => {
        document.getElementById('journeyName').value = '';
        document.getElementById('journeyDesc').value = '';
        document.getElementById('journeyIMG').value = '';
        document.getElementById('overlay').style.display = "none";
        document.getElementById("preview").src = "";
    }

    return (
        <div id="newJourneyContainer">
            <div>
                <button onClick={closeTab} class="closeTab">Close</button>
                <h4>Journey Name</h4>
                <input type="text" id="journeyName" placeholder="Journey Name"></input>
                <h3>Description</h3>
                <textarea cols="50" rows="5" id="journeyDesc" name="description" ></textarea>

                <h3>Image to Track</h3>
                <input type="file" onChange={fileSelected} id="journeyIMG" name="journeyIMG" accept="image/png, image/jpeg" /> 
                <img id="preview" alt="" />
                <br/><br/>
            </div>
        </div>
    );
}

export default NewJourneyTab