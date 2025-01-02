import React, { useState } from 'react';
import { TextField, Button, Box, Typography, Paper } from '@mui/material';
import { Google, GitHub } from '@mui/icons-material';
import AppConfig from '../config/AppConfig';

const LoginPage: React.FC = () => {
  const [username, setUsername] = useState<string>('');
  const [password, setPassword] = useState<string>('');

  const handleLogin = (event: React.FormEvent) => {

    const formData = new URLSearchParams();
    formData.append('username', username);
    formData.append('password', password);

    fetch(AppConfig.userPasswordLoginUrl, {
      method: 'POST',
      body: formData,
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded',
      },
    })
      .then((response) => {
        if (response.ok) {
          window.location.href = '/';
        } else {
          alert('Login failed. Please check your credentials.');
        }
      })
      .catch((error) => console.error('Error:', error));

    event.preventDefault();
    console.log('Logging in with:', { username, password });
  };

  const handleGoogleLogin = () => {
    window.location.href = AppConfig.googleLoginUrl;
  };

  const handleGithubLogin = () => {
    window.location.href = AppConfig.githubLoginUrl;
  };

  return (
    <Paper elevation={3} sx={{ p: 5, width: '100%', maxWidth: 400, borderRadius:5}}>
        <Typography variant="h5" gutterBottom align="center">
          Wecome Back!
        </Typography>

        <form onSubmit={handleLogin}>
          <TextField
            label="Username"
            variant="outlined"
            fullWidth
            margin="normal"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
          />
          <TextField
            label="Password"
            variant="outlined"
            type="password"
            fullWidth
            margin="normal"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />

          <Button
            type="submit"
            variant="contained"
            color="primary"
            fullWidth
            sx={{ mt: 2 }}
          >
            Login
          </Button>
        </form>

        <Box textAlign="center" my={2}>
          <Typography variant="body2" color="textSecondary">
            OR
          </Typography>
        </Box>

        <Button
          variant="outlined"
          fullWidth
          onClick={handleGoogleLogin}
          sx={{ mb: 2 }}
          startIcon={<Google />}
        >
          Login with Google
        </Button>

        <Button
          variant="outlined"
          fullWidth
          onClick={handleGithubLogin}
          startIcon={<GitHub />}
        >
          Login with GitHub
        </Button>
      </Paper>
  );
};

export default LoginPage;
