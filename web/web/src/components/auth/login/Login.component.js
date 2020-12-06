import React, {useState} from "react"
import Button from "@material-ui/core/Button"
import CircularProgress from "@material-ui/core/CircularProgress"
import Grid from "@material-ui/core/Grid"
import TextField from "@material-ui/core/TextField"
import {setAccessToken} from "@modules/api"
import {useHistory} from "react-router-dom"
import "./Login.scss"
import { Typography } from "@material-ui/core"

export const BaseLogin = ({authenticateUser}) => {
    const history = useHistory()
    const [password, setPassword] = useState("")
    const [usernameOrEmail, setUsernameOrEmail] = useState("")
    const [isFetching, setIsFetching] = useState(false)

    const handleSubmit = e => {
        e.preventDefault()
        setIsFetching(true)
        authenticateUser({usernameOrEmail, password})
            .then(res => {
                setAccessToken(res.accessToken)
                history.push("/dashboard")
            })
            .catch(() => {
                throw new Error("found error")
            })
            .finally(() => {
                setIsFetching(false)
            })
    }

    return (
            <Grid
                container
                justify={"center"}
                alignItems={"center"}
                className={"login__container"}
            >
                <form noValidate>
                    <Typography variant={"h1"}>
                        Login
                    </Typography>
                    <TextField
                        variant="outlined"
                        margin="normal"
                        required
                        fullWidth
                        name="usernameOrEmail"
                        label="Username or Email"
                        type="text"
                        id="usernameOrEmail"
                        value={usernameOrEmail}
                        onInput={e => setUsernameOrEmail(e.target.value)}
                    />
                    <TextField
                        variant="outlined"
                        margin="normal"
                        required
                        fullWidth
                        name="password"
                        label="Password"
                        type="password"
                        id="password"
                        autoComplete="current-password"
                        value={password}
                        onInput={e => setPassword(e.target.value)}
                    />
                    <Button
                        type="button"
                        fullWidth
                        variant={isFetching ? "disabled" : "contained"}
                        color="primary"
                        onClick={handleSubmit}
                    >
                        {isFetching ? <CircularProgress /> : 'Sign In'}
                    </Button>
                </form>
            </Grid>
    )
}
