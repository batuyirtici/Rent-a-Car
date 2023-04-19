package rent.a.car.rentacar.core.configuration.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import rent.a.car.rentacar.core.exceptions.BusinessException;
import rent.a.car.rentacar.core.utils.results.ExceptionResult;

@RestControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler
    @ResponseStatus (HttpStatus.UNPROCESSABLE_ENTITY)
    public ExceptionResult<BusinessException> handleBusinessException(BusinessException exception) {
        return new ExceptionResult<>(BusinessException.class, exception.getMessage());
    }
}
