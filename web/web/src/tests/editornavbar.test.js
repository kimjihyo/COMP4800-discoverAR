import React from "react"
import {render} from "@testing-library/react"
import {EditorNavBar} from "../components/editor/EditorNavBar"

test("shows if EditorNavBar exists", () => {
    const component = render(<EditorNavBar/>)
    expect(component).toBeDefined()
})
