const BASE_ROUTE = "/api/journeys/"

export const createJourney = ({
    method: "POST",
    route: BASE_ROUTE + "create",
})

export const getAllJourneys = ({
    method: "GET",
    route: BASE_ROUTE + "viewAll"
})

export const getOneJourney = id => ({
    method: "GET",
    route: BASE_ROUTE + id,
})

export const removeJourney = id => ({
    method: "DELETE",
    route: BASE_ROUTE + "delete/" + id
})


export const updateJourney = id => ({
    method: "PUT",
    route: BASE_ROUTE + "update/" + id,
})