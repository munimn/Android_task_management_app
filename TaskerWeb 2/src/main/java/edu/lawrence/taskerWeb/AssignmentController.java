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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/assignments")
@CrossOrigin(origins="*")
public class AssignmentController {
    private AssignmentDAO assignmentDAO;
    private JobDAO JobDAO;
    
    public AssignmentController(AssignmentDAO dao,JobDAO dao2) {
        this.assignmentDAO = dao;
        this.JobDAO = dao2;
    }
    
    
    
  
 
    
    @PostMapping
    public int save(@RequestBody Assignment a) {
        int i = a.getJob();
        JobDAO.Update(i);
        return assignmentDAO.save(a);
    }
    @GetMapping(params={"job"})
    public int findByjob(@RequestParam("job") int job) {
        Assignment result =assignmentDAO.findByjob(job);
        return result.getWorker();
        
    }
    
    
}

