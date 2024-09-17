# <h1 align="center">Flight Management</h1>

## Project Technologies
___
`Techonologies are use to buiild Online Book Store: `
- **Programming Language:** `Java 17`
- **Spring Framework:** `Spring Boot v3.3.3`
- **Testing:** `JUnit 5, Mockito`
- **Additional instruments:** `Lombok`
- **Documentation:** `Swagger`

<a name="endpoints"></a>
## Endpoints
___

### Baggage Controller  :

| Method | Endpoint                              | Description             | 
|--------|---------------------------------------|-------------------------|
| GET    | /baggages/{baggageId}/{destinationId} | check-in the baggage    |

### Discount Controller

| Method | Endpoint             | Description                       |
|--------|----------------------|-----------------------------------|
| GET    | /discount/{couponId} | Gets a discount with valid coupon |

### Ticket Controller

| Method | Endpoint            | Description                  |
|--------|---------------------|------------------------------|
| GET    | /tickets/{ticketId} | Checks whether a free ticket |

## How to use
___

1. **Clone the repository from GitHub:** [GitHub repositry](https://github.com/dmytrocherepov/PeoplePulseTT)
2. **Run the application**
3. **The application should be running locally at http://localhost:8080**
4. **Test the application using swagger http://localhost:8088/swagger-ui/index.html**