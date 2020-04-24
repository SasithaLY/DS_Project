import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';

const isDanger = (val) => {
    if (val > 5) {
        return { color: "#ff0000" }
    }
};

const isStatus = (val) => {
    if (val) {
        return "Active"
    }
    else {
        return "Inactive"
    }
};


const Card = ({ alarm }) => {
    return (
        <div className="col-4 mb-3">
            <div className="card">
                <div className="card-header">Floor No: {alarm.floorNo} - Room No: {alarm.roomNo}</div>
                <div className="card-body">
                    <p>Sensor Id: {alarm.sensorId}</p>
                    <p>Status: {isStatus(alarm.active)}</p>
                    <p style={isDanger(alarm.smoke)}>Smoke Level: {alarm.smoke}</p>
                    <p style={isDanger(alarm.co2)}>CO2 Level: {alarm.co2}</p>
                    <Link to={`/admin/updateSensor/${alarm.sensorId}`}>
                        <button className="btn btn-outline-warning mt-2 mb-2" >
                            Update Alarm Status
                        </button>
                    </Link>
                </div>
            </div>
        </div>
    )
};

export default Card;