package com.example.companyinspringboot.services;

import com.example.companyinspringboot.entity.Address;
import com.example.companyinspringboot.payload.AddressDto;
import com.example.companyinspringboot.payload.ApiResult;
import com.example.companyinspringboot.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    AddressRepository repository;

    public ApiResult add(AddressDto addressDto) {
        Address address = new Address();
        address.setHomeNumber(addressDto.getHomeNumber());
        address.setStreet(addressDto.getStreet());
        repository.save(address);
        return new ApiResult("Address muvoffaqiyatli qo'shildi", true);
    }

    public Page<Address> getAll(Integer page) {
        Pageable pageable = PageRequest.of(page, 10);
        return repository.findAll(pageable);
    }

    public Address getById(Long id) {
        Optional<Address> byId = repository.findById(id);
        if (!byId.isPresent())
            return null;
        return byId.get();
    }

    public ApiResult update(Long id, AddressDto addressDto) {

        Optional<Address> optionalAddress = repository.findById(id);
        if (!optionalAddress.isPresent()) {
            return new ApiResult("Bunday address mavjud emas", false);
        }
        Address address = optionalAddress.get();
        address.setHomeNumber(addressDto.getHomeNumber());
        address.setStreet(addressDto.getStreet());
        repository.save(address);
        return new ApiResult("Address muvoffaqiyatli taxrirlandi", true);
    }

    public ApiResult delete(Long id) {

        Optional<Address> optionalAddress = repository.findById(id);
        if (!optionalAddress.isPresent()) {
            return new ApiResult("Bunday address mavjud emas", false);
        }
        try {
            repository.deleteById(id);
            return new ApiResult("Muvoffaqiyatli o'chirildi", true);
        } catch (Exception e) {
            return new ApiResult("Xatolik", false);
        }
    }


}
