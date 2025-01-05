
import Navbar from "../components/Navbar";
import { useEffect } from "react";
import AppConfig from '../config/AppConfig';
import { Box } from "@mui/material";

export default function Home() {


    useEffect(() => {
        fetch(AppConfig.apiBaseUrl + '/api/user/principal', {
            method: 'GET',
            credentials: 'include', 
        })
            .then(response => {
                if (response.status === 401) {
                    window.location.href = '/login';
                }

                return response; 
            })
            .catch(error => {
                console.error("Error fetching user data:", error);
                window.location.href = '/login';
            });
    }, []);
    
    
    return (

        <Box>
            <Navbar />
        </Box>
    );
}
