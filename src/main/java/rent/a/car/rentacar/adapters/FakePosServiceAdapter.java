package rent.a.car.rentacar.adapters;

import org.springframework.stereotype.Service;
import rent.a.car.rentacar.business.abstracts.PosService;
import rent.a.car.rentacar.common.constants.Messages;
import rent.a.car.rentacar.core.exceptions.BusinessException;

import java.util.Random;

@Service
public class FakePosServiceAdapter implements PosService {
    @Override
    public void pay() {
        boolean isPaymentSuccessful = new Random().nextBoolean();
        if(!isPaymentSuccessful) throw new BusinessException(Messages.Payment.Failed);
    }
}