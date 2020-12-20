package com.parameta.employee.soap.core.service;

import com.parameta.employee.soap.core.soap.*;

public interface IEmployeeService {
    GetAllEmployeesResponse getAll();

    GetEmployeeByIdResponse getById(GetEmployeeByIdRequest request);

    GetEmployeeByDocResponse getByDoc(GetEmployeeByDocRequest request);

    AddEmployeeResponse save(AddEmployeeRequest body);
}
