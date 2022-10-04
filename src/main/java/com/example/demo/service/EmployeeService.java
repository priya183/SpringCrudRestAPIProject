package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Employee;

public interface EmployeeService {
	
	public Employee addEmp(Employee emp);
	public List<Employee> getAllEmployee();
	public Employee getEmpById(int empid) ;
	public String deleteEmpById(int empid);
	public String update(Employee emp,int empid);

}
