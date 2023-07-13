import React, { useState } from 'react';
import TestList from './TestList';
import TestDetails from './TestDetails';
import { Container, Grid, Typography } from '@mui/material';

const App = () => {
  const [selectedTest, setSelectedTest] = useState(null);

  const handleSelectTest = (test) => {
    console.log(test);
    setSelectedTest(test);
  };

  return (
    <Container maxWidth="md">
      <Typography variant="h3" component="h1" align="center" gutterBottom>
        Lab Tests
      </Typography>
      <Grid container spacing={2}>
        <Grid item xs={12} md={4}>
          <TestList onSelectTest={handleSelectTest} />
        </Grid>
        <Grid item xs={12} md={8}>
          {selectedTest ? (
            <TestDetails test={selectedTest} />
          ) : (
            <Typography variant="h5" align="center">
              Select a Test
            </Typography>
          )}
        </Grid>
      </Grid>
    </Container>
  );
};

export default App;
