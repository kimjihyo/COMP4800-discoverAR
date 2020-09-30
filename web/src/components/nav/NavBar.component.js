import React from "react"
import {Link} from "react-router-dom"
import {Typography} from "@material-ui/core"
import {navBarAuthenticatedRoutes, publicRoutes} from "../routes"
import "./NavBar.scss"

const SignoutButton = ({onClick}) => (
    <Typography onClick={onClick}>
        Sign out
    </Typography>
)

export const BaseNavBar = ({isAuthenticated, signOut}) => {
    const handleSignout = () => {
        signOut()
    }

    const activeRoutes = isAuthenticated ?
        [
            ...navBarAuthenticatedRoutes,
            {
                Component: () => <SignoutButton onClick={handleSignout}/>
            }
        ] :
        publicRoutes

    return (
        <nav className={"nav__container"}>
            <ul>
                {activeRoutes
                    .map(({path, name, Component}, idx) => {
                        return !(path && name) ? (
                                <li key={idx}>
                                    <Component/>
                                </li>
                            ) :
                            (
                                <li key={name}>
                                    <Link to={path}>
                                        <Typography
                                            align={"center"}
                                        >
                                            {name}
                                        </Typography>
                                    </Link>
                                </li>
                            )
                    })}
            </ul>
        </nav>
    )
}
