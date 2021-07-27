package uz.pdp.Lesson1task1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.Lesson1task1.entity.Address;
import uz.pdp.Lesson1task1.entity.Company;
import uz.pdp.Lesson1task1.entity.Department;
import uz.pdp.Lesson1task1.payload.ApiResponse;
import uz.pdp.Lesson1task1.payload.DepartmentDto;
import uz.pdp.Lesson1task1.repository.CompanyRepository;
import uz.pdp.Lesson1task1.repository.DepartmentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    CompanyRepository companyRepository;

    public ResponseEntity<List<Department>> get(){
        List<Department> departmentList = departmentRepository.findAll();
        return ResponseEntity.ok(departmentList);
    }

    public ResponseEntity<Department> getBYId(Integer id){
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        if (!optionalDepartment.isPresent())
            return ResponseEntity.ok(new Department());
        return ResponseEntity.ok(optionalDepartment.get());
    }

    public ApiResponse create(DepartmentDto departmentDto){
        Optional<Company> optionalCompany = companyRepository.findById(departmentDto.getCompanyId());
        if (!optionalCompany.isPresent())
            return new ApiResponse("Bunday kompaniya mavjud emas", false);
        Department department=new Department();
        department.setName(departmentDto.getName());
        department.setCompany(optionalCompany.get());
        departmentRepository.save(department);
        return new ApiResponse("Address saqlandi", true);
    }

    public ApiResponse delete(Integer id){
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        if (!optionalDepartment.isPresent())
            return new ApiResponse("Bunday Department mavjud emas", false);
        departmentRepository.deleteById(id);
        return new ApiResponse("Department o'chirildi", true);
    }

    public  ApiResponse update(Integer id, DepartmentDto departmentDto){
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        if (!optionalDepartment.isPresent())
            return new ApiResponse("Bunday bo'lim mavjud emas", false);
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (!optionalCompany.isPresent()) {
            return new ApiResponse("Bunday kompany mavjud emas", false);
        }
        Department editedDepartment = optionalDepartment.get();
        editedDepartment.setName(departmentDto.getName());
        editedDepartment.setCompany(optionalCompany.get());
        departmentRepository.save(editedDepartment);
        return new ApiResponse("Muvafaqiyaqli o'zgartirildi", true);
    }
}
