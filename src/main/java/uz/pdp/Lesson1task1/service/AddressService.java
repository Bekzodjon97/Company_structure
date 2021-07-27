package uz.pdp.Lesson1task1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.Lesson1task1.entity.Address;
import uz.pdp.Lesson1task1.payload.AddressDto;
import uz.pdp.Lesson1task1.payload.ApiResponse;
import uz.pdp.Lesson1task1.repository.AddressRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {
    @Autowired
    AddressRepository addressRepository;

    public ResponseEntity<List<Address>> get(){
        List<Address> addressList = addressRepository.findAll();
        return ResponseEntity.ok(addressList);
    }

    public ResponseEntity<Address> getBYId(Integer id){
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (!optionalAddress.isPresent())
            return ResponseEntity.ok(new Address());
        return ResponseEntity.ok(optionalAddress.get());
    }

    public ResponseEntity<ApiResponse> create(AddressDto addressDto){
        Address newAddress=new Address();
        newAddress.setStreet(addressDto.getStreet());
        newAddress.setHomeNumber(addressDto.getHomeNumber());
        addressRepository.save(newAddress);
        ApiResponse apiResponse=new ApiResponse("Address saqlandi", true);
        return ResponseEntity.ok(apiResponse);
    }

    public ApiResponse delete(Integer id){
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (!optionalAddress.isPresent())
            return new ApiResponse("Bunday address mavjud emas", false);
        addressRepository.deleteById(id);
        return new ApiResponse("Address o'chirildi", true);
    }

    public  ApiResponse update(Integer id, AddressDto addressDto){
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (!optionalAddress.isPresent())
            return new ApiResponse("Bunday address mavjud emas", false);
        Address editedAddress = optionalAddress.get();
        editedAddress.setStreet(addressDto.getStreet());
        editedAddress.setHomeNumber(addressDto.getHomeNumber());
        addressRepository.save(editedAddress);
        return new ApiResponse("Muvafaqiyaqli o'zgartirildi", true);
    }
}
