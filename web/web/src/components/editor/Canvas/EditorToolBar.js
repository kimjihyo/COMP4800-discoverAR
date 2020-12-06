import {ToolbarList} from "./toolbar";
import {EditorButton} from "../EditorButton";
import {Typography} from "@material-ui/core";
import React from "react";

export const EditorToolBar = toolBarProps => (
    <div className="div--editor-toolbox">
        {ToolbarList(toolBarProps).map(({type, description, onClick}, idx) => (
            <div className="div--editor-container" key={idx}>
                <div className="div--editor-container__element">
                    <EditorButton type={type} onClick={onClick}/>
                </div>
                <div className="div--editor-container__element">
                    <Typography variant="button">{description}</Typography>
                </div>
            </div>
        ))}
    </div>
)