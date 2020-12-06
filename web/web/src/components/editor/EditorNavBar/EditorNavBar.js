import React from "react"
import {EditorButton} from "../EditorButton"

export const EditorNavBar = () => {
    const functions = [
        "add",
        "remove",
        "move",
        "save"
    ]

    return (
        <div className="editornavbar">
        <ul>
            <li>DiscoverAR</li>
            {functions.map(f => (
                <li>
                    <EditorButton description={f} />
                </li>
            ))}
        </ul>
        </div>
    )
}
