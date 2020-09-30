import {authenticationReducer} from "./auth"
import {journeysReducer} from "./journey"

const initialState = {
    auth: {
        error: null,
        isAuthenticated: false,
        pending: false,
    },
    journeys: {
        activeJourney: {},
        pending: false,
        userJourneys: [],
        error: null
    },
}

export const appReducer = (state = initialState, action) => ({
    auth: authenticationReducer(state.auth, action),
    journeys: journeysReducer(state.journeys, action)
})
