import React, {useEffect, useState} from "react"
import {Stage} from "react-konva"
import {PopupIconList} from "./toolbar"
import {CanvasLayer} from "../CanvasLayer"
import {EditorButton} from "../EditorButton"

const AddEventPopup = props => (
    <div className="div--add__popup">
        {PopupIconList(props).map(({type, onClick}) => (
            <div>
                <EditorButton type={type} onClick={onClick}/>
            </div>
        ))}
    </div>
)

export const CanvasStage = ({activeKey, dispatch, handleLayerClick, isAddEventOpen, journey, triggerImage}) => {
    const [container, setContainer] = useState({})

    useEffect(() => setContainer(document.querySelector('.div--editor-canvas')), [])

    return (
        <div className="div--editor-canvas">
            {isAddEventOpen && AddEventPopup({dispatch, activeKey})}
            <Stage width={container.offsetWidth} height={container.offsetHeight} className="windowcanvas">
                {journey.map(({x, y, shape, type}, index) => <CanvasLayer
                    key={index}
                    onClick={() => handleLayerClick(activeKey)}
                    position={index}
                    isActive={index === activeKey}
                    type={type}
                    shape={shape}
                    posX={x}
                    posY={y}
                    dispatch={dispatch}
                    triggerImage={triggerImage}
                />)}
            </Stage>
        </div>
    )
}