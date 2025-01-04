import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import './App.css'
import Login from './pages/Login'
import Profile from './pages/Profile';
import Home from './pages/Home';
import { Box } from '@mui/material';

function App() {
  

  return (
    <Box sx = {{}}>
      <Router>
        <Routes>
          <Route path="/" element={<Home />} /> 
          <Route path="/login" element={<Login />} /> 
          <Route path="/profile" element={<Profile />} /> 
        </Routes>
      </Router>
    </Box>
  )
}

export default App
