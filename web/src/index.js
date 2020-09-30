import React from "react"
import ReactDOM from "react-dom"
import {applyMiddleware, createStore} from "redux"
import logger from "redux-logger"
import {Provider} from "react-redux"
import {App} from "@modules/components"
import { appReducer } from "@modules/redux"

const env = process.env.NODE_ENV

const middleWares = [
    appReducer,
    env === "development" ?  applyMiddleware(logger) : null
]

const store = createStore(
    ...middleWares.filter(ware => !!ware)
)

ReactDOM.render(
    <Provider store={store}>
        <App/>
    </Provider>,
    document.getElementById("root"))
