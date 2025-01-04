# Spring Security Form Login & Social Login

This codebase demonstrates how to implement both form-based authentication and social login using Spring Security. It includes examples of integrating Google OAuth for social login.

## Features

- Form-based login
- Google OAuth2 login
- React Login page

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven
- Google Developer account
- Node 17

### Setup

1. Clone the repository:
    ```sh
    git clone https://github.com/gauravvishalgv/spring-security-google-oauth.git
    cd spring-security-form-oauth
    ```

2. Configure Google OAuth2:
    - Go to the [Google Developer Console](https://console.developers.google.com/).
    - Create a new project.
    - Configure OAuth consent screen.
    - Create OAuth 2.0 Client IDs and set the authorized redirect URIs to `http://localhost:3333/login/oauth2/code/google`.

3. Update `application.properties` with your Google client ID and secret:
    ```
    spring.security.oauth2.client.registration.google.client-id=XXX
    spring.security.oauth2.client.registration.google.client-secret=XXX
    ```

4. Build and run the spring application:
    ```sh
    mvn clean install
    mvn spring-boot:run
    ```
5. Frontend Setup

   5.1 Navigate to the `react-login` directory:
   ```sh
   cd react-login
   ```

   5.2 Install the dependencies:
   ```sh
   npm install
   ```

   5.3 Start the React development server:
   ```sh
   npm run dev
   ```

### Usage

- Access the application frontend at `http://localhost:3333/login`.
- Use the form-based login or click on the "Login with Google" button to authenticate using Google OAuth2.

## Contributing

Contributions are welcome! Please fork the repository and submit a pull request.
