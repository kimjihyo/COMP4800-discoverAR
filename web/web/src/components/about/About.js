import React from "react"
import {Typography} from "@material-ui/core"
import "./About.css"

export const About = () => {

    return (
        <div className="div--about">
            <div className="div--about_title">
                <Typography variant={"h2"}>
                    What is this application about?
                </Typography>
            </div>
            <div>
                <Typography variant={"body1"}>
                    This website is an editor to create augmented reality journeys, 
                    abbreviated as AR journeys. A journey is a series of events 
                    for the user to visit. In a journey, the user is prompted 
                    by events as represented as images, shapes, buttons, and 
                    videos. Once the user has created a journey, the user can 
                    export the project for export to the Android viewer 
                    (currently in development). The Android viewer will integrate 
                    an augmented reality engine to allow the user to recognize 
                    triggers that start the journey. 
                </Typography>
            </div>
        </div>
    )
}
