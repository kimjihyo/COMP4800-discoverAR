import React, {useEffect, useState} from "react"
import {Typography} from "@material-ui/core"

export const NameWidget = ({dispatch, title}) => {
    const [projectName, setProjectName] = useState("");

    useEffect(() => {
        setProjectName(title)
    }, [])

    const handleChange = e => {
        setProjectName(e.target.value)
    }

    const handleUpdate = e => {
        dispatch({
            type: "UPDATE_TITLE",
            title: projectName
        })
    }

    return (
        <ul className="div--editor-name__widget">
            <li><Typography variant="button">
                Project Name
            </Typography></li>
            <li><input
                className={"input--editor-name__widget"}
                type={"text"}
                value={projectName}
                onChange={handleChange}
                onBlur={handleUpdate}
            /></li>
        </ul>
    )
}