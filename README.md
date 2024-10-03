# FitGymApp

FitGymApp is a console application developed in Java using Maven. The application allows managing gym clients through a command-line interface, providing different functionalities. It connects to a PostgreSQL database to perform CRUD operations on client records.

## Features

- Add new clients
- Update existing clients
- Delete clients
- List all clients
- Search for clients by ID

## Technologies Used

- Java
- Maven
- PostgreSQL

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or higher
- Maven
- PostgreSQL

### Installation

1. **Clone the repository:**
    ```sh
    git clone https://github.com/yourusername/FitGymApp.git
    cd FitGymApp
    ```

2. **Configure the database:**
    - Create a PostgreSQL database.
    - Create the `client` table in your PostgreSQL database with the following structure:
      ```sql
      CREATE TABLE client (
          id SERIAL PRIMARY KEY,
          name VARCHAR(50) NOT NULL,
          lastname VARCHAR(50) NOT NULL,
          membership INT NOT NULL
      );
      ```
    - Update the database connection settings in your environment variables according to the names in the file `src/main/java/fit_gym/connection/DbConnection.java`.
3. 
4. **Build the project:**
    ```sh
    mvn clean install
    ```

### Running the Application

1. **Run the application:**
    ```sh
    mvn exec:java -Dexec.mainClass="fit_gym.Main"
    ```

## Usage

The application provides a command-line interface to manage gym clients. You can use the following commands:

- **Add a client:**
    ```
    add <name> <lastName> <membership>
    ```

- **Update a client:**
    ```
    update <id> <name> <lastName> <membership>
    ```

- **Delete a client:**
    ```
    delete <id>
    ```

- **List all clients:**
    ```
    list
    ```

- **Search for a client by ID:**
    ```
    search <id>
    ```