import React from "react"
import Button from "@material-ui/core/Button"
import "./MockGallery.scss"

const imageUrls = [
    "/assets/test.png",
    "/assets/baby-yoda-11.jpg",
    "/assets/Jar_Jar_SWSB.png"
]

const ImageThumbnail = ({img, onClick}) => {
    return (
        <div className={"div--gallery-thumbnail"}>
            <img src={img} key={img}/>
            <Button onClick={() => onClick(img)} variant={"contained"}>Detect</Button>
        </div>
    )
}

export const MockGallery = () => {
    const handleClick = img => {
        alert(img === "/assets/test.png"  ? "Image detected!" : "Image not detected")
    }
    return (
        <div className={"div--gallery-container"}>
            {imageUrls.map(img => <ImageThumbnail img={img} onClick={handleClick} />)}
        </div>
    )
}