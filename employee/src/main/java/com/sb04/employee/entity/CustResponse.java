package com.sb04.employee.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class CustResponse {
    private List<Customer> data;
    private int pageNumber;
    private int pageSize;
    private Long totalElements;
    private int totalPages;

    private boolean lastPage;
}
