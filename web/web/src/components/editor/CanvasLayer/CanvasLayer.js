import React, {useState, useEffect} from "react"
import {Layer, Circle, Rect, RegularPolygon, Image, Transformer} from "react-konva"
import useImage from 'use-image'

export const CanvasLayer = ({onClick, isActive, position, shape, type, posX, posY, dispatch, triggerImage }) => {

    const [x, setX] = useState(posX)
    const [y, setY] = useState(posY)
    const [trigger_image] = triggerImage != 0 ? useImage(triggerImage) : useImage("/assets/Jar_Jar_SWSB.png")
    const [image] = useImage("/assets/Jar_Jar_SWSB.png")

    const updatePosition = (objKey, x, y) => {
        dispatch({
            type: "MOVE_OBJECT",
            obj: {
                objKey,
                x,
                y
            }
        })
    }

    switch (type) {
        case ("TRIGGER_IMAGE"):
            return (
                <Layer onClick={() => onClick(position)}>
                    <Image
                        x={x}
                        y={y}
                        width={300}
                        height={300}
                        image={trigger_image}
                        onClick={() => onClick(position)}
                        opacity={0.5}
                    />
                </Layer>
            )
        case ("IMAGE"):
            return (
                <Layer onClick={() => onClick(position)}>
                    <Image
                        draggable
                        x={x}
                        y={y}
                        width={200}
                        height={200}
                        image={image}
                        onDragStart={() => {
                            onClick(position)
                        }}
                        onDragEnd={e => {
                            setX(e.target.x())
                            setY(e.target.y())
                            updatePosition(position, e.target.x(), e.target.y())
                        }}
                        onClick={() => onClick(position)}
                    />
                </Layer>
            )
        case "SHAPE":
            switch (shape) {
                case ("CIRCLE"):
                    return (
                        <Layer onClick={() => onClick(position)}>
                            <Transformer
                                anchorStroke={"red"}
                                anchorFill={"yellow"}
                                anchorSize={20}
                                borderStroke={"green"}
                                borderDash={[3, 3]}/>
                            <Circle
                                draggable
                                x={x}
                                y={y}
                                fill={isActive ? 'red' : 'orange'}
                                radius={100}
                                onDragStart={() => {
                                    onClick(position)
                                }}
                                onDragEnd={e => {
                                    setX(e.target.x())
                                    setY(e.target.y())
                                    updatePosition(position, e.target.x(), e.target.y())
                                }}
                                onClick={() => onClick(position)}
                            />
                        </Layer>
                    )
                case ("RECTANGLE"):
                    return (
                        <Layer onClick={() => onClick(position)}>
                            <Rect
                                draggable
                                x={x}
                                y={y}
                                width={200}
                                height={100}
                                fill={isActive ? 'red' : 'orange'}
                                onDragStart={() => {
                                    onClick(position)
                                }}
                                onDragEnd={e => {
                                    setX(e.target.x())
                                    setY(e.target.y())
                                    updatePosition(position, e.target.x(), e.target.y())
                                }}
                                onClick={() => onClick(position)}
                            />
                        </Layer>
                    )
                case ("TRIANGLE"):
                    return (
                        <Layer onClick={() => onClick(position)}>
                            <RegularPolygon
                                draggable
                                x={x}
                                y={y}
                                sides={3}
                                radius={100}
                                fill={isActive ? 'red' : 'orange'}
                                onDragStart={() => {
                                    onClick(position)
                                }}
                                onDragEnd={e => {
                                    setX(e.target.x())
                                    setY(e.target.y())
                                    updatePosition(position, e.target.x(), e.target.y())
                                }}
                                onClick={() => onClick(position)}
                            />
                        </Layer>
                    )
            }
        default:
            throw new Error()
    }

}