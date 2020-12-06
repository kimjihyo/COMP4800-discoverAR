import React from "react"
import {Typography} from "@material-ui/core"
import "./Home.scss"

export const BaseHome = () => (
    <>
    <div className={"home__container"}>
        <img style={{width: "150px"}} src="/assets/logo.png"/>
        <Typography variant={"h1"}>
            discoverAR editor
        </Typography>
    </div>

    </>
)
