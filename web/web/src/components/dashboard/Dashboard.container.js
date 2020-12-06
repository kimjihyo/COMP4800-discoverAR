import {connect} from "react-redux"
import {createJourneyAction, deleteJourneyAction, getAllJourneysAction} from "@modules/redux"
import {BaseDashboard} from "./Dashboard.component"

const mapStateToProps = state => ({
    journeys: state.journeys.userJourneys
})

const mapDispatchToProps = dispatch => ({
    createJourney: () => createJourneyAction()(dispatch),
    getJourneys: () => getAllJourneysAction()(dispatch),
    removeJourney: id => deleteJourneyAction(id)(dispatch)
})

export const Dashboard = connect(mapStateToProps, mapDispatchToProps)(BaseDashboard)