package com.example.companyinspringboot.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddressDto {

    @NotNull(message = "Street bo'sh bo'lmasligi kerak")
    private String street;

    @NotNull(message = "homeNumber bo'sh bo'lmasligi kerak")
    private String homeNumber;



}
