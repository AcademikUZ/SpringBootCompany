package com.example.companyinspringboot.services;

import com.example.companyinspringboot.entity.Address;
import com.example.companyinspringboot.entity.Company;
import com.example.companyinspringboot.entity.Department;
import com.example.companyinspringboot.entity.Worker;
import com.example.companyinspringboot.payload.ApiResult;
import com.example.companyinspringboot.payload.DepartmentDto;
import com.example.companyinspringboot.payload.WorkerDto;
import com.example.companyinspringboot.repository.AddressRepository;
import com.example.companyinspringboot.repository.CompanyRepository;
import com.example.companyinspringboot.repository.DeportmentRepository;
import com.example.companyinspringboot.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WorkerService {

    @Autowired
    WorkerRepository repository;
    @Autowired
    DeportmentRepository deportmentRepository;
    @Autowired
    AddressRepository addressRepository;

    public ApiResult add(WorkerDto workerDto) {
        Optional<Department> optionalDepartment = deportmentRepository.findById(workerDto.getDepartmentId());
        if (!optionalDepartment.isPresent()) {
            return new ApiResult("Bunday department mavjud emas", false);
        }
        Optional<Address> optionalAddress = addressRepository.findById(workerDto.getAddressId());
        if (!optionalAddress.isPresent())
            return new ApiResult("Bunday address mavjud emas", false);

        Worker worker = new Worker();
        worker.setAddress(optionalAddress.get());
        worker.setDepartment(optionalDepartment.get());
        worker.setName(workerDto.getName());
        worker.setPhoneNumber(workerDto.getPhoneNumber());
        repository.save(worker);
        return new ApiResult("Worker muvoffaqiyatli qo'shildi", true);
    }

    public Page<Worker> getAll(Integer page) {
        Pageable pageable = PageRequest.of(page, 10);
        return repository.findAll(pageable);
    }

    public Worker getById(Long id) {
        Optional<Worker> byId = repository.findById(id);
        if (!byId.isPresent())
            return null;
        return byId.get();
    }

    public ApiResult update(Long id, WorkerDto workerDto) {

        Optional<Worker> optionalWorker = repository.findById(id);
        if (!optionalWorker.isPresent()) {
            return new ApiResult("Bunday worker mavjud emas", false);
        }
        Optional<Department> optionalDepartment = deportmentRepository.findById(workerDto.getDepartmentId());
        if (!optionalDepartment.isPresent()) {
            return new ApiResult("Bunday department mavjud emas", false);
        }
        Optional<Address> optionalAddress = addressRepository.findById(workerDto.getAddressId());
        if (!optionalAddress.isPresent())
            return new ApiResult("Bunday address mavjud emas", false);

        Worker worker = optionalWorker.get();
        worker.setAddress(optionalAddress.get());
        worker.setDepartment(optionalDepartment.get());
        worker.setName(workerDto.getName());
        worker.setPhoneNumber(workerDto.getPhoneNumber());
        repository.save(worker);
        return new ApiResult("Worker muvoffaqiyatli taxrirlandi", true);
    }


    public ApiResult delete(Long id) {

        Optional<Worker> optionalWorker = repository.findById(id);
        if (!optionalWorker.isPresent()) {
            return new ApiResult("Bunday worker mavjud emas", false);
        }
        try {
            repository.deleteById(id);
            return new ApiResult("Muvoffaqiyatli o'chirildi", true);
        } catch (Exception e) {
            return new ApiResult("Xatolik", false);
        }
    }


}
