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

  const handleUserTypeChange = (e) => {
    setUserType(e.target.value);
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
        console.log(JSON.stringify(formData));
        console.log(response);
      })
      .catch(error => {
        // Handle the error
        console.log(JSON.stringify(formData));
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

        {userType && (
          <button type="submit" className="submit-button">Submit</button>
        )}
      </form>
    </div>
  );
};

export default RegistrationForm;
