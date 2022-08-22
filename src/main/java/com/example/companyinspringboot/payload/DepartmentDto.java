package com.example.companyinspringboot.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class DepartmentDto {

    @NotNull
    private String name;

    @NotNull
    private Long companyId;

}
