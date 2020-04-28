import React, { useState, useEffect } from 'react';
import { API } from './config';
import Card from './cardLayout';

const getSensorDetails = () => {
    return fetch(`${API}/getSensors`, {
        method: "GET"
    })
        .then(response => {
            return response.json();
        })
        .catch(err => console.log(err));
};



const AlarmDashboard = () => {
    const [alarms, setAlarms] = useState([]);
    const [error, setError] = useState(false);


    const loadAlarms = () => {
        getSensorDetails().then(data => {
            if (data.error) {
                setError(data.error)
            } else {
                setAlarms(data);
            }
        });
    }

    useEffect(() => {
        const interval = setInterval(function(){
            // window.location.reload(1);
            // console.log("reloading");
            loadAlarms();
        }, 40000);

        return () => clearInterval(interval);
    });

    useEffect(() => {
        loadAlarms();
    }, []);


    const showError = () => (
        <div className="alert alert-danger" style={{ display: error ? '' : 'none' }}>
            {error}
        </div>
    );


    return (
        <div className="container-fluid">
            <div className="jumbotron">
                <h2>Alarm Dashboard</h2>
                <p className="lead">Monitor all alarm statuses.</p>
            </div>
            {showError()}
            <div className="row">
                {alarms.map((alarm, i) => (
                    <Card key={i} alarm={alarm} />
                ))}
            </div>
        </div>
    );
};



export default AlarmDashboard;