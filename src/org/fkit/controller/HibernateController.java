package org.fkit.controller;


import org.fkit.entity.one.Person;
import org.fkit.service.FPersonService;
import org.fkit.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HibernateController {
	
	@Autowired
    private PersonService personService;

	@Autowired
    private FPersonService fpersonService;
//	@Autowired
//    private SessionFactory sessionFactory;
    @RequestMapping(value = "savePerson", method = RequestMethod.GET)
    @ResponseBody
    public String savePerson(){
        try {
			personService.savePerson();
    	  
    	  //Person person=(Person)session.load(Person.class,new Long(1)) ;
    	 
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return "savePerson!";
    }
    @RequestMapping(value = "loadPerson", method = RequestMethod.GET)
    @ResponseBody
    public String loadPerson(){
    	Person person=(Person)personService.loadPerson(Long.parseLong("1"));
        return person.toString();
    }

    
    @RequestMapping(value = "oneToOne", method = RequestMethod.GET)
    @ResponseBody
    public String OneToOne(){
        try {
			personService.OneToOne();
    	  
    	  //Person person=(Person)session.load(Person.class,new Long(1)) ;
    	 
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return "OneToOne!";
    }
    
    @RequestMapping(value = "manyToOne", method = RequestMethod.GET)
    @ResponseBody
    public String ManyToOne(){
        try {
			personService.ManyToOne();
    	  
    	  //Person person=(Person)session.load(Person.class,new Long(1)) ;
    	 
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return "ManyToOne!";
    }
    @RequestMapping(value = "oneToMany", method = RequestMethod.GET)
    @ResponseBody
    public String OneToMany(){
        try {
			personService.OneToMany();
    	  
    	  //Person person=(Person)session.load(Person.class,new Long(1)) ;
    	 
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return "OneToMany!";
    }
    
    
    
    
    @RequestMapping(value = "manyToMany", method = RequestMethod.GET)
    @ResponseBody
    public String ManyToMany(){
        try {
			personService.ManyToMany();
    	  
    	  //Person person=(Person)session.load(Person.class,new Long(1)) ;
    	 
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return "ManyToMany!";
    }
    
    //双向连接

    @RequestMapping(value = "foneToOne", method = RequestMethod.GET)
    @ResponseBody
    public String foneToOne(){
        try {
        	fpersonService.OneToOne();
    	  
    	  //Person person=(Person)session.load(Person.class,new Long(1)) ;
    	 
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return "foneToOne!";
    }
    
    @RequestMapping(value = "fmanyToOne", method = RequestMethod.GET)
    @ResponseBody
    public String fmanyToOne(){
        try {
        	fpersonService.ManyToOne();
    	  
    	  //Person person=(Person)session.load(Person.class,new Long(1)) ;
    	 
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return "fmanyToOne!";
    }
    @RequestMapping(value = "foneToMany", method = RequestMethod.GET)
    @ResponseBody
    public String foneToMany(){
        try {
        	fpersonService.OneToMany();
    	  
    	  //Person person=(Person)session.load(Person.class,new Long(1)) ;
    	 
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return "foneToMany!";
    }
    
    
    
    
    @RequestMapping(value = "fmanyToMany", method = RequestMethod.GET)
    @ResponseBody
    public String fmanyToMany(){
        try {
        	fpersonService.ManyToMany();
    	  
    	  //Person person=(Person)session.load(Person.class,new Long(1)) ;
    	 
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return "fmanyToMany!";
    }
    
    
}
