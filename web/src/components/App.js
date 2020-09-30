import React, { useEffect, useState } from "react"
import { connect } from "react-redux"
import {BrowserRouter as Router, Switch, Route, Redirect} from "react-router-dom"
import CircularProgress from '@material-ui/core/CircularProgress'
import {NavBar} from "./nav"
import {authenticatedRoutes, publicRoutes, routes} from "./routes"
import { ErrorComponent } from "./404"
import {checkSessionAction} from "@modules/redux"
import "./App.scss"

const BaseApp = ({ checkSession, isAuthenticated }) => {
    const [isLoading, setIsLoading] = useState(true)

    useEffect(() => {
        checkSession().finally(() => setIsLoading(false))
    }, [])

    return isLoading ? (<CircularProgress />) :
        (
        <Router>
            <div className={"main__container"}>
                <NavBar/>
                <Switch>
                    {[
                        ...publicRoutes.map(({path, Component}) => (
                            <Route exact key={path} path={path} component={Component}/>
                        )),
                        ...authenticatedRoutes.map(({path, Component}) => (
                            <Route exact key={path} path={path} component={props => isAuthenticated ?
                                <Component {...props } /> : <Redirect to="/login" />}
                            />
                        )),
                        <Route component={ErrorComponent} />,
                    ]}
                </Switch>
            </div>
        </Router>
    )
}

const mapStateToProps = state => ({
    isAuthenticated: state.auth.isAuthenticated
})

const mapDispatchToProps = dispatch => ({
    checkSession: () => checkSessionAction()(dispatch),
})

export const App = connect(mapStateToProps, mapDispatchToProps)(BaseApp)
