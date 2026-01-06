# TP12 – SecureServer with SOAP and CXF

## Description
This project demonstrates a **secure SOAP web service** using **Apache CXF** with **UsernameToken authentication**.  
It exposes a simple `HelloService` that requires a username and password for access.

---

## Features
- SOAP Web Service endpoint: `/services/hello-secure`
- Secured using **WS-Security UsernameToken**
- Sample user: `student` / `secret123`
- Testable via **cURL**, **SoapUI**, or any SOAP client
- WSDL URL printed on startup

---

## Technologies Used
- Java 17+
- Apache CXF
- WSS4J (WS-Security)
- Maven

---

## Getting Started

### Requirements
- Java 17+
- Maven
- Internet connection for dependencies

### Build & Run

1. Navigate to the project folder:
```bash
cd /path/to/tp12
