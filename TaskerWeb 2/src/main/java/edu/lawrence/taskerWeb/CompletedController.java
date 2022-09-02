/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.lawrence.taskerWeb;

import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/completed")
@CrossOrigin(origins="*")
public class CompletedController {
    private JobDAO jobDAO;
    
    
    public CompletedController(JobDAO dao) {
        this.jobDAO = dao;
    }
    
    @GetMapping(params={"customer"})
    public List<Job> fetchavailableJobs(@RequestParam("customer") int customer) {
        return jobDAO.completedJobs(customer);
    }
   
  
}