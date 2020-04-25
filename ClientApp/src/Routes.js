import React from 'react';
import { BrowserRouter, Switch, Route } from 'react-router-dom';
import AlarmDashboard from './AlarmDashboard';
import UpdateAlarm from './UpdateAlarm';

const Routes = () => {
    return (
        <BrowserRouter>
            <Switch>
                <Route path="/" exact component={AlarmDashboard}></Route>
                <Route path="/admin/updateSensor/:sensorId/:smoke/:co2" exact component={UpdateAlarm}></Route>
            </Switch>
        </BrowserRouter>
    );
};

export default Routes;