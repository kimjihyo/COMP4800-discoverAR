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
} from "../../types/auth"

export const authenticationReducer = (state, action) => {
    switch (action.type) {
        case AUTHENTICATE_USER_PENDING:
        case CHECK_AUTH_PENDING:
        case REGISTER_USER_PENDING:
        case SIGNOUT_USER_PENDING:
            return ({
                ...state,
                pending: true,
            })
        case AUTHENTICATE_USER_SUCCESS:
        case CHECK_AUTH_SUCCESS:
        case REGISTER_USER_SUCCESS:
        case SIGNOUT_USER_SUCCESS:
            return ({
                ...state,
                isAuthenticated: action.isAuthenticated,
                pending: false,
            })
        case AUTHENTICATE_USER_ERROR:
        case CHECK_AUTH_ERROR:
        case REGISTER_USER_ERROR:
        case SIGNOUT_USER_ERROR:
            return ({
                ...state,
                error: action.error,
                pending: false,
            })
        default:
            return state
    }
}
