import React from "react"
import {render} from "@testing-library/react"
import {EditorButton} from "../components/editor/EditorButton"

test("shows if EditorButton component exists", () => {
    const component = render(<EditorButton/>)
    expect(component).toBeDefined()
})
