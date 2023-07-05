import React, { useState } from 'react';
import './RegistrationForm.css'; // Import the CSS file for styling

const RegistrationForm = () => {


  const [email, setEmail] = useState('');
  const [userType, setUserType] = useState('');
  const [name, setName] = useState('');
  const [addressLine1, setAddressLine1] = useState('');
  const [addressLine2, setAddressLine2] = useState('');
  const [state, setState] = useState('');
  const [city, setCity] = useState('');
  const [country, setCountry] = useState('');
  const [zipCode, setZipCode] = useState('');
  const [password, setPassword] = useState('');
  const [submissionStatus, setSubmissionStatus] = useState('');

  const [doctorValues, setDoctorValues] = useState({
    firstName: '',
    lastName: '',
    userName: '',
    department: '',
    dateOfBirth: '',
    age:'',
    email:'',
    addressLine1: '',
    addressLine2: '',
    state: '',
    city: '',
    country: '',
    zipCode: '',
    createdAt: ''
  });

  const handleUserTypeChange = (e) => {
    setUserType(e.target.value);
  };

  const handleDoctorFormChange = (e) => {
    const { name, value } = e.target;
    setDoctorValues((prevValues) => ({
      ...prevValues,
      [name]: value,
    }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    // Create an object with the form data
    const formData = {
      email,
      userType,
      name,
      addressLine1,
      addressLine2,
      state,
      city,
      country,
      zipCode,
      password
    };

    // Send the form data to your Java backend using fetch or axios
    // Example:
    fetch('http://localhost:8080/app/addHospital', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(formData)
    })
      .then(response => {
        // Handle the response
        console.log(response);
        setSubmissionStatus('Hospital added');
      })
      .catch(error => {
        // Handle the error
        console.error(error);
      });
  };

  return (
    <div className="registration-container">
      <h1 className="registration-title">User Registration</h1>
      <form className="registration-form" onSubmit={handleSubmit}>
        <div className="form-group">
          <label htmlFor="email">Email:</label>
          <input type="email" id="email" value={email} onChange={(e) => setEmail(e.target.value)} required />
        </div>

        <div className="form-group">
          <label htmlFor="userType">User Type:</label>
          <select id="userType" value={userType} onChange={handleUserTypeChange} required>
            <option value="">Select User Type</option>
            <option value="hospital">Hospital</option>
            <option value="doctor">Doctor</option>
            <option value="department">Department</option>
            <option value="labTech">Lab Tech</option>
          </select>
        </div>

        {userType === 'hospital' && (
          <div className="user-details">
            <div className="form-group">
              <label htmlFor="name">Name:</label>
              <input type="text" id="name" value={name} onChange={(e) => setName(e.target.value)} required />
            </div>

            <div className="form-group">
              <label htmlFor="addressLine1">Address Line 1:</label>
              <input type="text" id="addressLine1" value={addressLine1} onChange={(e) => setAddressLine1(e.target.value)} required />
            </div>

            <div className="form-group">
              <label htmlFor="addressLine2">Address Line 2:</label>
              <input type="text" id="addressLine2" value={addressLine2} onChange={(e) => setAddressLine2(e.target.value)} />
            </div>

            <div className="form-group">
              <label htmlFor="state">State:</label>
              <input type="text" id="state" value={state} onChange={(e) => setState(e.target.value)} required />
            </div>

            <div className="form-group">
              <label htmlFor="city">City:</label>
              <input type="text" id="city" value={city} onChange={(e) => setCity(e.target.value)} required />
            </div>

            <div className="form-group">
              <label htmlFor="country">Country:</label>
              <input type="text" id="country" value={country} onChange={(e) => setCountry(e.target.value)} required />
            </div>

            <div className="form-group">
              <label htmlFor="zipCode">Zip Code:</label>
              <input type="text" id="zipCode" value={zipCode} onChange={(e) => setZipCode(e.target.value)} required />
            </div>

            <div className="form-group">
              <label htmlFor="password">Password:</label>
              <input type="password" id="password" value={password} onChange={(e) => setPassword(e.target.value)} required />
            </div>
          </div>
        )}
        {userType === 'doctor' && (
          <div className="user-details">
            <div className="form-group">
              <label htmlFor="fName">First Name:</label>
              <input type="text" id="fname" value={doctorValues.firstName} onChange={handleDoctorFormChange} required />
            </div>
            <div className="form-group">
              <label htmlFor="lName">Last Name:</label>
              <input type="text" id="lname" value={doctorValues.LastName} onChange={handleDoctorFormChange} required />
            </div>
            <div className="form-group">
              <label htmlFor="uName">First Name:</label>
              <input type="text" id="uname" value={doctorValues.userName} onChange={handleDoctorFormChange} required />
            </div>
            <div className="form-group">
              <label htmlFor="department">Department:</label>
              <input type="text" id="departmentName" value={doctorValues.department} onChange={handleDoctorFormChange} required />
            </div>
            <div className="form-group">
              <label htmlFor="dob">Date Of Birth:</label>
              <input type="text" id="dob" value={doctorValues.dateOfBirth} onChange={handleDoctorFormChange} required />
            </div>
            <div className="form-group">
              <label htmlFor="age">Age:</label>
              <input type="text" id="age" value={doctorValues.age} onChange={handleDoctorFormChange} required />
            </div>
            <div className="form-group">
              <label htmlFor="email">Email:</label>
              <input type="text" id="dEmail" value={doctorValues.email} onChange={handleDoctorFormChange} required />
            </div>
            <div className="form-group">
              <label htmlFor="addressLine1">Address Line 1:</label>
              <input type="text" id="docAddressLine1" value={doctorValues.addressLine1} onChange={handleDoctorFormChange} required />
            </div>

            <div className="form-group">
              <label htmlFor="addressLine2">Address Line 2:</label>
              <input type="text" id="docAddressLine2" value={doctorValues.addressLine2} onChange={handleDoctorFormChange} />
            </div>

            <div className="form-group">
              <label htmlFor="state">State:</label>
              <input type="text" id="docState" value={doctorValues.state} onChange={handleDoctorFormChange} required />
            </div>

            <div className="form-group">
              <label htmlFor="city">City:</label>
              <input type="text" id="docCity" value={doctorValues.city} onChange={handleDoctorFormChange} required />
            </div>

            <div className="form-group">
              <label htmlFor="country">Country:</label>
              <input type="text" id="docCountry" value={doctorValues.country} onChange={handleDoctorFormChange} required />
            </div>

            <div className="form-group">
              <label htmlFor="zipCode">Zip Code:</label>
              <input type="text" id="zipCode" value={doctorValues.zipCode} onChange={handleDoctorFormChange} required />
            </div>

            <div className="form-group">
              <label htmlFor="createdAt">Created At:</label>
              <input type="text" id="createdAt" value={doctorValues.createdAt} onChange={handleDoctorFormChange} required />
            </div>

          </div>
        )}
        {userType === 'department' && (
          <div className="user-details">
            <div className="form-group">
              <label htmlFor="name">Department Name:</label>
              <input type="text" id="dName" value={name} onChange={(e) => setName(e.target.value)} required />
            </div>
            <div className="form-group">
              <label htmlFor="name">Department Email:</label>
              <input type="email" id="dEmail" value={name} onChange={(e) => setName(e.target.value)} required />
            </div>
            <div className="form-group">
              <label htmlFor="name">Department Name:</label>
              <input type="text" id="name" value={name} onChange={(e) => setName(e.target.value)} required />
            </div>
            <div className="form-group">
              <label htmlFor="name">Department Name:</label>
              <input type="text" id="name" value={name} onChange={(e) => setName(e.target.value)} required />
            </div>
            <div className="form-group">
              <label htmlFor="name">Department Name:</label>
              <input type="text" id="name" value={name} onChange={(e) => setName(e.target.value)} required />
            </div>
            <div className="form-group">
              <label htmlFor="name">Department Name:</label>
              <input type="text" id="name" value={name} onChange={(e) => setName(e.target.value)} required />
            </div>
            <div className="form-group">
              <label htmlFor="name">Department Name:</label>
              <input type="text" id="name" value={name} onChange={(e) => setName(e.target.value)} required />
            </div>

          </div>
        )}
        {userType === 'labTech' && (
          <div className="user-details">
            <div className="form-group">
              <label htmlFor="name">Name:</label>
              <input type="text" id="name" value={name} onChange={(e) => setName(e.target.value)} required />
            </div>

          </div>
        )}

        {submissionStatus && <p>{submissionStatus}</p>}

        <button type="submit" className="submit-button">Submit</button>
      </form>
    </div>
  );
};

export default RegistrationForm;
