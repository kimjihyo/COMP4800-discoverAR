import React, {useEffect, useReducer, useState} from "react"
import {useHistory, useParams} from "react-router-dom"
import {CircularProgress} from "@material-ui/core"
import {editorReducer} from "./Canvas.reducer"
import {EditorToolBar} from "./EditorToolBar"
import {JourneySidebar} from "./JourneySidebar"
import {CanvasStage} from "./Stage"
import {NameWidget} from "./NameWidget"
import './Canvas.css'

const discovery = {
    url: "",
    shape: "UNKNOWN" || "CIRCLE" || "RECTANGLE" || "TRIANGLE",
    x: 0,
    y: 0,
    text: "",
    color: "FFF",
    type: "IMAGE" || "SHAPE" || "TEXT" || "TRIGGER_IMAGE"
}

export const BaseCanvas = ({currentJourney, getJourney, saveJourney}) => {
    const history = useHistory()
    const {journeyId} = useParams()
    const initialState = {
        ...currentJourney,
        activekey: 0,
        triggerImage: currentJourney.images && !currentJourney.images.length ? currentJourney.images[0] : 0,
        discoveries: currentJourney.discoveries && !currentJourney.discoveries.length ? [
            {
                ...discovery,
                type: "TRIGGER_IMAGE",
                x: 0.5 * (window.innerWidth - 300),
                y: 0.5 * (window.innerHeight - 300),
            }] : currentJourney.discoveries
    }

    const [state, dispatch] = useReducer(editorReducer, initialState)
    const [activeKey, setActiveKey] = useState(state.activekey)
    const [toggleAddEvent, setToggleAddEvent] = useState(false)
    const [isLoading, setIsLoading] = useState(true)

    useEffect(() => {
        getJourney(journeyId)
            .then(res => {
                dispatch({
                    type: "GET_JOURNEY",
                    journey: res
                })
            })
            .finally(() => {
                setIsLoading(false)
            })
    }, [])

    const handleLayerClick = pos => {
    }

    const handleDelete = pos => {
        if (pos > 0) {
            dispatch({type: "DELETE_EVENT", key: pos})
            setActiveKey(--pos)
        }
    }

    const handleAdd = () => {
        setToggleAddEvent(!toggleAddEvent)
    }

    const handleSave = () => {
        saveJourney(state)
            .then(() => alert("Journey saved"))
            .catch(() => alert("Error saving journey"))
    }

    const toolBarProps = {
        activeKey,
        handleAdd,
        handleDelete,
        handleSave,
        handleReturn: () => {
            history.push("/dashboard")
        }
    }

    return isLoading ? (<CircularProgress/>) :
        (
            <>
                <NameWidget dispatch={dispatch} title={currentJourney.title}/>
                <div className="canvasContainer">
                    <EditorToolBar toolBarProps={toolBarProps}/>
                    <CanvasStage
                        activeKey={activeKey}
                        dispatch={dispatch}
                        handleLayerClick={handleLayerClick}
                        isAddEventOpen={toggleAddEvent}
                        journey={state.discoveries}
                        triggerImage={state.triggerImage}
                    />
                    <JourneySidebar
                        activeKey={activeKey}
                        handleDelete={handleDelete}
                        journey={state.discoveries}
                        setActiveKey={setActiveKey}
                    />
                </div>
            </>
        )
}
