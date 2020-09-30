export const setAccessToken = accessToken => {
    window.localStorage.setItem("Authorization", `Bearer ${accessToken}`)
}

export const apiAction = (action, body) => {
    const authKey = window.localStorage.getItem("Authorization")
    return fetch(
        action.route,
        Object.assign(
            {
                cache: "no-cache", // *default, no-cache, reload, force-cache, only-if-cached
                credentials: "same-origin", // include, *same-origin, omit
                headers: Object.assign({
                        "Content-Type": "application/json",
                    },
                    !!authKey &&
                    {"Authorization": authKey}),
                method: action.method,
                mode: "cors", // no-cors, *cors, same-origin
                redirect: "follow", // manual, *follow, error
                referrer: "no-referrer", // no-referrer, *client
            },
            action.method !== "GET" && body &&
            {
                body: JSON.stringify(body), // body data type must match "Content-Type" header
            }))
        .then(res => res.json())
}
