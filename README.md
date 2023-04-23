# Turkcell --> Rent A Car

Basic Rent a Car Site Back-End

- Brand, Car, Invoice, Maintenance, Model, Payment and Rental are kept in the system.

- The Brand has characteristics in the form of id and name.

- The Car has features such as id, model year, plate and price.

- The Invoice has features such as id, model year, plate, model name, brand name, card holder, daily price, rented for days and rented at.

- Maintenance has properties such as id, information, completion status, entry date and exit date.

- The Model has characteristics in the form of id and name.

- The Payment has features such as id, card cvv, balance, card number, card holder, card expiration year and card expiration month.

- Rental has properties such as id, carId, daily price, rented fo days, start date and total price.

- Adding, deleting, updating, listing all Entities and listing by id value are available.

- Cars can be sent for maintenance.

- The Car from maintenance should be rentable again.

- A Car that is already in rented cannot be sent for maintenance.

- Rented Car cannot be sent to the maintenance.

- According to the user's choice, the Car under maintenance should appear in the list or not.

- Car can be rented.

- The status of the rented Car must be updated.

- A Car that is rented or in maintenance should not be rented.

- The totalPrice field must be read-only.
