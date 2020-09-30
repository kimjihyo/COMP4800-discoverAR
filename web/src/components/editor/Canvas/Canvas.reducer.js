// TODO: This should be extrapolated to the redux folder

export const editorReducer = (state, action) => {
    switch (action.type) {
        case "ADD_CIRCLE":
            return {
                ...state,
                activekey: action.key,
                discoveries: [
                    ...state.discoveries,
                    {
                        type: "SHAPE",
                        shape: "CIRCLE",
                        x : action.x,
                        y : action.y,
                        color : "orange"
                    }
                ]
            }
        case "ADD_RECTANGLE":
            return {
                ...state,
                activekey: action.key,
                discoveries: [
                    ...state.discoveries,
                    {
                        type: "SHAPE",
                        shape : "RECTANGLE",
                        x : action.x,
                        y : action.y,
                        color : "orange"
                    }
                ]
            }
        case "ADD_TRIANGLE":
            return {
                ...state,
                activekey: action.key,
                discoveries: [
                    ...state.discoveries,
                    {
                        type: "SHAPE",
                        shape: "TRIANGLE",
                        x : action.x,
                        y : action.y,
                        color : "orange"
                    }
                ]
            }
        case "ADD_IMAGE":
            return {
                ...state,
                activekey: action.key,
                discoveries: [
                    ...state.discoveries,
                    {
                        type : "IMAGE",
                        x : action.x,
                        y : action.y,
                    }
                ]
            }
        case "GET_JOURNEY":
            return {
                ...state,
                ...action.journey,
                discoveries: action.journey.discoveries && !action.journey.discoveries.length ? [
                    {
                        url: "",
                        shape: "UNKNOWN",
                        text: "",
                        color: "FFF",
                        type: "TRIGGER_IMAGE",
                        x: 0.5 * (window.innerWidth - 300),
                        y: 0.5 * (window.innerHeight - 300),
                    }] : action.journey.discoveries
            }
        case "RESET_TRIGGER_IMAGE":
            return {
                ...state,
                activekey: 0,
                discoveries: [
                    {
                        type: "TRIGGER_IMAGE",
                        x : action.x,
                        y : action.y,
                    }
                ]
            }
        case "DELETE_EVENT":
            return {
                ...state,
                activekey: action.key,
                discoveries: state.discoveries.filter((events , index) => index !== action.key)
            }
        // case "UPDATE_EVENT":
        //     return {}
        // case "SAVE_JOURNEY":
        //     return {}
        case "SET_ACTIVE_KEY":
            return {
                ...state,
                activekey: action.key
            }
        case "MOVE_OBJECT":
            const {objKey, x, y} = action.obj
            const discovery = state.discoveries.find((_obj, idx) => idx === objKey)
            discovery.x = x;
            discovery.y = y;
            state.discoveries[objKey] = discovery
            return state
        case "UPDATE_TITLE":
            return {
                ...state,
                title: action.title
            }
        default:
            return state
    }
}