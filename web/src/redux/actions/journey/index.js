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

import {
    apiAction,
    createJourney,
    getAllJourneys,
    getOneJourney,
    removeJourney,
    updateJourney
} from "@modules/api"

export const createJourneyPending = () => ({
    type: CREATE_JOURNEY_PENDING
})

export const createJourneySuccess = journey => ({
    journey,
    type: CREATE_JOURNEY_SUCCESS
})

export const createJourneyError = error => ({
    error,
    type: CREATE_JOURNEY_ERROR
})

export const deleteJourneyPending = () => ({
    type: DELETE_JOURNEY_PENDING
})

export const deleteJourneySuccess = () => ({
    type: DELETE_JOURNEY_SUCCESS
})

export const deleteJourneyError = error => ({
    error,
    type: DELETE_JOURNEY_ERROR
})

export const getAllJourneysPending = () => ({
    type: GET_JOURNEYS_PENDING
})

export const getAllJourneysSuccess = journeys => ({
    journeys,
    type: GET_JOURNEYS_SUCCESS
})

export const getAllJourneysError = error => ({
    error,
    type: GET_JOURNEYS_ERROR
})

export const getOneJourneyPending = () => ({
    type: GET_JOURNEY_PENDING
})

export const getOneJourneySuccess = journey => ({
    journey,
    type: GET_JOURNEY_SUCCESS
})

export const getOneJourneyError = error => ({
    error,
    type: GET_JOURNEY_ERROR
})

export const updateJourneyPending = () => ({
    type: UPDATE_JOURNEY_PENDING
})

export const updateJourneySuccess = journey => ({
    journey,
    type: UPDATE_JOURNEY_SUCCESS
})

export const updateJourneyError = error => ({
    error,
    type: UPDATE_JOURNEY_ERROR
})

export const createJourneyAction = () => dispatch => {
    dispatch(createJourneyPending())
    return apiAction(createJourney)
        .then(res => {
            if (res.error) {
                throw res.error
            }
            dispatch(createJourneySuccess(res))
            return res
        })
        .catch(error => {
            dispatch(createJourneyError(error))
            throw error
        })
}


export const deleteJourneyAction = id => dispatch => {
    dispatch(deleteJourneyPending())
    return apiAction(removeJourney(id))
        .then(res => {
            if (res.error) {
                throw res.error
            }
            dispatch(deleteJourneySuccess())
            return res
        })
        .catch(error => {
            dispatch(deleteJourneyError(error))
            throw error
        })
}

export const getAllJourneysAction = () => dispatch => {
    dispatch(getAllJourneysPending())
    return apiAction(getAllJourneys)
        .then(res => {
            if (res.error) {
                throw res.error
            }
            dispatch(getAllJourneysSuccess(res))
            return res
        })
        .catch(error => {
            dispatch(getAllJourneysError(error))
            throw error
        })
}

export const getOneJourneyAction = id => dispatch => {
    dispatch(getOneJourneyPending())
    return apiAction(getOneJourney(id))
        .then(res => {
            if (res.error) {
                throw res.error
            }
            dispatch(getOneJourneySuccess(res))
            return res
        })
        .catch(error => {
            dispatch(getOneJourneyError(error))
            throw error
        })
}

export const updateJourneyAction = body => dispatch => {
    dispatch(updateJourneyPending())
    return apiAction(updateJourney(body.id), body)
        .then(res => {
            if (res.error) {
                throw res.error
            }
            dispatch(updateJourneySuccess(res))
            return res
        })
        .catch(error => {
            dispatch(updateJourneyError(error))
            throw error
        })
}
