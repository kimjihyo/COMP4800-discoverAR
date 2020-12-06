const BASE_ROUTE = "/api/auth/"

export const login = ({
    method: "POST",
    route: BASE_ROUTE + "signin",
})

export const refresh = ({
    method: "GET",
    route: BASE_ROUTE + "refresh",
})

export const register = ({
    method: "POST",
    route: BASE_ROUTE + "signup"
})

export const logout = () => Promise.resolve()

