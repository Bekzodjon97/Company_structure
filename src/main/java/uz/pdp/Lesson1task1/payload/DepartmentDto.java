package uz.pdp.Lesson1task1.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDto {
    @NotNull(message = "Korxona bolimi nomi bo'sh bo'lmasligi kerak")
    private String name;

    @NotNull(message = "Korxona bo'sh bolmasligi kerak")
    private Integer companyId;
}
