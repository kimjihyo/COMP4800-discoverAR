import React from "react"
import {Canvas} from "../Canvas"
import {EditorNavBar} from "../EditorNavBar"

export const EditorApp = () => (
    <div className="app">
        <EditorNavBar/>
        <div className="editorbody">
            <Canvas />
        </div>
    </div>
)

