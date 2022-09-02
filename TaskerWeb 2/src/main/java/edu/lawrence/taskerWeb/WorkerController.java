/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.lawrence.taskerWeb;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feedback")
@CrossOrigin(origins="*")
public class WorkerController {
    private WorkerDAO workerDAO;
    
    public WorkerController(WorkerDAO dao) {
        workerDAO = dao;
    }
    
 
    
    @PostMapping
    public int save(@RequestBody Worker work) {
        return workerDAO.save(work);
    }
}
