import React from "react"
import { About } from "./about"
import { Login, Register } from "./auth"
import { Canvas } from "./editor"
import { Dashboard } from "./dashboard"
import { Home } from "./home"
import { Users} from "./users"
import {MockGallery} from "./vuforia"

const aboutRoute = {
    Component: About,
    name: "About",
    path: "/about",
}

const canvasRoute = {
    Component: Canvas,
    name: "Canvas",
    path: "/canvas/:journeyId"
}

const dashboardRoute = {
    Component: Dashboard,
    name: "Dashboard",
    path: "/dashboard",
}

const homeRoute = {
    Component: Home,
    name: "Home",
    path: "/",
}

const loginRoute = {
    Component: Login,
    name: "Login",
    path: "/login",
}

const registerRoute = {
    Component: Register,
    name: "Register",
    path: "/register",
}

const usersRoute = {
    Component: Users,
    name: "Users",
    path: "/users",
}

const vuforiaRoute = {
    Component: MockGallery,
    name: "Vuforia",
    path: "/vuforia"
}

export const routes = [
    aboutRoute,
    dashboardRoute,
    homeRoute,
    loginRoute,
    registerRoute,
    usersRoute,
    vuforiaRoute
]

export const navBarAuthenticatedRoutes = [
    dashboardRoute,
    vuforiaRoute
]

export const authenticatedRoutes = [
    dashboardRoute,
    usersRoute,
    canvasRoute,
    vuforiaRoute
]

export const publicRoutes = [
    homeRoute,
    aboutRoute,
    loginRoute,
    registerRoute,
]
