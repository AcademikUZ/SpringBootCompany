package com.example.companyinspringboot.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class WorkerDto {

    @NotNull
    private String name;

    @NotNull
    private String phoneNumber;

    @NotNull
    private Long addressId;

    @NotNull
    private Long departmentId;

}
