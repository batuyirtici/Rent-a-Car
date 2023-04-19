package rent.a.car.rentacar.business.concretes;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import rent.a.car.rentacar.business.abstracts.PaymentService;
import rent.a.car.rentacar.business.abstracts.PosService;
import rent.a.car.rentacar.business.dto.requests.creates.CreatePaymentRequest;
import rent.a.car.rentacar.business.dto.requests.updates.UpdatePaymentRequest;
import rent.a.car.rentacar.business.dto.responses.creates.CreatePaymentResponse;
import rent.a.car.rentacar.business.dto.responses.gets.payment.GetAllPaymentsResponse;
import rent.a.car.rentacar.business.dto.responses.gets.payment.GetPaymentResponse;
import rent.a.car.rentacar.business.dto.responses.updates.UpdatePaymentResponse;
import rent.a.car.rentacar.business.rules.PaymentBusinessRules;
import rent.a.car.rentacar.common.dto.CreateRentalPaymentRequest;
import rent.a.car.rentacar.entities.Payment;
import rent.a.car.rentacar.repository.PaymentRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class PaymentManager implements PaymentService {
    private final ModelMapper mapper;
    private final PosService posService;
    private final PaymentRepository repository;
    private final PaymentBusinessRules rules;

    @Override
    public List<GetAllPaymentsResponse> getAll() {
        List<Payment> payments = repository.findAll();
        List<GetAllPaymentsResponse> response = payments
                .stream()
                .map(payment -> mapper.map(payment, GetAllPaymentsResponse.class))
                .toList();

        return response;
    }

    @Override
    public GetPaymentResponse getById(int id) {
        rules.checkIfPaymentExists(id);
        Payment payment = repository.findById(id).orElseThrow();
        GetPaymentResponse response = mapper.map(payment, GetPaymentResponse.class);

        return response;
    }

    @Override
    public CreatePaymentResponse add(CreatePaymentRequest request) {
        rules.checkIfCardExists(request.getCardNumber());
        Payment payment = mapper.map(request, Payment.class);
        payment.setId(0);
        repository.save(payment);
        CreatePaymentResponse response = mapper.map(payment, CreatePaymentResponse.class);

        return response;
    }

    @Override
    public UpdatePaymentResponse update(int id, UpdatePaymentRequest request) {
        rules.checkIfPaymentExists(id);
        Payment payment = mapper.map(request,Payment.class);
        payment.setId(id);
        repository.save(payment);
        UpdatePaymentResponse response = mapper.map(payment, UpdatePaymentResponse.class);

        return response;
    }

    @Override
    public void delete(int id) {
        rules.checkIfPaymentExists(id);
        repository.deleteById(id);
    }

    @Override
    public void processRentalPayment(CreateRentalPaymentRequest request) {
        rules.checkIfPaymentIsValid(request);
        Payment payment = repository.findByCardNumber(request.getCardNumber());
        rules.checkIfBalanceIdEnough(payment.getBalance(), request.getPrice());
        posService.pay(); // fake pos service
        payment.setBalance(payment.getBalance() - request.getPrice());
        repository.save(payment);
    }
}