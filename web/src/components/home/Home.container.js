import { connect } from "react-redux"
import { BaseHome } from "./Home.component"

const mapStateToProps = state => ({
    isLoggedIn: state.isLoggedIn,
})

export const Home = connect(mapStateToProps)(BaseHome)
