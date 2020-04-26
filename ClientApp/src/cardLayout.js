import React from 'react';
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

const isInactive = (val) => {
    if (!val) {      
        return { color: "#C6C6C6" }
    }
};

const Card = ({ alarm }) => {
    return (
        <div className="col-4 mb-3">
            <div className="card" style={isInactive(alarm.active)}>
                <div className="card-header" style={isDanger(alarm.co2) || isDanger(alarm.smoke)}>Floor No: {alarm.floorNo} - Room No: {alarm.roomNo}</div>
                <div className="card-body">
                    <p>Sensor Id: {alarm.sensorId}</p>
                    <p>Status: {isStatus(alarm.active)}</p>
                    <p style={isDanger(alarm.smoke)}>Smoke Level: {alarm.smoke}</p>
                    <p style={isDanger(alarm.co2)}>CO2 Level: {alarm.co2}</p>
                    <Link to={`/admin/updateSensor/${alarm.sensorId}/${alarm.smoke}/${alarm.co2}`} target="_blank">
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