# Payment Processing System

## Description
This project is a payment processing system that allows users to purchase products and process payments through various payment methods. 
It is built using Java with JPA for persistence and a CDI-based architecture for dependency injection.

## Features
- User management
- Product catalog and selection
- Multiple payment methods integration
- Transactional payment processing

## Technologies Used
- **Java**: Core language for the application.
- **Jakarta EE**: For dependency injection and RESTful web services.
- **JPA (Java Persistence API)**: For data persistence.
- **MySQL**: Database for storing user, product, and payment data.
- **CDI (Contexts and Dependency Injection)**: For managing dependency injection.
- **Maven**: For project build and dependency management.

## Project Structure
```
├── src
│   ├── main
│   │   ├── java
│   │   │   ├── ec.edu.uce.payment
│   │   │   │   ├── annotations        # Custom annotations
│   │   │   │   ├── configs            # Entity Manager configs
│   │   │   │   ├── interceptors       # Transactional interceptor
│   │   │   │   ├── models             # Entities and interfaces
│   │   │   │   ├── repositories       # Data base operations
│   │   │   │   ├── services           # Business logic services
│   │   │   │   ├── util               # Entity Manager Factory creation
│   │   │   │   ├── HelloResource.java     # REST endpoints
│   │   ├── resources
│   │       ├── META-INF               # Persistence configuration
├── pom.xml                            # Maven configuration
```

## How to Run
1. **Clone the Repository**:
   ```bash
   git clone https://github.com/Erenriquezp/PaymentProcess.git
   cd PaymentProcess

   ```

2. **Configure the Database**:
   - Create a MySQL database `paymentdb` and update the connection details (user, password) in `META-INF/persistence.xml`.

3. **Build the Project**:
   ```bash
   mvn clean install
   ```

4. **Run the Application**:
   - Deploy the application to a Jakarta EE server (Wildfly).

5. **Access the API**:
   - Base URL: `http://localhost:8080/payment-1.0-SNAPSHOT/api/purchase/test`
   - Example endpoints:
     - `GET /api/purchase/process/...`
     - `GET /api/purchase/payments`

## Endpoints

### Create example data
**URL**: `/api/purchase/test`

**Method**: `GET`

**Response**: List of users, products and payment method examples.

### Process Purchase
**URL**: `/api/purchase/process`

**Method**: `GET`

**Query Parameters**:
- `userId` (Long): ID of the user.
- `productIds` (String): Comma-separated list of product IDs.
- `paymentMethod` (String): Payment method ID.

**Response**: Plain text confirmation of payment processing.

### View Payments
**URL**: `/purchase/process?userId=<USER_ID>&productIds=<PRODUCT_IDS>&paymentMethod=<METHOD>`.

**Example**: `/purchase/process?userId=1&productIds=1,2&paymentMethod=paypal`

**Method**: `GET`

**Response**: List of all processed payments.

## Contributing
1. Fork the repository.
2. Create a new branch (`git checkout -b feature/your-feature`).
3. Commit your changes (`git commit -am 'Add some feature'`).
4. Push to the branch (`git push origin feature/your-feature`).
5. Open a Pull Request.

## Aditional
You can test the crud operations in CrudApi.java

## License
This project is licensed under the MIT License.

---

Happy coding!

