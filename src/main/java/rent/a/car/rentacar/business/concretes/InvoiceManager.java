package rent.a.car.rentacar.business.concretes;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import rent.a.car.rentacar.business.abstracts.InvoiceService;
import rent.a.car.rentacar.business.dto.requests.creates.CreateInvoiceRequest;
import rent.a.car.rentacar.business.dto.requests.updates.UpdateInvoiceRequest;
import rent.a.car.rentacar.business.dto.responses.creates.CreateInvoiceResponse;
import rent.a.car.rentacar.business.dto.responses.gets.invoice.GetAllInvoicesResponse;
import rent.a.car.rentacar.business.dto.responses.gets.invoice.GetInvoiceResponse;
import rent.a.car.rentacar.business.dto.responses.updates.UpdateInvoiceResponse;
import rent.a.car.rentacar.business.rules.InvoiceBusinessRules;
import rent.a.car.rentacar.common.constants.Messages;
import rent.a.car.rentacar.entities.Invoice;
import rent.a.car.rentacar.repository.InvoiceRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class InvoiceManager implements InvoiceService {
    private final ModelMapper mapper;
    private final InvoiceRepository repository;
    private final InvoiceBusinessRules rules;

    @Override
    public List<GetAllInvoicesResponse> getAll() {
        List<Invoice> invoices = repository.findAll();
        List<GetAllInvoicesResponse> response = invoices
                .stream()
                .map(invoice -> mapper.map(invoice, GetAllInvoicesResponse.class))
                .toList();

        return response;
    }

    @Override
    public GetInvoiceResponse getById(int id) {
        rules.checkIfInvoiceExists(id);
        Invoice invoice = repository.findById(id).orElseThrow();
        GetInvoiceResponse response = mapper.map(invoice, GetInvoiceResponse.class);

        return response;
    }

    @Override
    public CreateInvoiceResponse add(CreateInvoiceRequest request) {
        Invoice invoice = mapper.map(request, Invoice.class);
        invoice.setId(0);
        invoice.setTotalPrice(getTotalPrice(invoice));
        repository.save(invoice);
        CreateInvoiceResponse response = mapper.map(invoice, CreateInvoiceResponse.class);

        return response;
    }

    @Override
    public UpdateInvoiceResponse update(int id, UpdateInvoiceRequest request) {
        rules.checkIfInvoiceExists(id);
        Invoice invoice = mapper.map(request, Invoice.class);
        invoice.setId(id);
        invoice.setTotalPrice(getTotalPrice(invoice));
        repository.save(invoice);
        UpdateInvoiceResponse response = mapper.map(invoice, UpdateInvoiceResponse.class);

        return response;
    }

    @Override
    public void delete(int id) {
        rules.checkIfInvoiceExists(id);
        repository.deleteById(id);
    }

    private double getTotalPrice(Invoice invoice)
    { return invoice.getDailyPrice() * invoice.getRentedForDays(); }
}