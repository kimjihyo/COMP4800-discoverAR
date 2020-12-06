export const PopupIconList = ({activeKey, dispatch}) => [
    {
        type: "ADD_CIRCLE",
        description: "Add Circle",
        onClick: () => dispatch({
            type: "ADD_CIRCLE",
            x: 200,
            y: 200,
            key: activeKey
        })
    },
    {
        type: "ADD_RECTANGLE",
        description: "Add Rectangle",
        onClick: () => dispatch({
            type: "ADD_RECTANGLE",
            x: 200,
            y: 200,
            key: activeKey
        })
    },
    {
        type: "ADD_TRIANGLE",
        description: "Add Triangle",
        onClick: () => dispatch({
            type: "ADD_TRIANGLE",
            x: 200,
            y: 200,
            key: activeKey
        })
    },
    {
        type: "ADD_IMAGE",
        description: "Add Image",
        onClick: () => dispatch({
            type: "ADD_IMAGE",
            x: 200,
            y: 200,
            key: activeKey
        })
    }
]

// export const ToolbarList = ({ activeKey, handleAdd, handleDelete, handleSave, handleReturn }) => [
export const ToolbarList = ({ toolBarProps }) => {
    const { activeKey, handleAdd, handleDelete, handleSave, handleReturn } = toolBarProps
    return [
        {
            type: "ADD_EVENT",
            description: "Add Event",
            onClick: () => handleAdd()
        },
        {
            type: "DELETE_EVENT",
            description: "Delete Event",
            onClick: () => handleDelete(activeKey)
        },
        {
            type: "SAVE_JOURNEY",
            description: "Save Journey",
            onClick: () => handleSave()
        },
        {
            type: "RETURN_TO_DASHBOARD",
            description: "Dashboard",
            onClick: () => handleReturn()
        }
    ]
}