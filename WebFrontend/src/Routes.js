import React from 'react';
import { BrowserRouter, Switch, Route } from 'react-router-dom';
import AlarmDashboard from './AlarmDashboard';

const Routes = () => {
    return (
        <BrowserRouter>
            <Switch>
                <Route path="/" exact component={AlarmDashboard}></Route>
            </Switch>
        </BrowserRouter>
    );
};

export default Routes;