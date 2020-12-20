package com.parameta.employee.soap.core.service;

import com.parameta.employee.soap.core.model.Employee;
import com.parameta.employee.soap.core.repo.IEmployeeRepo;
import com.parameta.employee.soap.core.soap.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import java.math.BigInteger;
import java.util.GregorianCalendar;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmployeeService implements IEmployeeService
{

    private final IEmployeeRepo employeeRepo;


    @Override
    public GetAllEmployeesResponse getAll()
    {
        var employees = this.employeeRepo.findAll();
        var employeesSoap = employees.stream().map(this::getEmployeeSoap).collect(Collectors.toList());
        var res = new GetAllEmployeesResponse();
        res.getEmployeeSoap().clear();
        res.getEmployeeSoap().addAll(employeesSoap);
        return res;
    }

    /**
     * @param employee
     * @return
     */
    private EmployeeSoap getEmployeeSoap(Employee employee)
    {
        var employeeSoap = new EmployeeSoap();
        employeeSoap.setId(employee.getId());
        employeeSoap.setNameEmployee(employee.getNameEmployee());
        employeeSoap.setLastname(employee.getLastname());
        employeeSoap.setRoleEmployee(employee.getRoleEmployee());
        employeeSoap.setTypeDocument(TypeDoc.valueOf(employee.getTypeDocument()));
        employeeSoap.setDocument(employee.getDocument());
        employeeSoap.setSalary(employee.getSalary());
        try
        {
            GregorianCalendar gc = new GregorianCalendar();

            gc.setTime(employee.getBirthday());
            var birthday = DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
            employeeSoap.setBirthday(birthday);

            gc.setTime(employee.getStartDate());
            var startDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
            employeeSoap.setStartDate(startDate);
        }
        catch (DatatypeConfigurationException e)
        {
            log.error("Error:", e);
        }

        return employeeSoap;
    }

    /**
     * @param employeeSoap
     * @return
     */
    private Employee getEmployee(EmployeeSoap employeeSoap)
    {
        var employee = new Employee();
        employee.setLastname(employeeSoap.getLastname());
        employee.setNameEmployee(employeeSoap.getNameEmployee());
        employee.setDocument(employeeSoap.getDocument());
        employee.setRoleEmployee(employeeSoap.getRoleEmployee());
        employee.setSalary(employeeSoap.getSalary());
        employee.setTypeDocument(employeeSoap.getTypeDocument().value());
        employee.setBirthday(employeeSoap.getBirthday().toGregorianCalendar().getTime());
        employee.setStartDate(employeeSoap.getStartDate().toGregorianCalendar().getTime());
        log.info(employeeSoap.toString());
        log.info(employee.toString());
        return employee;
    }

    @Override
    public GetEmployeeByIdResponse getById(GetEmployeeByIdRequest request)
    {
        var optionalEmployee = this.employeeRepo.findById(request.getId().intValue());
        if (optionalEmployee.isPresent())
        {
            var employeeSoap = this.getEmployeeSoap(optionalEmployee.get());
            var response = new GetEmployeeByIdResponse();
            response.setEmployeeSoap(employeeSoap);
            return response;
        }
        return null;
    }

    @Override
    public GetEmployeeByDocResponse getByDoc(GetEmployeeByDocRequest request)
    {
        var employee = this.employeeRepo.findEmployeesByDocument(request.getDocument());
        var employeeSoap = this.getEmployeeSoap(employee);
        var response = new GetEmployeeByDocResponse();
        response.setEmployeeSoap(employeeSoap);
        return response;
    }

    @Override
    public AddEmployeeResponse save(AddEmployeeRequest body)
    {
        var response = new AddEmployeeResponse();
        var basicResponse = new BasicResponse();
        try
        {
            var employee = getEmployee(body.getEmployeeSoap());
            var saveEmployee = this.employeeRepo.save(employee);
            var message = String.format("The employee %s was saved", saveEmployee.getId());
            basicResponse.setCode(new BigInteger("0"));
            basicResponse.setDescription(message);
            response.setBasicResponse(basicResponse);
            return response;
        }
        catch (Exception ex)
        {
            basicResponse.setCode(new BigInteger("1"));
            basicResponse.setDescription(ex.getMessage());
            response.setBasicResponse(basicResponse);
            return response;
        }
    }
}
