package rent.a.car.rentacar.business.abstracts;

import rent.a.car.rentacar.business.dto.requests.creates.CreateInvoiceRequest;
import rent.a.car.rentacar.business.dto.requests.updates.UpdateInvoiceRequest;
import rent.a.car.rentacar.business.dto.responses.creates.CreateInvoiceResponse;
import rent.a.car.rentacar.business.dto.responses.gets.invoice.GetAllInvoicesResponse;
import rent.a.car.rentacar.business.dto.responses.gets.invoice.GetInvoiceResponse;
import rent.a.car.rentacar.business.dto.responses.updates.UpdateInvoiceResponse;

import java.util.List;

public interface InvoiceService {
    List<GetAllInvoicesResponse> getAll();
    GetInvoiceResponse getById(int id);
    CreateInvoiceResponse add(CreateInvoiceRequest request);
    UpdateInvoiceResponse update(int id, UpdateInvoiceRequest request);
    void delete(int id);
}
