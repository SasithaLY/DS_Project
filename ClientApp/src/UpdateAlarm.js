import React, { useState, useEffect } from 'react';
import { API } from './config';

const UpdateAlarm = ({ match }) => {
    const [values, setValues] = useState({
        sensorId: "",
        smoke: "",
        co2: "",
        error: false,
        success: false
    });

    const {
        sensorId,
        smoke,
        co2,
        error,
        success
    } = values;

    const updateSensor = (sensorId, smoke, co2) => {
        return fetch(`${API}/setSensorLevels`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                sensorId: sensorId,
                smoke: smoke,
                co2: co2
            })
        }).then(response => {
            return response.json();
        })
            .catch(err => console.log(err));
    };

    const init = (sensorId, smoke, co2) => {
        setValues({
            ...values,
            sensorId: sensorId,
            smoke: smoke,
            co2: co2,
        })
    };

    useEffect(() => {
        init(match.params.sensorId, match.params.smoke, match.params.co2);
    }, []);

    const handleChange = name => event => {
        const value = event.target.value;
        setValues({ ...values, [name]: value });
    };

    const clickSubmit = event => {
        event.preventDefault();
        setValues({ ...values, error: "" });

        if (smoke > 10 || smoke < 0) {
            setValues({ ...values, error: "Smoke level must be greater than 0 or less than 10" });
        }
        else if (co2 > 10 || co2 < 0) {
            setValues({ ...values, error: "CO2 level must be greater than 0 or less than 10" });
        }
        else {
            // setTimeout(() => {
            //     updateSensor(sensorId, smoke, co2)
            //   }, 10000);

            // setValues({
            //     ...values,
            //     sensorId: "",
            //     smoke: "",
            //     co2: "",
            //     error: false,
            //     redirectToDashboard: true
            // });

            updateSensor(sensorId, smoke, co2).then(data => {
                if (data.error) {
                    setValues({ ...values, error: data.error, success: false });
                } else {
                    setValues({
                        ...values,
                        sensorId: sensorId,
                        smoke: smoke,
                        co2: co2,
                        error: false,
                    });
                    setValues({ ...values, error: false, success: "Sensor Values Updated!" });
                    setTimeout(() => {
                        setValues({ ...values, error: false, success: false });
                    }, 2000);
                }
            });
        }
    };




    const newPostForm = () => (
        <form className="mb-3" onSubmit={clickSubmit}>

            <h2>Update Sensor Status</h2> <br />

            <div className="form-group">
                <label className="text-muted">Sensor Status</label>
                <input
                    type="number"
                    className="form-control"
                    value={sensorId}
                    disabled={true} />
            </div>

            <div className="form-group">
                <label className="text-muted">Sensor Status</label>
                <input
                    onChange={handleChange("smoke")}
                    type="number"
                    className="form-control"
                    min="0" max="10"
                    value={smoke} />
            </div>

            <div className="form-group">
                <label className="text-muted">Sensor Status</label>
                <input
                    onChange={handleChange("co2")}
                    type="number"
                    className="form-control"
                    min="0" max="10"
                    value={co2} />
            </div>

            <button className="btn btn-dark">Update Sensor</button>
        </form>
    );

    const showError = () => (
        <div className="alert alert-danger" style={{ display: error ? '' : 'none' }}>
            {error}
        </div>
    );

    const showSuccess = () => (
        <div className="alert alert-success" style={{ display: success ? '' : 'none' }}>
            {success}
        </div>
    );

    return (
        <div className="row">
            <div className="col-md-8 offset-md-2">
                <div className="container-fluid">
                    <div className="jumbotron">
                        {showSuccess()}
                        {showError()}
                        {newPostForm()}
                    </div>
                </div>


            </div>
        </div>
    );
}

export default UpdateAlarm;