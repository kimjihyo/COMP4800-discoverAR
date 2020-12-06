import { connect } from "react-redux"
import { BaseCanvas } from "./Canvas.component"
import {getOneJourneyAction, updateJourneyAction} from "@modules/redux"

const mapStateToProps = state => ({
    currentJourney: state.journeys.activeJourney
})

const mapDispatchToProps = dispatch => ({
    getJourney: id => getOneJourneyAction(id)(dispatch),
    saveJourney: journey => updateJourneyAction(journey)(dispatch),
})

export const Canvas = connect(mapStateToProps, mapDispatchToProps)(BaseCanvas)