import React, {useEffect, useState} from 'react';
import {API} from './config';

const UpdateAlarm = ({match}) => {
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


    useEffect(() => {
        const interval = setInterval(() => {

            setValues({...values, error: "", success: ""});

            if (smoke > 10 || smoke < 0) {
                setValues({...values, error: "Smoke level must be between 0 and 10", success: ""});
            } else if (co2 > 10 || co2 < 0) {
                setValues({...values, error: "CO2 level must be between 0 and 10", success: ""});
            } else {
                updateSensor(values).then(data => {
                    if (data.error) {
                        setValues({...values, error: data.error, success: false});
                    } else {
                        setValues({
                            ...values,
                            sensorId: values.sensorId,
                            smoke: values.smoke,
                            co2: values.co2,
                            error: false,
                        });
                        setValues({...values, error: false, success: "Sensor Values Automatically Updated!"});

                    }
                });
            }
        }, 10000);
        return () => clearInterval(interval);
    });

    const updateSensor = () => {
        console.log(values);
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
        console.log("here");
        init(match.params.sensorId, match.params.smoke, match.params.co2);
    }, []);

    const handleChange = (event) => {
        const value = event.target.value;
        const name = event.target.name;
        setValues({...values, [name]: value});
        console.log(values);
    };


    const clickSubmit = event => {
        event.preventDefault();
        setValues({...values, error: ""});
        setValues({...values, error: "", success: ""});

        if (smoke > 10 || smoke < 0) {
            setValues({...values, error: "Smoke level must be between 0 and 10"});
        } else if (co2 > 10 || co2 < 0) {
            setValues({...values, error: "CO2 level must be between 0 and 10"});
        } else {
            updateSensor(values).then(data => {
                if (data.error) {
                    setValues({...values, error: data.error, success: false});
                } else {
                    setValues({
                        ...values,
                        sensorId: values.sensorId,
                        smoke: values.smoke,
                        co2: values.co2,
                        error: false,
                    });
                    setValues({...values, error: false, success: "Sensor Values Updated!"});

                }
            });
        }
    };


    const newPostForm = (values) => (
        <form className="mb-3" onSubmit={clickSubmit}>

            <div className="form-group">
                <label className="text-muted">Sensor ID</label>
                <input
                    type="number"
                    className="form-control"
                    value={values.sensorId}
                    name="sensorId"
                    disabled={true}/>
            </div>

            <div className="form-group">
                <label className="text-muted">Smoke Status</label>
                <input
                    onChange={handleChange}
                    type="number"
                    className="form-control"
                    min="0" max="10"
                    name="smoke"
                    value={values.smoke}/>
            </div>

            <div className="form-group">
                <label className="text-muted">CO2 Status</label>
                <input
                    onChange={handleChange}
                    type="number"
                    className="form-control"
                    min="0" max="10"
                    name="co2"
                    value={values.co2}/>
            </div>

            <button className="btn btn-dark">Update Sensor</button>
        </form>
    );

    const showError = () => (
        <div>{(error != '') ? <div className="alert alert-danger alert-dismissible">
            {error}
        </div> : ''}

        </div>

    );

    const showSuccess = () => (
        <div>{(success != '') ? <div className="alert alert-success">
            {success}
        </div> : ''}

        </div>

    );

    return (
        <div className="row">

                <div className="container">

                    <div className="jumbotron my-5 mx-2">
                        <h2>Update Sensor Status</h2>
                        <br/>
                        {showSuccess()}
                        {showError()}
                        {newPostForm(values)}
                        <div>
                            <span>Note: Sensor status will be updated automatically every 10 seconds. You can manually update by clicking Update Sensor button</span>
                        </div>
                    </div>
                </div>



        </div>
    );
}

export default UpdateAlarm;