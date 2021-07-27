package uz.pdp.Lesson1task1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.Lesson1task1.entity.Worker;
import uz.pdp.Lesson1task1.payload.ApiResponse;
import uz.pdp.Lesson1task1.payload.WorkerDto;
import uz.pdp.Lesson1task1.service.WorkerService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/worker")
public class WorkerController {
    @Autowired
    WorkerService workerService;

    /**
     * get all address
     * @return ResponseEntity
     */
    @GetMapping
    public ResponseEntity<List<Worker>> get(){
        return workerService.get();
    }

    /**
     * Get by id
     * @param id
     * @return ResponseEntity
     */
    @GetMapping("/{id}")
    public ResponseEntity<Worker> getById(@PathVariable Integer id){
        return workerService.getBYId(id);
    }

    /**
     * Create
     * @param workerDto
     * @return Response<ApiResponse>
     */
    @PostMapping
    public ResponseEntity<ApiResponse> create(@Valid @RequestBody WorkerDto workerDto){
        ApiResponse apiResponse = workerService.create(workerDto);
        if (apiResponse.isSuccess())
            return ResponseEntity.status(200).body(apiResponse);
        return ResponseEntity.status(409).body(apiResponse);
    }


    /**
     * Delete by id
     * @param id
     * @return ResponseEntity<ApiResponse>
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Integer id){
        ApiResponse apiResponse = workerService.delete(id);
        if (apiResponse.isSuccess())
            return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
    }


    /**
     * Update
     * @param id
     * @param workerDto
     * @return ResponseEntity<ApiResponse>
     */
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable Integer id,@Valid @RequestBody WorkerDto workerDto ){
        ApiResponse apiResponse = workerService.update(id, workerDto);
        if (apiResponse.isSuccess())
            return ResponseEntity.status(200).body(apiResponse);
        return ResponseEntity.status(409).body(apiResponse);

    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
