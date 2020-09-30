import { connect } from "react-redux"
import { BaseLogin } from "./Login.component"
import {authenticateUserAction} from "@modules/redux"

const mapStateToProps = state => ({})

const mapDispatchToProps = dispatch => ({
    authenticateUser: user => authenticateUserAction(user)(dispatch),
})

export const Login = connect(mapStateToProps, mapDispatchToProps)(BaseLogin)
