import React, { useState } from 'react';
import { Card, CardContent, Typography, TextField, Button } from '@mui/material';

const TestDetails = ({ test }) => {
  const [selectedFile, setSelectedFile] = useState(null);
  const [comments, setComments] = useState('');

  const handleFileChange = (e) => {
    setSelectedFile(e.target.files[0]);
  };

  const handleCommentsChange = (e) => {
    setComments(e.target.value);
  };

  const handleUpload = () => {
    if (!selectedFile) {
      console.log('No file selected');
      return;
    }

    const formData = new FormData();
    formData.append('file', selectedFile);
    formData.append('comments', comments);

    fetch(`http://localhost:8080/app/tests/${test.id}/upload`, {
      method: 'POST',
      body: formData,
    })
      .then((response) => response.json())
      .then((data) => {
        console.log('File uploaded successfully');
        console.log(data);
      })
      .catch((error) => {
        console.error('Error uploading file:', error);
      });
  };

  return (
    <div>
      <h2>Test Details</h2>
      <Card>
        <CardContent>
        <Typography variant="body2" component="div">
            <strong>Test ID:</strong> <span>{test.id}</span>
          </Typography>
          <Typography variant="body2" component="div">
            <strong>Name:</strong> <span>{test.testName}</span>
          </Typography>
          <Typography variant="body2" component="div">
            <strong>Description:</strong> <span>{test.baseDescription}</span>
          </Typography>
          <Typography variant="body2" component="div">
            <strong>Comments:</strong>
            <textarea
              value={comments}
              onChange={handleCommentsChange}
              rows={4}
              cols={50}
              style={{ resize: 'none', marginTop: '8px' }}
            />
          </Typography>
          <Typography variant="body2" component="div">
            <strong>Status:</strong> <span>{test.completed}</span>
          </Typography>
          <TextField
            type="file"
            onChange={handleFileChange}
            variant="outlined"
            margin="normal"
            fullWidth
          />
          <Button variant="contained" onClick={handleUpload}>
            Upload
          </Button>
        </CardContent>
      </Card>
    </div>
  );
};

export default TestDetails;
