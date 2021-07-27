package uz.pdp.Lesson1task1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.Lesson1task1.entity.Address;
import uz.pdp.Lesson1task1.entity.Company;
import uz.pdp.Lesson1task1.payload.ApiResponse;
import uz.pdp.Lesson1task1.payload.CompanyDto;
import uz.pdp.Lesson1task1.repository.AddressRepository;
import uz.pdp.Lesson1task1.repository.CompanyRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    AddressRepository addressRepository;

    public ResponseEntity<List<Company>> get(){
        List<Company> addressList = companyRepository.findAll();
        return ResponseEntity.ok(addressList);
    }

    public ResponseEntity<Company> getBYId(Integer id){
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (!optionalCompany.isPresent())
            return ResponseEntity.ok(new Company());
        return ResponseEntity.ok(optionalCompany.get());
    }

    public ApiResponse create(CompanyDto companyDto){
        boolean existsByCorpName = companyRepository.existsByCorpName(companyDto.getCorpName());
        if (existsByCorpName)
            return new ApiResponse("Bunday nomli company mavjud", false);
        Optional<Address> optionalAddress = addressRepository.findById(companyDto.getAddressId());
        if (!optionalAddress.isPresent())
            return new ApiResponse("Address yo'q", false);
        Company newCompany=new Company();
        newCompany.setCorpName(companyDto.getCorpName());
        newCompany.setDirectorName(companyDto.getDirectorName());
        newCompany.setAddress(optionalAddress.get());
        companyRepository.save(newCompany);
        return new ApiResponse("Company saqlandi", true);
    }

    public ApiResponse delete(Integer id){
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (!optionalCompany.isPresent())
            return new ApiResponse("Bunday Company mavjud emas", false);
        companyRepository.deleteById(id);
        return new ApiResponse("Company o'chirildi", true);
    }

    public  ApiResponse update(Integer id, CompanyDto companyDto){
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (!optionalCompany.isPresent())
            return new ApiResponse("Bunday company mavjud emas", false);
        boolean existsByCorpName = companyRepository.existsByCorpName(companyDto.getCorpName());
        if (existsByCorpName)
            return new ApiResponse("Bunday nomli company mavjud ", false);
        Optional<Address> optionalAddress = addressRepository.findById(companyDto.getAddressId());
        if (!optionalAddress.isPresent())
            return new ApiResponse("Address yo'q ", false);
        Company editedCompany=optionalCompany.get();
        editedCompany.setCorpName(companyDto.getCorpName());
        editedCompany.setDirectorName(companyDto.getDirectorName());
        editedCompany.setAddress(optionalAddress.get());
        companyRepository.save(editedCompany);
        return new ApiResponse("Company o'zgartirildi", true);
    }
}
