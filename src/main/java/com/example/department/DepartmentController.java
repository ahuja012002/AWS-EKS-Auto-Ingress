package com.example.department;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
public class DepartmentController {

	
	 @Autowired
	    private DepartmentService departmentService;
	 
	    @GetMapping("/home")
	    public String viewHomePage( Model model) {
	        model.addAttribute("alldeptList", departmentService.getDepartmentData());
	        return "home";
	    }
	    
	 
	   
	    @GetMapping("/addnew")
	    public String addNewEmployee(Model model) {
	        Department department = new Department();
	        model.addAttribute("department", department);
	        return "newDepartment";
	    }
	 
	    @PostMapping("/updateDepartment")
	    public String updateEmployee(@ModelAttribute("department") Department department) {
	    	departmentService.updateDepartmentData(department);
	        return "redirect:/home";
	    }
	    
	    @PostMapping("/save")
	    public String saveEmployee(@ModelAttribute("department") Department department) {
	    	departmentService.saveDepartmentData(department);
	        return "redirect:/home";
	    }
	 
	    @GetMapping("/showFormForUpdate/{id}")
	    public String updateForm(@PathVariable(value = "id") long id, Model model) {
	    	Department department = departmentService.getDeptById(id);
	        model.addAttribute("department", department);
	        return "update";
	    }
	 
	    @GetMapping("/deleteDepartment/{id}")
	    public String deleteThroughId(@PathVariable(value = "id") long id) {
	    	departmentService.deleteDepartmentData(id);
	        return "redirect:/home";
	 
	    }
}
