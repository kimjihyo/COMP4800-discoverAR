import { connect } from "react-redux"
import {signoutUserAction} from "@modules/redux"
import { BaseNavBar } from "./NavBar.component"

const mapStateToProps = state => ({
    isAuthenticated: state.auth.isAuthenticated,
})

const mapDispatchToProps = dispatch => ({
    signOut: () => signoutUserAction()(dispatch)
})

export const NavBar = connect(mapStateToProps, mapDispatchToProps)(BaseNavBar)
