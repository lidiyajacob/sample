package com.example.sample.sample.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sample.sample.model.Employee;
import com.example.sample.sample.repository.EmployeeRepository;

@RestController @CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1")
public class EmployeeController {
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@GetMapping("/employees")
	public List<Employee> getEmployees(){
		return employeeRepository.findAll();
	}
	
	@GetMapping("/employees/{id}")
	public Employee getEmployee(@PathVariable(value="id") Long employeeId){
		Employee employee = employeeRepository.findById(employeeId).orElseThrow(null);
		return employee;
		
	}
	
	@PostMapping("/employees")
	public Employee createEmployee( @Valid @RequestBody Employee employee){
		return employeeRepository.save(employee);
	}
	
	@PutMapping("/employees/{id}")
	public Employee updateEmployee(@PathVariable(value="id") Long employeeId, @Valid @RequestBody Employee employeeDetails){
		Employee employee = employeeRepository.findById(employeeId).orElseThrow(null);
		if(employee !=null){
			employee.setFirstName(employeeDetails.getFirstName());
			employee.setLastName(employeeDetails.getLastName());
			employee.setEmailId(employeeDetails.getEmailId());
		}
		return employee;
	}
	
	@DeleteMapping("/employees/{id}")
	public String deleteEmployee(@PathVariable(value="id") Long employeeId){
		  Employee employee = employeeRepository.findById(employeeId).orElseThrow(null);
		  if(employee!=null){
			  employeeRepository.delete(employee);
			  return "Deleted Successfully";
		  }
		return " Some issue occurred";
	}

}
