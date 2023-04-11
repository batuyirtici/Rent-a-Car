package rent.a.car.rentacar.business.abstracts;

import rent.a.car.rentacar.business.dto.requests.creates.CreatePaymentRequest;
import rent.a.car.rentacar.business.dto.requests.updates.UpdatePaymentRequest;
import rent.a.car.rentacar.business.dto.responses.creates.CreatePaymentResponse;
import rent.a.car.rentacar.business.dto.responses.gets.payment.GetAllPaymentsResponse;
import rent.a.car.rentacar.business.dto.responses.gets.payment.GetPaymentResponse;
import rent.a.car.rentacar.business.dto.responses.updates.UpdatePaymentResponse;
import rent.a.car.rentacar.common.dto.CreateRentalPaymentRequest;

import java.util.List;

public interface PaymentService {
    List<GetAllPaymentsResponse> getAll();
    GetPaymentResponse getById(int id);
    CreatePaymentResponse add(CreatePaymentRequest request);
    UpdatePaymentResponse update(int id, UpdatePaymentRequest request);
    void delete(int id);
    void processRentalPayment(CreateRentalPaymentRequest request);
}