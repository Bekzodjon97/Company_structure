package uz.pdp.Lesson1task1.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkerDto {
    @NotNull(message = "Ishchi nomi bo'sh bo'lmasligi kerak")
    private String name;
    @NotNull(message = "Telefon raqam bo'sh bo'lmasligi kerak")
    private String phoneNumber;
    @NotNull(message = "Address bo'sh bolmasligi kerak")
    private Integer addressId;
    @NotNull(message = "Department bo'sh bolmasligi kerak")
    private Integer departmentId;
}
