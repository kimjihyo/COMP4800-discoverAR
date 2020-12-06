import React, {useState} from "react"
import Button from "@material-ui/core/Button"
import CircularProgress from "@material-ui/core/CircularProgress"
import Grid from "@material-ui/core/Grid"
import TextField from "@material-ui/core/TextField"
import {setAccessToken} from "@modules/api"
import {useHistory} from "react-router-dom"
import "./Register.scss"
import { Typography } from "@material-ui/core"

export const BaseRegister = ({registerUser}) => {
    const history = useHistory()
    const [username, setUsername] = useState('')
    const [email, setEmail] = useState('')
    const [password, setPassword] = useState('')
    const [isFetching, setIsFetching] = useState(false)

    const handleSubmit = e => {
        e.preventDefault()
        setIsFetching(true)
        registerUser({username, email, password})
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
            className={"register__container"}
        >
            <form noValidate>
                <Typography variant={"h2"}>
                    Register an Account
                </Typography>
                <TextField
                    variant="outlined"
                    margin="normal"
                    required
                    fullWidth
                    name="username"
                    label="Username"
                    type="text"
                    id="usernameOrEmail"
                    value={username}
                    onInput={e => setUsername(e.target.value)}
                />
                <TextField
                    variant="outlined"
                    margin="normal"
                    required
                    fullWidth
                    name="email"
                    label="Email"
                    type="text"
                    id="email"
                    value={email}
                    onInput={e => setEmail(e.target.value)}
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
                    {isFetching ? <CircularProgress/> : 'Sign up'}
                </Button>
            </form>
        </Grid>
    )
}
