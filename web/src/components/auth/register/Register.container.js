import {connect} from "react-redux"
import {BaseRegister} from "./Register.component"
import {registerUserAction} from "@modules/redux"

const mapStateToProps = state => ({})

const mapDispatchToProps = dispatch => ({
    registerUser: user => registerUserAction(user)(dispatch)
})

export const Register = connect(mapStateToProps, mapDispatchToProps)(BaseRegister)