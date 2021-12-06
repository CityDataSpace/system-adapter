# System Adapter

Fraunhofer Data Space System Adapter is an application developed in Java Spring that allows for storing sensitive data in a secure manner. Since Fraunhofer Data Space Ecosystem supports different connectors and micro-services, there is a need to have a possibility to securely store data outside the connectors. Therefore, System Adapter was developed.

# Features

System Adapter is made from three layers of security: Authentication, Authorization and Encryption.

Authentication is handled by Spring Security Library. We use basic form authentication in order to access pages with sensitive data. Authorization is handled through permissions and roles designed inside Spring Application. Encryption is handled by RSA with public and private key. Private key is used for encryption whereas public key is used for decryption.      

# Swagger UI

System Adapter has support for Swagger UI. In order to access it head to **http://localhost:1400/swagger-ui.html**.

# Eureka Client

System Adapter is conceived as a micro-service. It is automatically discoverable by Eureka Servers.

# Pre-requisities

Make sure you have the following installed:

 - Mysql Server
 - Postgres Server


# Usage

To launch System Adapter, do the following:

- Clone the project by typing in the command line: 
 ```sh
    $ git clone https://gitlab.cc-asp.fraunhofer.de/iese-ids/system-adapter
 ```
- Open **Eclipse** and import the repository as a **Maven** project.
- After that launch the app as a **JAVA Application**

To launch Front-End of the system adapter, do the folliwng:
```sh
    $ cd system-adapter/src/main/ui/systemadapter-mui
    $ npm run start
 ```
 
# Useful Links

- http://localhost:3000/ - Front-End
- http://localhost:1400/ - Back-End
- http://localhost:8761/ - Eureka Control Panel
- http://localhost:1400/swagger-ui.html - Swagger Ui

# Bugs

Please report them to: arian.ajdari@iese.fraunhofer.de