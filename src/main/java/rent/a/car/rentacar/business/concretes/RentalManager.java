package rent.a.car.rentacar.business.concretes;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import rent.a.car.rentacar.business.abstracts.PaymentService;
import rent.a.car.rentacar.business.abstracts.RentalService;
import rent.a.car.rentacar.business.dto.requests.creates.CreateRentalRequest;
import rent.a.car.rentacar.business.dto.requests.updates.UpdateRentalRequest;
import rent.a.car.rentacar.business.dto.responses.creates.CreateRentalResponse;
import rent.a.car.rentacar.business.dto.responses.gets.rental.GetAllRentalsResponse;
import rent.a.car.rentacar.business.dto.responses.gets.rental.GetRentalResponse;
import rent.a.car.rentacar.business.dto.responses.updates.UpdateRentalResponse;
import rent.a.car.rentacar.common.dto.CreateRentalPaymentRequest;
import rent.a.car.rentacar.entities.Car;
import rent.a.car.rentacar.entities.Rental;
import rent.a.car.rentacar.entities.enums.State;
import rent.a.car.rentacar.repository.CarRepository;
import rent.a.car.rentacar.repository.RentalRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class RentalManager implements RentalService {
    private final ModelMapper mapper;
    private final CarRepository carRepository;
    private final PaymentService paymentService;
    private final RentalRepository rentalRepository;

    @Override
    public List<GetAllRentalsResponse> getAll() {
        List<Rental> rentals = rentalRepository.findAll();

        List<GetAllRentalsResponse> responses = rentals
                .stream()
                .map(rental -> mapper.map(rental, GetAllRentalsResponse.class))
                .toList();

        return responses;
    }

    @Override
    public GetRentalResponse getById(int id) {
        Rental rental = rentalRepository.findById(id).orElseThrow();
        GetRentalResponse response = mapper.map(rental, GetRentalResponse.class);

        return response;
    }

    @Override
    public CreateRentalResponse add(CreateRentalRequest request) {
        checkCarForRented(request.getCarId());
        Rental rental = mapper.map(request, Rental.class);
        rental.setId(0);
        rental.setTotalPrice(getTotalPrice(rental));

        CreateRentalPaymentRequest paymentRequest = new CreateRentalPaymentRequest();
        mapper.map(request.getPaymentRequest(), paymentRequest);
        paymentRequest.setPrice(getTotalPrice(rental));
        paymentService.processRentalPayment(paymentRequest);
        
        rentalRepository.save(rental);
        checkIfRentedCarState(rental.getCar().getId(), State.RENTED);
        CreateRentalResponse response = mapper.map(rental, CreateRentalResponse.class);

        return response;
    }


    @Override
    public UpdateRentalResponse update(int id, UpdateRentalRequest request) {
        Rental rental = mapper.map(request, Rental.class);

        rental.setId(id);
        rental.setTotalPrice(getTotalPrice(rental));

        rentalRepository.save(rental);

        UpdateRentalResponse response = mapper.map(rental, UpdateRentalResponse.class);

        return response;
    }

    @Override
    public void delete(int id) {
        Rental rental = rentalRepository.findById(id).orElseThrow();
        checkIfRentedCarState(rental.getCar().getId(), State.AVAILABLE);
        rentalRepository.deleteById(id);
    }

// Business Rules

    private int getTotalPrice(Rental rental)
    {return rental.getRentalForDays() * rental.getDailyPrice();}

    private void checkIfRentedCarState(int id, State state) {
        Car car = carRepository.findById(id).orElseThrow();
        car.setState(state);
        carRepository.save(car);
    }

    private void checkCarForRented(int id) {
        Car car = carRepository.findById(id).orElseThrow();

        if (car.getState().equals(State.RENTED))
            throw new RuntimeException("The rental car cannot be rented.");

        else if (car.getState().equals(State.MAINTENANCE))
            throw new RuntimeException("The car in maintenance cannot be rented.");
    }
}