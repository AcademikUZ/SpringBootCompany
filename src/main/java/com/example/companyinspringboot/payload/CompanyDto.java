package com.example.companyinspringboot.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class CompanyDto {

    @NotNull
    private String corpName;

    @NotNull
    private String directorName;

    @NotNull
    private Long addressId;


}
