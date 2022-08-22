package com.example.companyinspringboot.controller;

import com.example.companyinspringboot.entity.Company;
import com.example.companyinspringboot.entity.Department;
import com.example.companyinspringboot.payload.ApiResult;
import com.example.companyinspringboot.payload.CompanyDto;
import com.example.companyinspringboot.payload.DepartmentDto;
import com.example.companyinspringboot.services.CompanyService;
import com.example.companyinspringboot.services.DeportmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/department")
public class DeportmentController {

    @Autowired
    DeportmentService service;

    @GetMapping("/getall")
    public ResponseEntity<Page<Department>> getAll(@RequestParam Integer page) {
        Page<Department> all = service.getAll(page);
        return ResponseEntity.status(all.hasContent() ? HttpStatus.OK : HttpStatus.BAD_REQUEST).body(all);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Department department = service.getById(id);
        return ResponseEntity.status(department != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST).body(department != null ? department:null);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@Valid @PathVariable Long id, @RequestBody DepartmentDto departmentDto) {
        ApiResult apiResult = service.update(id, departmentDto);
        return ResponseEntity.status(apiResult.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.BAD_REQUEST).body(apiResult);
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@Valid @RequestBody DepartmentDto departmentDto) {
        ApiResult apiResult = service.add(departmentDto);
        return ResponseEntity.status(apiResult.isSuccess() ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST).body(apiResult);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        ApiResult apiResult = service.delete(id);
        return ResponseEntity.status(apiResult.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST).body(apiResult);
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
