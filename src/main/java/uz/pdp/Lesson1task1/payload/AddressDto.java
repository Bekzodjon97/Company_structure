package uz.pdp.Lesson1task1.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {
    @NotNull(message = "Ko'cha nomi bo'sh bo'lmasligi kerak")
    private String street;
    @NotNull(message = "Uy raqami bo'sh bo'lmasligi kerak")
    private String homeNumber;
}
