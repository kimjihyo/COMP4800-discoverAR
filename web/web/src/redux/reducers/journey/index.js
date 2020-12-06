import {
    CREATE_JOURNEY_PENDING,
    CREATE_JOURNEY_SUCCESS,
    CREATE_JOURNEY_ERROR,
    DELETE_JOURNEY_PENDING,
    DELETE_JOURNEY_SUCCESS,
    DELETE_JOURNEY_ERROR,
    GET_JOURNEY_PENDING,
    GET_JOURNEY_SUCCESS,
    GET_JOURNEY_ERROR,
    GET_JOURNEYS_PENDING,
    GET_JOURNEYS_SUCCESS,
    GET_JOURNEYS_ERROR,
    UPDATE_JOURNEY_PENDING,
    UPDATE_JOURNEY_SUCCESS,
    UPDATE_JOURNEY_ERROR
} from "../../types"

export const journeysReducer = (state, action) => {
    switch(action.type) {
        case CREATE_JOURNEY_PENDING:
        case DELETE_JOURNEY_PENDING:
        case GET_JOURNEY_PENDING:
        case GET_JOURNEYS_PENDING:
        case UPDATE_JOURNEY_PENDING:
            return ({
                ...state,
                pending: true
            })
        case CREATE_JOURNEY_ERROR:
        case DELETE_JOURNEY_ERROR:
        case GET_JOURNEY_ERROR:
        case GET_JOURNEYS_ERROR:
        case UPDATE_JOURNEY_ERROR:
            return ({
                ...state,
                error: action.error,
                pending: false,
            })
        case CREATE_JOURNEY_SUCCESS:
            return ({
                ...state,
                activeJourney: action.journey,
                error: null,
                pending: false,
            })
        case GET_JOURNEY_SUCCESS:
            return ({
                ...state,
                activeJourney: action.journey,
                error: null,
                pending: false
            })
        case GET_JOURNEYS_SUCCESS:
            return ({
                ...state,
                userJourneys: action.journeys,
                error: null,
                pending: false
            })
        case UPDATE_JOURNEY_SUCCESS:
            return ({
                ...state,
                activeJourney: action.journey,
                error: null,
                pending: false,
            })
        case DELETE_JOURNEY_SUCCESS:
            return ({
                ...state,
                error: null,
                pending: false
            })
        default:
            return state
    }
}