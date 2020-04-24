import React, { useState, useEffect } from 'react';
import { API } from './config';
import { Redirect } from 'react-router-dom';

const updateProduct = ({ match }) => {
    const [values, setValues] = useState({
        smoke: "",
        co2: "",
        error: false,
        redirectToDashboard: false,
        formData: ""
    });

    const {
        smoke,
        co2,
        error,
        redirectToDashboard,
        formData
    } = values;

    const getSensor = sensorId => {
        return fetch(`${API}/admin/getSensor/${sensorId}`, {
            method: "GET"
        })
            .then(response => {
                return response.json();
            })
            .catch(err => console.log(err));
    };

    const updateSensor = (sensorId, sensor) => {
        return fetch(`${API}/admin/updateSensor/${sensorId}`, {
            method: "PUT",
            headers: {
                Accept: "application/json"
            },
            body: sensor
        })
            .then(response => {
                return response.json();
            })
            .catch(err => console.log(err));
    };

    const init = (sensorId) => {
        getSensor(sensorId).then(data => {
            if (data.error) {
                setValues({ ...values, error: data.error })
            } else {
                setValues({
                    ...values,
                    smoke: data.smoke,
                    co2: data.co2,
                    formData: new FormData()
                });
            }
        });
    };

    useEffect(() => {
        init(match.params.sensorId);
    }, []);

    const handleChange = name => event => {
        const value = event.target.value;
        formData.set(name, value);
        setValues({ ...values, [name]: value });
    };

    const clickSubmit = event => {
        event.preventDefault();
        setValues({ ...values, error: "", loading: true });

        updateSensor(match.params.sensorId, alarm.sensorId, formData).then(data => {
            if (data.error) {
                setValues({ ...values, error: data.error });
            } else {
                setValues({
                    ...values,
                    smoke: "",
                    co2: "",
                    error: false,
                    redirectToDashboard: true
                });
            }
        });
    };

    const newPostForm = () => (
        <form className="mb-3" onSubmit={clickSubmit}>

            {/* <div className="form-group">
                <p>{status}</p>
            </div> */}

            <div className="form-group">
                <label className="text-muted">Sensor Status</label>
                <input
                    onChange={handleChange("smoke")}
                    type="number"
                    className="form-control"
                    value={smoke} />
            </div>

            <div className="form-group">
                <label className="text-muted">Sensor Status</label>
                <input
                    onChange={handleChange("co2")}
                    type="number"
                    className="form-control"
                    value={co2} />
            </div>

            <button className="btn btn-outline-warning">Update Sensor</button>
        </form>
    );

    const showError = () => (
        <div className="alert alert-danger" style={{ display: error ? '' : 'none' }}>
            {error}
        </div>
    );

    const redirect = () => {
        if(redirectToDashboard) {
            if(!error) {
                return <Redirect to="/" />
            }
        }
    }

    return (
        <div className="row">
            <div className="col-md-8 offset-md-2">
                {showError()}
                {newPostForm()}
                {redirect()}
            </div>
        </div>
    );
}

export default updateProduct;