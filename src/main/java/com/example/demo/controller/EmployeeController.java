package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeServiceImpl;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	public EmployeeServiceImpl empService;
	
	@PostMapping("/addEmp")
	public ResponseEntity<Employee> addEmp(@RequestBody Employee emp)
	{
		System.out.println(emp);
		Employee employee= empService.addEmp(emp);
		return new ResponseEntity<Employee>(employee,HttpStatus.CREATED);//Status Code :201 CREATED
	}
	@GetMapping("/getAllEmp")
	public ResponseEntity<List<Employee>> getAll()
	{
		List<Employee> allEmp=empService.getAllEmployee();
		System.out.println(allEmp);
		return new ResponseEntity<List<Employee>>(allEmp,HttpStatus.OK); //Status Code : 200 OK
	}
	@GetMapping("/getEmpById/{id}")
	public ResponseEntity<Employee> getEmpById(@PathVariable("id") int id)
	{
		Employee emp=empService.getEmpById(id);
		System.out.println(emp);
	  	if(emp!=null) {
	  		 return new ResponseEntity<Employee>(emp,HttpStatus.OK);	
	  	}
	  	else
	  {
	  		return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
	  		
	  	}
	}
	@DeleteMapping("/deleteEmpById/{id}")
	public ResponseEntity<Employee> deleteEmpById(@PathVariable("id") int id)
	{
		empService.deleteEmpById(id);
		return new ResponseEntity<Employee>(HttpStatus.NO_CONTENT ); 
	}
	@PutMapping("/updateEmp/{id}")
	public ResponseEntity<String> update(@RequestBody Employee emp,@PathVariable("id")int empid)
	{
	Employee id=empService.getEmpById(empid);
	if(id.getEmpid()==empid)
	{
	String employee=empService.update(emp,empid);
	return new ResponseEntity<String>(employee,HttpStatus.OK); //Status Code : 200 OK
	}
	else
	{
		return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
	}
	}

}
/*
 * {
    "empid":104,
    "name":"karishma",
    "designation":"SE",
    "salary":60000,
    "address":"Nagpur"

}
*/
