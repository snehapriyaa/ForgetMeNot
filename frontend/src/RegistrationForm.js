import React, { useState, useEffect } from 'react';
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
  const [hospitalNames, setHospitalNames] = useState([]);
  const [departmentNames, setDepartmentNames] = useState([]);

  const [doctorValues, setDoctorValues] = useState({
    firstName: '',
    lastName: '',
    userName: '',
    department: '',
    dateOfBirth: '',
    age: '',
    email: email,
    addressLine1: '',
    addressLine2: '',
    state: '',
    city: '',
    country: '',
    zipCode: '',
    createdAt: '',
    password: '',
    hospitalName: ''
  });

  const [departmentValues, setDepartmentValues] = useState({
    hospitalName: [],
    userName: '',
    password: '',
    departmentName: '',
    departmentEmail: '',
    addressLine1: '',
    addressLine2: '',
    state: '',
    city: '',
    country: '',
    zipCode: '',
    createdAt: ''

  })

  const [labWorkerValues, setLabWorkerValues] = useState({
    departmentName: '',
    userName: '',
    firstName: '',
    lastName: '',
    password: '',
    dob: '',
    age: '',
    email: '',
    addressLine1: '',
    addressLine2: '',
    state: '',
    city: '',
    country: '',
    zipCode: '',
    createdAt: '',
    hospitalName: ''

  })

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
  const handleDepartmentFormChange = (e) => {
    const { name, value } = e.target;
    setDepartmentValues((prevValues) => ({
      ...prevValues,
      [name]: value,
    }));
  };

  const handleLabWorkerFormChange = (e) => {
    const { name, value } = e.target;
    setLabWorkerValues((prevValues) => ({
      ...prevValues,
      [name]: value,
    }));
  };

  useEffect(() => {
    if (userType === 'department' || userType === 'doctor' || userType === 'labTech') {
      // Fetch hospital names
      fetch('http://localhost:8080/app/hospitals')
        .then(response => {
          if (response.ok) {
            return response.json();
          } else {
            throw new Error('Error fetching hospital names');
          }
        })
        .then(data => {
          setHospitalNames(data);
        })
        .catch(error => {
          console.error('Error fetching hospital names:', error);
        });
    }

  }, [userType]);


  useEffect(() => {
    let fetch_url = 'http://localhost:8080/api/departments'
    if (doctorValues.hospitalName) {
      fetch_url = `http://localhost:8080/api/getDepartmentByHospitalName?hospitalName=${doctorValues.hospitalName}`
    } else if (labWorkerValues.hospitalName) {
      fetch_url = `http://localhost:8080/api/getDepartmentByHospitalName?hospitalName=${labWorkerValues.hospitalName}`
    }
    fetch(`${fetch_url}`)
      .then(response => {
        if (response.ok) {
          return response.json();
        } else {
          throw new Error('Error fetching department names');
        }
      })
      .then(data => {
        setDepartmentNames(data);
      })
      .catch(error => {
        console.error(error);
      });
  }, [doctorValues.hospitalName, labWorkerValues.hospitalName]);



  const handleSubmit = (e) => {
    e.preventDefault();
    // ------------add condition for each user types ----------
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
    switch (userType) {
      case 'hospital':
        fetch('http://localhost:8080/app/addHospital', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify(formData)
        })
          .then(response => {
            // Handle the response
            //console.log(response);
            console.log(response.data);
            setSubmissionStatus('Hospital added');
          })
          .catch(error => {
            // Handle the error
            console.error(error);
          });
        break;
      case 'doctor':
        fetch('http://localhost:8080/app/addDoctor', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify(doctorValues)
        })
          .then(response => {
            // Handle the response
            console.log(response);
            setSubmissionStatus('Doctor added');
          })
          .catch(error => {
            // Handle the error
            console.error(error);
          });
        console.log("Doctor Details " + JSON.stringify(doctorValues));
        break;
      case 'department':
        fetch('http://localhost:8080/app/addDepartment', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify(departmentValues)
        })
          .then(response => {
            // Handle the response
            console.log(response);
            setSubmissionStatus('Department added');
          })
          .catch(error => {
            // Handle the error
            console.error(error);
          });
        console.log("Department Details " + JSON.stringify(departmentValues));
        break;
      case 'labTech':
        labWorkerValues.email = formData.email;
        console.log("Lab Tech details " + JSON.stringify(labWorkerValues));
        break;

    }


  };

  return (
    <div>
      <div class="logo">iGem 2023</div>

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
                <input type="text" id="fname" name="firstName" value={doctorValues.firstName} onChange={handleDoctorFormChange} required />
              </div>
              <div className="form-group">
                <label htmlFor="lName">Last Name:</label>
                <input type="text" id="lname" name="lastName" value={doctorValues.lastName} onChange={handleDoctorFormChange} required />
              </div>
              {/* <div className="form-group">
              <label htmlFor="uName">User Name:</label>
              <input type="text" id="uname" name="userName" value={doctorValues.userName} onChange={handleDoctorFormChange} required />
            </div> */}
              {/* ----------------------------------------------- get hospital list, load dept list based on hosp-----------------------------------*/}
              <div className="form-group">
                <label htmlFor="hospitalName">Hospital Name:</label>
                <select id="hospitalName" name="hospitalName" value={doctorValues.hospitalName} onChange={handleDoctorFormChange} required>
                  <option value="">Select Hospital Name</option>
                  {hospitalNames.map(name => (
                    <option key={name}>{name}</option>
                  ))}
                </select>
              </div>
              <div className="form-group">
                <label htmlFor="department">Department:</label>
                <select id="departmentName" name="department" value={doctorValues.department} onChange={handleDoctorFormChange} required>
                  <option value="">Select Department Name</option>
                  {departmentNames.map(name => (
                    <option key={name}>{name}</option>
                  ))}
                </select>
                {/* <input type="text" id="departmentName" name="department" value={doctorValues.department} onChange={handleDoctorFormChange} required /> */}
              </div>
              <div className="form-group">
                <label htmlFor="dob">Date Of Birth:</label>
                <input type="date" id="dob" name="dateOfBirth" value={doctorValues.dateOfBirth} onChange={handleDoctorFormChange} required />
              </div>
              <div className="form-group">
                <label htmlFor="age">Age:</label>
                <input type="number" id="age" name="age" value={doctorValues.age} onChange={handleDoctorFormChange} required />
              </div>
              {/* <div className="form-group">
              <label htmlFor="email">Email:</label>
              <input type="text" id="dEmail" name="email" value={doctorValues.email} onChange={handleDoctorFormChange} required />
            </div> */}
              <div className="form-group">
                <label htmlFor="addressLine1">Address Line 1:</label>
                <input type="text" id="docAddressLine1" name="addressLine1" value={doctorValues.addressLine1} onChange={handleDoctorFormChange} required />
              </div>

              <div className="form-group">
                <label htmlFor="addressLine2">Address Line 2:</label>
                <input type="text" id="docAddressLine2" name="addressLine2" value={doctorValues.addressLine2} onChange={handleDoctorFormChange} />
              </div>

              <div className="form-group">
                <label htmlFor="state">State:</label>
                <input type="text" id="docState" name="state" value={doctorValues.state} onChange={handleDoctorFormChange} required />
              </div>

              <div className="form-group">
                <label htmlFor="city">City:</label>
                <input type="text" id="docCity" name="city" value={doctorValues.city} onChange={handleDoctorFormChange} required />
              </div>

              <div className="form-group">
                <label htmlFor="country">Country:</label>
                <input type="text" id="docCountry" name="country" value={doctorValues.country} onChange={handleDoctorFormChange} required />
              </div>

              <div className="form-group">
                <label htmlFor="zipCode">Zip Code:</label>
                <input type="text" id="zipCode" name="zipCode" value={doctorValues.zipCode} onChange={handleDoctorFormChange} required />
              </div>

              {/* <div className="form-group">
              <label htmlFor="createdAt">Created At:</label>
              <input type="text" id="createdAt" name="createdAt" value={doctorValues.createdAt} onChange={handleDoctorFormChange} required />
            </div> */}
              <div className="form-group">
                <label htmlFor="password">Password:</label>
                <input type="password" id="docPassword" name="password" value={doctorValues.password} onChange={handleDoctorFormChange} required />
              </div>

            </div>
          )}
          {userType === 'department' && (
            <div className="user-details">
              <div className="form-group">
                <label htmlFor="dName">Department Name:</label>
                <input type="text" id="dname" name="departmentName" value={departmentValues.departmentName} onChange={handleDepartmentFormChange} required />
              </div>

              <div className="form-group">
                <label htmlFor="hospitalName">Hospital Name:</label>
                <select id="hospitalName" name="hospitalName" value={departmentValues.hospitalName} onChange={handleDepartmentFormChange} required>
                  <option value="">Select Hospital Name</option>
                  {hospitalNames.map(name => (
                    <option key={name}>{name}</option>
                  ))}
                </select>
                {/* <input type="text" id="hospitalName" name="hospitalName" value={departmentValues.hospitalName} onChange={handleDepartmentFormChange} required /> */}
              </div>
              <div className="form-group">
                <label htmlFor="addressLine1">Address Line 1:</label>
                <input type="text" id="deptAddressLine1" name="addressLine1" value={departmentValues.addressLine1} onChange={handleDepartmentFormChange} required />
              </div>

              <div className="form-group">
                <label htmlFor="addressLine2">Address Line 2:</label>
                <input type="text" id="deptAddressLine2" name="addressLine2" value={departmentValues.addressLine2} onChange={handleDepartmentFormChange} />
              </div>

              <div className="form-group">
                <label htmlFor="state">State:</label>
                <input type="text" id="deptState" name="state" value={departmentValues.state} onChange={handleDepartmentFormChange} required />
              </div>

              <div className="form-group">
                <label htmlFor="city">City:</label>
                <input type="text" id="deptCity" name="city" value={departmentValues.city} onChange={handleDepartmentFormChange} required />
              </div>

              <div className="form-group">
                <label htmlFor="country">Country:</label>
                <input type="text" id="deptCountry" name="country" value={departmentValues.country} onChange={handleDepartmentFormChange} required />
              </div>

              <div className="form-group">
                <label htmlFor="zipCode">Zip Code:</label>
                <input type="text" id="deptZipCode" name="zipCode" value={departmentValues.zipCode} onChange={handleDepartmentFormChange} required />
              </div>

              {/* <div className="form-group">
              <label htmlFor="createdAt">Created At:</label>
              <input type="text" id="deptCreatedAt" name="createdAt" value={departmentValues.createdAt} onChange={handleDepartmentFormChange} required />
            </div> */}
              <div className="form-group">
                <label htmlFor="password">Password:</label>
                <input type="password" id="deptPassword" name="password" value={departmentValues.password} onChange={handleDepartmentFormChange} required />
              </div>
            </div>
          )}
          {userType === 'labTech' && (
            <div className="user-details">
              <div className="form-group">
                <label htmlFor="hospitalName">Hospital Name:</label>
                <select id="hospitalName" name="hospitalName" value={labWorkerValues.hospitalName} onChange={handleLabWorkerFormChange} required>
                  <option value="">Select Hospital Name</option>
                  {hospitalNames.map(name => (
                    <option key={name}>{name}</option>
                  ))}
                </select>
              </div>
              <div className="form-group">
                <label htmlFor="department">Department:</label>
                <select id="departmentName" name="departmentName" value={labWorkerValues.departmentName} onChange={handleLabWorkerFormChange} required>
                  <option value="">Select Department Name</option>
                  {departmentNames.map(name => (
                    <option key={name}>{name}</option>
                  ))}
                </select>
                {/* <input type="text" id="departmentName" name="department" value={doctorValues.department} onChange={handleDoctorFormChange} required /> */}
              </div>
              <div className="form-group">
                <label htmlFor="fName">First Name:</label>
                <input type="text" id="labFname" name="firstName" value={labWorkerValues.firstName} onChange={handleLabWorkerFormChange} required />
              </div>
              <div className="form-group">
                <label htmlFor="lName">Last Name:</label>
                <input type="text" id="labLname" name="lastName" value={labWorkerValues.lastName} onChange={handleLabWorkerFormChange} required />
              </div>
              {/* <div className="form-group">
              <label htmlFor="uName">User Name:</label>
              <input type="text" id="labUname" name="userName" value={labWorkerValues.userName} onChange={handleLabWorkerFormChange} required />
            </div> */}

              <div className="form-group">
                <label htmlFor="dob">Date Of Birth:</label>
                <input type="date" id="LabDob" name="dob" value={labWorkerValues.dob} onChange={handleLabWorkerFormChange} required />
              </div>
              <div className="form-group">
                <label htmlFor="age">Age:</label>
                <input type="number" id="LabAge" name="age" value={labWorkerValues.age} onChange={handleLabWorkerFormChange} required />
              </div>
              {/* <div className="form-group">
              <label htmlFor="email">Email:</label>
              <input type="text" id="labEmail" name="email" value={labWorkerValues.email} onChange={handleLabWorkerFormChange} required />
            </div> */}
              <div className="form-group">
                <label htmlFor="addressLine1">Address Line 1:</label>
                <input type="text" id="labAddressLine1" name="addressLine1" value={labWorkerValues.addressLine1} onChange={handleLabWorkerFormChange} required />
              </div>

              <div className="form-group">
                <label htmlFor="addressLine2">Address Line 2:</label>
                <input type="text" id="labAddressLine2" name="addressLine2" value={labWorkerValues.addressLine2} onChange={handleLabWorkerFormChange} />
              </div>

              <div className="form-group">
                <label htmlFor="state">State:</label>
                <input type="text" id="labState" name="state" value={labWorkerValues.state} onChange={handleLabWorkerFormChange} required />
              </div>

              <div className="form-group">
                <label htmlFor="city">City:</label>
                <input type="text" id="labCity" name="city" value={labWorkerValues.city} onChange={handleLabWorkerFormChange} required />
              </div>

              <div className="form-group">
                <label htmlFor="country">Country:</label>
                <input type="text" id="labCountry" name="country" value={labWorkerValues.country} onChange={handleLabWorkerFormChange} required />
              </div>

              <div className="form-group">
                <label htmlFor="zipCode">Zip Code:</label>
                <input type="text" id="labZipCode" name="zipCode" value={labWorkerValues.zipCode} onChange={handleLabWorkerFormChange} required />
              </div>

              {/* <div className="form-group">
              <label htmlFor="createdAt">Created At:</label>
              <input type="text" id="labCreatedAt" name="createdAt" value={labWorkerValues.createdAt} onChange={handleLabWorkerFormChange} required />
            </div> */}

              <div className="form-group">
                <label htmlFor="password">Password:</label>
                <input type="password" id="labPassword" name="password" value={labWorkerValues.password} onChange={handleLabWorkerFormChange} required />
              </div>
            </div>
          )}

          {submissionStatus && <p>{submissionStatus}</p>}

          <button type="submit" className="submit-button">Submit</button>
        </form>
      </div>
    </div>
  );
};

export default RegistrationForm;
