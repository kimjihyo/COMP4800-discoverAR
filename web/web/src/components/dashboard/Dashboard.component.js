import React, { useEffect, useState } from "react"
import { Link, useHistory } from "react-router-dom"
import NewJourneyTab from './NewJourney'
import Button from "@material-ui/core/Button"
import ButtonGroup from "@material-ui/core/ButtonGroup"
import Card from "@material-ui/core/Card"
import Grid from "@material-ui/core/Grid"
import CardContent from "@material-ui/core/CardContent"
import CircularProgress from '@material-ui/core/CircularProgress'
import Typography from "@material-ui/core/Typography"
import Fab from '@material-ui/core/Fab'
import AddIcon from '@material-ui/icons/Add'
import "./dashboard.scss"

const UserJourneys = ({ journeys, removeJourney }) => (

    <div className={'grid-container'}>
    <Grid container direction="row" justify="left" alignItems="flex-start" spacing={5}>
        {journeys.map(journey => (
            <Grid item>
                <Card>
                    <CardContent>
                        <Typography variant="h6">
                            {journey.title}
                        </Typography>
                        <ButtonGroup>
                            <Button component={Link} color="primary" to={`/canvas/${journey.id}`}>
                                Edit
                            </Button>
                            <Button color="secondary" onClick={() => removeJourney(journey.id)}>
                                Delete
                            </Button>
                        </ButtonGroup>
                    </CardContent>
                </Card>
            </Grid>
        ))}
    </Grid>
    </div>)

export const BaseDashboard = ({ createJourney, getJourneys, journeys, removeJourney }) => {
    const [isLoading, setIsLoading] = useState(true)
    let creatingJourneyGg = 0
    useEffect(() => {
        getJourneys().finally(res => setIsLoading(false))
    }, [])

    const history = useHistory()

    const showNewJourneyOverlay = () => {
        document.getElementById('overlay').style.display = "block";
    }

    const handleCreateJourney = () => {
        creatingJourneyGg++;
        if (creatingJourneyGg == 1) {
            createJourney()
                .then(res => {
                    creatingJourneyGg = 0;
                    history.push(`/canvas/${res.id}`)
                })
        }
    }

    const handleRemoveJourney = id => {
        removeJourney(id)
            .then(() => {
                getJourneys()
            })
    }
    
    
    return isLoading ? (
        <>
            <Typography>
                Loading journeys...
                </Typography>
            <CircularProgress />
        </>
    ) :
        (
            <div id="dashboard-main">
                <div id="main">
                    <Typography variant="h5" className={'title-container'}>
                        Existing Projects
                    </Typography>
                    <div className={'fab-container'}>
                        <Fab color="primary" onClick={showNewJourneyOverlay}>
                            <AddIcon />
                        </Fab>
                    </div>
                    <UserJourneys journeys={journeys} removeJourney={handleRemoveJourney} />
                </div>
                <div id="overlay">
                    <NewJourneyTab />
                    <button onClick={handleCreateJourney} class="create-journey-button" >Create Journey!</button>
                </div>
            </div>
        )
}
