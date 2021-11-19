package net.jerome.projecttracker;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AppController {
	@Autowired
	private BugService service;
	
	@RequestMapping("/")
	public String viewHomePage(Model model) {
		List<Bug> listBugs = service.listAll();
		model.addAttribute("listBugs", listBugs);
		
		return "index";
	}
	
	@RequestMapping("/new")
	public String showNewBugForm(Model model) {
		Bug bug = new Bug();
		model.addAttribute("bug", bug);
		
		return "new_bug";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveBug(@ModelAttribute("bug") Bug bug) {
		service.save(bug);
		
		return "redirect:/";
	}
	
	@RequestMapping("/edit/{id}")
	public ModelAndView showEditBugForm(@PathVariable(name = "id") Long id) {
		ModelAndView mav = new ModelAndView("edit_bug");
		
		Bug bug = service.get(id);
		mav.addObject("bug", bug);
		
		return mav;
	}	
	
	@RequestMapping("/delete/{id}")
	public String deleteBug(@PathVariable(name = "id") Long id) {
		service.delete(id);
		
		return "redirect:/";
	}
	
	
}
