package test.controllers;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import test.beans.student;
import test.dao.stud;

@Controller
public class homecontr

{
	@Autowired
	stud ss;
	@RequestMapping(value="/get",method=RequestMethod.POST)
	
		public String display(@ModelAttribute("s1") student s1)
		{
		
		ss.register(s1);
		return "redirect:/one";
		
}
	@RequestMapping(value="/one")
	public String welcome(Model m)
	{
		List<student> l1=ss.getInfo();
		m.addAttribute("l1", l1);
		return "one";
	}
	
	@RequestMapping(value="del/{id}",method=RequestMethod.GET)
	public String deletedata(@PathVariable int id)
	{
		ss.deletedata(id);
		return "redirect:/one";
		
	}
	
	@RequestMapping(value="edit/{id}",method=RequestMethod.GET)
	public String editdata(@PathVariable int id,Model m)
	{
		student s=ss.editdata(id);
		m.addAttribute("command",s);
		return "edit";
		
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public String updatedata(@ModelAttribute("s1") student s1)
	{
	    ss.updated(s1);
	    return "redirect:/one";
	}
	
	@RequestMapping("/file")
	public String display() 
	{
		return "file_upload";
		
	}
	
	@RequestMapping(value="/upload",method=RequestMethod.POST)
	public String up(@RequestParam CommonsMultipartFile file)
	{
		try {
		String path="C:\\Users\\SWAG__DHWN\\eclipse\\13th_day_22_2_23\\maven";
		String filename=file.getOriginalFilename();
		
		
	     byte b[]=file.getBytes();
	     BufferedOutputStream f1 =new BufferedOutputStream(new FileOutputStream(path+"/"+filename));
	          f1.write(b);
	          f1.flush();
	          f1.close();	     }
		catch (Exception e)
		{
			System.out.println("fail upload");
		}
		return null;
		
	}
	
	@RequestMapping(value="/login")
	public String login() 
	{
		return "login";
		
	}
	@RequestMapping(value="/login_check",method=RequestMethod.POST)
	public String log(@RequestParam("name") String name,@RequestParam("email") String email,HttpSession s1)
	{
	    student s=ss.login_check(name,email);
	if(s!=null)
	{
		s1.setAttribute("email", email);
		return "redirect:/dashboard";
	}
	else
	{
		return "redirect:/login";
	 }
	}
	@RequestMapping(value="/dashboard")
	public String dashboard(HttpServletRequest req) 
	{
		HttpSession s1 =req.getSession(false);
		String x=(String)s1.getAttribute("email");
		if(x==null || s1.getAttribute("email")==null )
		{
			return "redirect:/login";
		}
		else
		{
		return "dashboard";
		}
	}
	
	
	
	@RequestMapping(value="/logout")
	public String logout(HttpServletRequest req)
	
	{   HttpSession s1 =req.getSession();
	    s1.invalidate();
		return "login";
		
	}

}
