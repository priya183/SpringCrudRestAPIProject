package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	public EmployeeRepository empRepo;

	public Employee addEmp(Employee emp) {
		
		 Employee employee=null; 
		 try { 
			 employee=(Employee)empRepo.save(emp); 
		 }
		 catch(Exception e) 
		 {
		 System.out.println("Exception while adding the employee");
		 }
		 
		return employee;
	}

	public List<Employee> getAllEmployee() {
		List<Employee> allEmp = null;

		try {
			allEmp = empRepo.findAll();
		} catch (Exception e) {
			System.out.println("Exception getting while fetching the all employees");
		}
		return allEmp;
	}

	@SuppressWarnings("deprecation")
	public Employee getEmpById(int empid) {
		Employee emp = null;
		try {
			emp =empRepo.findById(empid).get();
		} catch (Exception e) {
			System.out.println("Exception while getting employee deails by id");
		}
		return emp;
	}

	public String deleteEmpById(int empid) {
		 try {
	            if (empid != 0) {
	                empRepo.deleteById(empid);
	            }
	       } catch (Exception e) {
	            System.out.println(e.getMessage());
	            return "employee whose id " + empid + " is not  present";
	        }
       return "employee whose id " + empid + " is  deleted successfully..";
	    }
	

	public String update(Employee emp,int empid) {
		Employee employee = null;
		try {
			employee = getEmpById(emp.getEmpid());
//employee != null && 
			if (employee == null && empid==employee.getEmpid()) {
				employee.setName(emp.getName());
				employee.setAddress(emp.getAddress());
				employee.setDesignation(emp.getDesignation());
				employee.setSalary(emp.getSalary());
				empRepo.save(employee);
			}

		} catch (Exception e) {
			System.out.println("Exception while modifing Employee");
			 return "employee whose id " + empid + " is not  present";
		}
		 return "employee whose id " + empid + " is  updated successfully..";
	}
}
