package uz.pdp.Lesson1task1.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.Lesson1task1.entity.Address;

import javax.persistence.Column;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDto {
    @NotNull(message = "Korxona nomi bo'sh bo'lmasligi kerak")
    private String corpName;
    @NotNull(message = "Direktor nomi bo'sh bo'lmasligi kerak")
    private String directorName;
    @NotNull(message = "Address bo'sh bolmasligi kerak")
    private Integer addressId;
}
