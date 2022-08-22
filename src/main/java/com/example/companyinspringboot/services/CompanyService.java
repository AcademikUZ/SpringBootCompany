package com.example.companyinspringboot.services;

import com.example.companyinspringboot.entity.Address;
import com.example.companyinspringboot.entity.Company;
import com.example.companyinspringboot.entity.Company;
import com.example.companyinspringboot.payload.CompanyDto;
import com.example.companyinspringboot.payload.ApiResult;
import com.example.companyinspringboot.repository.AddressRepository;
import com.example.companyinspringboot.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    CompanyRepository repository;
    @Autowired
    AddressRepository addressRepository;

    public ApiResult add(CompanyDto companyDto) {
        Optional<Address> optionalAddress = addressRepository.findById(companyDto.getAddressId());
        if (!optionalAddress.isPresent())
            return new ApiResult("Bunday address mavjud emas", false);
        Company company = new Company();
        company.setAddress(optionalAddress.get());
        company.setCorpName(companyDto.getCorpName());
        company.setDirectorName(companyDto.getDirectorName());
        repository.save(company);
        return new ApiResult("Company muvoffaqiyatli qo'shildi", true);
    }

    public Page<Company> getAll(Integer page) {
        Pageable pageable = PageRequest.of(page, 10);
        return repository.findAll(pageable);
    }

    public Company getById(Long id) {
        Optional<Company> byId = repository.findById(id);
        if (!byId.isPresent())
            return null;
        return byId.get();
    }

    public ApiResult update(Long id, CompanyDto companyDto) {

        Optional<Company> optionalCompany = repository.findById(id);
        if (!optionalCompany.isPresent()) {
            return new ApiResult("Bunday company mavjud emas", false);
        }
        Optional<Address> optionalAddress = addressRepository.findById(companyDto.getAddressId());
        if (!optionalAddress.isPresent())
            return new ApiResult("Bunday address mavjud emas", false);
        Company company = optionalCompany.get();
        company.setAddress(optionalAddress.get());
        company.setCorpName(companyDto.getCorpName());
        company.setDirectorName(companyDto.getDirectorName());
        repository.save(company);
        return new ApiResult("Company muvoffaqiyatli taxrirlandi", true);
    }

   public ApiResult delete(Long id) {

        Optional<Company> optionalCompany = repository.findById(id);
        if (!optionalCompany.isPresent()) {
            return new ApiResult("Bunday company mavjud emas", false);
        }
        try {
            repository.deleteById(id);
            return new ApiResult("Muvoffaqiyatli o'chirildi", true);
        } catch (Exception e) {
            return new ApiResult("Xatolik", false);
        }
    }


}
