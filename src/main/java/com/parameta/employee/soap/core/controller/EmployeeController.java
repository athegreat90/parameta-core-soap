package com.parameta.employee.soap.core.controller;

import com.parameta.employee.soap.core.service.IEmployeeService;
import com.parameta.employee.soap.core.soap.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
@Slf4j
@RequiredArgsConstructor
public class EmployeeController {

    private static final String NAMESPACE_URI = "https://parameta-employee-soap.herokuapp.com/ws/gen";

    private final IEmployeeService employeeService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllEmployeesRequest")
    @ResponsePayload
    public GetAllEmployeesResponse getAll() {
        return this.employeeService.getAll();
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getEmployeeByIdRequest")
    @ResponsePayload
    public GetEmployeeByIdResponse getById(@RequestPayload GetEmployeeByIdRequest request) {
        return this.employeeService.getById(request);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getEmployeeByDocRequest")
    @ResponsePayload
    public GetEmployeeByDocResponse getByDoc(@RequestPayload GetEmployeeByDocRequest request) {
        return this.employeeService.getByDoc(request);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addEmployeeRequest")
    @ResponsePayload
    public AddEmployeeResponse addArticle(@RequestPayload AddEmployeeRequest request) {
        return this.employeeService.save(request);
    }
}
