import {IconButton, Typography} from "@material-ui/core";
import Clear from "@material-ui/icons/Clear"
import React from "react";

export const JourneySidebar = ({activeKey, handleDelete, journey, setActiveKey}) => (
    <div className="div--editor-journey">
        <div className="div--editor-journey__title">
            <Typography variant="button">Journey Sequence</Typography>
        </div>
        {journey.map((key, index) => (
            <div
                key={index}
                className={`div--editor-journey__event div--editor-journey__event__${journey.indexOf(key) === activeKey ? 'active' : 'inactive'}`}
                onClick={() => {
                    setActiveKey(index)
                }}
            >
                <div className="div--editor-journey__event__content">
                    <div className="div--editor-journey__event__content__num">
                        <Typography>{journey.indexOf(key)}</Typography>
                    </div>
                    {index !== 0 ? (
                        <div className="div--editor-journey__event__content__delete">
                            <IconButton size="small" onClick={() => handleDelete(activeKey)}>
                                <Clear />
                            </IconButton>
                        </div>
                    ) : null}
                </div>
            </div>))}
    </div>
)