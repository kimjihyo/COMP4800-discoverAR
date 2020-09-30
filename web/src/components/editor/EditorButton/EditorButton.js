import React from 'react'
import ArrowBackSharpIcon from '@material-ui/icons/ArrowBackSharp';
import Delete from '@material-ui/icons/Delete';
import DeleteForeverOutlinedIcon from '@material-ui/icons/DeleteForeverOutlined';
import AddIcon from '@material-ui/icons/Add';
import SaveIcon from '@material-ui/icons/Save';
import { IconButton } from '@material-ui/core';
import PanoramaOutlinedIcon from '@material-ui/icons/PanoramaOutlined';
import "./EditorButton.css"

export const EditorButton = ({type, onClick}) => {
    
    const theme = {color: "white"}

    switch (type) {
        case ("ADD_EVENT"):
            return (<div >
                <IconButton onClick={onClick}>
                    <AddIcon style={theme} />
                </IconButton>
            </div>)
        case ("DELETE_EVENT"):
            return (<div >
                <IconButton onClick={onClick}>
                    <Delete style={theme} />
                </IconButton>
            </div>)
        case ("SAVE_JOURNEY"):
            return (<div>
                <IconButton onClick={onClick}>
                    <SaveIcon style={theme} />
                </IconButton>
            </div>)
        case ("DELETE_JOURNEY"):
            return (<div >
                <IconButton onClick={onClick}>
                    <DeleteForeverOutlinedIcon style={theme} />
                </IconButton>
            </div>)
        case ("RETURN_TO_DASHBOARD"):
            return (<div >
                <IconButton onClick={onClick}>
                    <ArrowBackSharpIcon style={theme} />
                </IconButton>
            </div>)
        case ("ADD_CIRCLE"):
            return (<div >
                <IconButton onClick={onClick}>
                    <div className="button--editor__container__icon">
                        <img className="button--editor__container__icon__image" src="/assets/circle.svg" alt="circleIcon"/>
                    </div>
                </IconButton>
            </div>)
        case ("ADD_RECTANGLE"):
            return (<div >
                <IconButton onClick={onClick}>
                    <div className="button--editor__container__icon">
                        <img className="button--editor__container__icon__image" src="/assets/rectangle.svg" alt="rectangleIcon"/>
                    </div>
                </IconButton>
            </div>)
        case ("ADD_TRIANGLE"):
            return (<div >
                <IconButton onClick={onClick}>
                <div className="button--editor__container__icon">
                    <img className="button--editor__container__icon__image" src="/assets/triangle.svg" alt="triangleIcon"/>
                </div>
                </IconButton>
            </div>)
        case ("ADD_IMAGE"):
                return (<div >
                    <IconButton onClick={onClick}>
                        <PanoramaOutlinedIcon style={theme} fontSize="large"/>
                    </IconButton>
                </div>)
        default:
            throw new Error()
    }
}