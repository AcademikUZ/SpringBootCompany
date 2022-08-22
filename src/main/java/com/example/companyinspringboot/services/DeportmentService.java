package com.example.companyinspringboot.services;

import com.example.companyinspringboot.entity.Company;
import com.example.companyinspringboot.entity.Department;
import com.example.companyinspringboot.payload.ApiResult;
import com.example.companyinspringboot.payload.DepartmentDto;
import com.example.companyinspringboot.repository.CompanyRepository;
import com.example.companyinspringboot.repository.DeportmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeportmentService {

    @Autowired
    DeportmentRepository repository;
    @Autowired
    CompanyRepository companyRepository;

    public ApiResult add(DepartmentDto departmentDto) {
        Optional<Company> optionalCompany = companyRepository.findById(departmentDto.getCompanyId());
        if (!optionalCompany.isPresent())
            return new ApiResult("Bunday department mavjud emas", false);
        Department department = new Department();
        department.setCompany(optionalCompany.get());
        department.setName(departmentDto.getName());
        repository.save(department);
        return new ApiResult("Department muvoffaqiyatli qo'shildi", true);
    }

    public Page<Department> getAll(Integer page) {
        Pageable pageable = PageRequest.of(page, 10);
        return repository.findAll(pageable);
    }

    public Department getById(Long id) {
        Optional<Department> byId = repository.findById(id);
        if (!byId.isPresent())
            return null;
        return byId.get();
    }

    public ApiResult update(Long id, DepartmentDto departmentDto) {

        Optional<Department> optionalDepartment = repository.findById(id);
        if (!optionalDepartment.isPresent()) {
            return new ApiResult("Bunday department mavjud emas", false);
        }
        Optional<Company> optionalCompany = companyRepository.findById(departmentDto.getCompanyId());
        if (!optionalCompany.isPresent())
            return new ApiResult("Bunday company mavjud emas", false);
        Department department = optionalDepartment.get();
        department.setCompany(optionalCompany.get());
        department.setName(departmentDto.getName());
        repository.save(department);
        return new ApiResult("department muvoffaqiyatli taxrirlandi", true);
    }


    public ApiResult delete(Long id) {

        Optional<Department> optionalDepartment = repository.findById(id);
        if (!optionalDepartment.isPresent()) {
            return new ApiResult("Bunday department mavjud emas", false);
        }
        try {
            repository.deleteById(id);
            return new ApiResult("Muvoffaqiyatli o'chirildi", true);
        } catch (Exception e) {
            return new ApiResult("Xatolik", false);
        }
    }


}
