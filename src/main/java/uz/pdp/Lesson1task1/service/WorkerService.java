package uz.pdp.Lesson1task1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.Lesson1task1.entity.Address;
import uz.pdp.Lesson1task1.entity.Department;
import uz.pdp.Lesson1task1.entity.Worker;
import uz.pdp.Lesson1task1.payload.ApiResponse;
import uz.pdp.Lesson1task1.payload.WorkerDto;
import uz.pdp.Lesson1task1.repository.AddressRepository;
import uz.pdp.Lesson1task1.repository.DepartmentRepository;
import uz.pdp.Lesson1task1.repository.WorkerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class WorkerService {
    @Autowired
    WorkerRepository workerRepository;
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    AddressRepository addressRepository;

    public ResponseEntity<List<Worker>> get(){
        List<Worker> workerList = workerRepository.findAll();
        return ResponseEntity.ok(workerList);
    }

    public ResponseEntity<Worker> getBYId(Integer id){
        Optional<Worker> optionalWorker = workerRepository.findById(id);
        return optionalWorker.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.ok(new Worker()));
    }

    public ApiResponse create(WorkerDto workerDto){
        Optional<Department> optionalDepartment = departmentRepository.findById(workerDto.getDepartmentId());
        if (!optionalDepartment.isPresent())
            return new ApiResponse("Bunday bo'lim mavjud emas",false);
        Optional<Address> optionalAddress = addressRepository.findById(workerDto.getAddressId());
        if (!optionalAddress.isPresent())
            return new ApiResponse("Bunday address mabjud emas", false);
        boolean existsByPhoneNumber = workerRepository.existsByPhoneNumber(workerDto.getPhoneNumber());
        if (existsByPhoneNumber)
            return new ApiResponse("Bunday telefon raqam mavjud", false);
        Worker worker=new Worker();
        worker.setName(workerDto.getName());
        worker.setPhoneNumber(workerDto.getPhoneNumber());
        worker.setAddress(optionalAddress.get());
        worker.setDepartment(optionalDepartment.get());
        workerRepository.save(worker);
        return new ApiResponse("Ishchi saqlandi", true);
    }

    public ApiResponse delete(Integer id){
        Optional<Worker> optionalWorker = workerRepository.findById(id);
        if (!optionalWorker.isPresent())
            return new ApiResponse("Bunday ishchi mavjud emas", false);
        workerRepository.deleteById(id);
        return new ApiResponse("Ishchi o'chirildi", true);
    }

    public  ApiResponse update(Integer id, WorkerDto workerDto){
        Optional<Worker> optionalWorker = workerRepository.findById(id);
        if (!optionalWorker.isPresent())
            return new ApiResponse("Bunday ishchi mavjud emas", false);
        Optional<Department> optionalDepartment = departmentRepository.findById(workerDto.getDepartmentId());
        if (!optionalDepartment.isPresent())
            return new ApiResponse("Bunday bo'lim mavjud emas",false);
        Optional<Address> optionalAddress = addressRepository.findById(workerDto.getAddressId());
        if (!optionalAddress.isPresent())
            return new ApiResponse("Bunday address mabjud emas", false);
        boolean existsByPhoneNumber = workerRepository.existsByPhoneNumber(workerDto.getPhoneNumber());
        if (existsByPhoneNumber)
            return new ApiResponse("Bunday telefon raqam mavjud", false);
        Worker worker=optionalWorker.get();
        worker.setName(workerDto.getName());
        worker.setPhoneNumber(workerDto.getPhoneNumber());
        worker.setAddress(optionalAddress.get());
        worker.setDepartment(optionalDepartment.get());
        workerRepository.save(worker);
        return new ApiResponse("Ishchi o'zgartirildi", true);
    }
}
