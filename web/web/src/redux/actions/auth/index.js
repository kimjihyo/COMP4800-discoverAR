import {
    AUTHENTICATE_USER_PENDING,
    AUTHENTICATE_USER_SUCCESS,
    AUTHENTICATE_USER_ERROR,
    CHECK_AUTH_PENDING,
    CHECK_AUTH_SUCCESS,
    CHECK_AUTH_ERROR,
    REGISTER_USER_PENDING,
    REGISTER_USER_SUCCESS,
    REGISTER_USER_ERROR,
    SIGNOUT_USER_PENDING,
    SIGNOUT_USER_SUCCESS,
    SIGNOUT_USER_ERROR
} from "@modules/redux"

import {apiAction, login, refresh, register} from "@modules/api"

export const authenticateUserPending = () => ({
    type: AUTHENTICATE_USER_PENDING,
})

export const authenticateUserSuccess = isAuthenticated => ({
    isAuthenticated,
    type: AUTHENTICATE_USER_SUCCESS,
})

export const authenticateUserError = error => ({
    error,
    type: AUTHENTICATE_USER_ERROR,
})

export const authenticateSessionPending = () => ({
    type: CHECK_AUTH_PENDING,
})

export const authenticateSessionSuccess = isAuthenticated => ({
    isAuthenticated,
    type: CHECK_AUTH_SUCCESS,
})

export const authenticateSessionError = error => ({
    error,
    type: CHECK_AUTH_ERROR,
})

export const registerUserPending = () => ({
    type: REGISTER_USER_PENDING
})

export const registerUserSuccess = isAuthenticated => ({
    isAuthenticated,
    type: REGISTER_USER_SUCCESS
})

export const registerUserError = error => ({
    error,
    type: REGISTER_USER_ERROR
})

export const signoutUserPending = () => ({
    type: SIGNOUT_USER_PENDING
})

export const signoutUserSuccess = isAuthenticated => ({
    isAuthenticated,
    type: SIGNOUT_USER_SUCCESS
})

export const signoutUserError = error => ({
    error,
    type: SIGNOUT_USER_ERROR
})

export const authenticateUserAction = body => {
    return dispatch => {
        dispatch(authenticateUserPending())
        return apiAction(login, body)
            .then(res => {
                if (res.error) {
                    throw (res.error)
                }
                dispatch(authenticateUserSuccess(!!res.accessToken))
                return res
            })
            .catch(error => {
                dispatch(authenticateUserError(error))
                throw error
            })
    }
}

export const checkSessionAction = () => {
    return dispatch => {
        dispatch(authenticateSessionPending())
        return apiAction(refresh)
            .then(res => {
                if (res.error || (res.success === "false")) {
                    throw (res.error || res.message)
                }
                dispatch(authenticateSessionSuccess(!!res.accessToken))
                return res
            })
            .catch(error => {
                dispatch(authenticateSessionError(error))
                throw error
            })
    }
}

export const registerUserAction = body => {
    return dispatch => {
        dispatch(registerUserPending())
        return apiAction(register, body)
            .then(res => {
            if (res.error) {
                throw (res.error)
            }
            dispatch(registerUserSuccess(!!res.accessToken))
            return res
        })
            .catch(error => {
                dispatch(registerUserError(error))
                throw error
            })
    }
}

export const signoutUserAction = () => {
    return dispatch => {
        dispatch(signoutUserPending())
        localStorage.clear()
        if (localStorage.length === 0) {
            dispatch(signoutUserSuccess(false))
        } else {
            dispatch(signoutUserError("Error removing access token"))
        }
    }
}
