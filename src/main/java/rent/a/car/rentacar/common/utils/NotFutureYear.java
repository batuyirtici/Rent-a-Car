package rent.a.car.rentacar.common.utils;

import jakarta.validation.Payload;

public @interface NotFutureYear {
    String message() default "Model year value cannot be in the future";
    //? Farklı kullanıcı gruplarına özelleştirilmiş işlemler veya özellikler tanımlamak için kullanılır.
    Class<?>[] groups() default {};
    //? veri transfer nesnelerinde belirli bir grubun mesajlarını taşımak için kullanılır.
    Class<? extends Payload>[] payload() default {};
}
