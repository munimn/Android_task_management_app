package edu.lawrence.taskerWeb;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/people")
@CrossOrigin(origins="*")
public class PersonController {
    private PersonDAO personDAO;
    
    public PersonController(PersonDAO dao) {
        this.personDAO = dao;
    }
    
    @GetMapping(params={"user","password"})
    public int checkLogin(@RequestParam(value="user") String user,@RequestParam(value="password") String password) {
        Person result = personDAO.findByUserName(user);
        if(result == null)
            return 0;
        if(!result.getPassword().equals(password))
            return 0;
        return result.getId();
    }
    
    @GetMapping("/{id}")
    public Person findById(@PathVariable("id") int id) {
        return personDAO.findById(id);
    }
}