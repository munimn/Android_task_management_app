/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.lawrence.taskerWeb;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/peoplenew")
@CrossOrigin(origins="*")
public class PersonNewController {
    private PersonNewDAO personnewDAO;
    
    public PersonNewController(PersonNewDAO dao) {
        this.personnewDAO = dao;
    }
    
    
    @GetMapping(params={"id"})
    public PersonNew findById(@RequestParam("id") int id) {
        return personnewDAO.findById(id);
    }
}
