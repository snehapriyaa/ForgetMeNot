import React, { useState, useEffect } from 'react';
import './TestList.css'; // Import the CSS file

const TestList = ({ onSelectTest }) => {
  const [tests, setTests] = useState([]);

  useEffect(() => {
    fetch('http://localhost:8080/app/tests/getall')
      .then(response => response.json())
      .then(data => setTests(data));
  }, []);

  return (
    <div>
      <h2>List of Tests</h2>
      <ul className="list">
        {tests.map(test => (
          <li key={test.id} className="list-item" onClick={() => onSelectTest(test)}>
            <span className="test-id">{test.id}</span>
            <span className="test-name">{test.name}</span>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default TestList;
