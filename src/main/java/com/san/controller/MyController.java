package com.san.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.san.model.*;
import com.san.repo.Candidaterepo;
import com.san.repo.Userrepo;
import com.san.repo.Voterepo;


@Controller
@RequestMapping("/voting")
public class MyController {
	
	
	
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	
	@Autowired
    private Userrepo userRepository;
    
    @Autowired
    private Candidaterepo candidateRepository;
    
    @Autowired
    private Voterepo voteRepository;

    @GetMapping("/loginpage")
	public String indexpage() {
		
		
		return "login";
	}
	
	
	
	
	@GetMapping("/showform")
	public String showFormForAdd(Model theModel) {
		User theUserinfo =new User();
		
		
		 theModel.addAttribute("userinfo", theUserinfo);
		
		return "register";
	}
	
	@PostMapping("/save")
	 public String saveUser(@ModelAttribute("userinfo") User theUserinfo)
	 {
		
		theUserinfo.setPassword(passwordEncoder.encode(theUserinfo.getPassword()));
		userRepository.save(theUserinfo);
		 return "redirect:/voting/loginpage";
	 }
	
	
	
	@GetMapping("/homepage")
	public String userpage(Model theModel) {
		List<Candidate> theCandidate1 = candidateRepository.findAll();	

        Optional<User> user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        if (user.isPresent() && !voteRepository.existsByUser(user.get())) {
        	theModel.addAttribute("candidates", candidateRepository.findAll());
        	theModel.addAttribute("vote", new Vote());
            return "home";
        }

		
		return "home";
	}
	
 
	@PostMapping("/vote")
	 public String voteUser(@ModelAttribute Vote  theVote )
	 {
		
		Optional<User> user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        Optional<Candidate> candidate = candidateRepository.findById(theVote.getCandidate().getId());
        if (user.isPresent() && candidate.isPresent()) {
        	theVote.setUser(user.get());
        	theVote.setCandidate(candidate.get());
            voteRepository.save(theVote);
        }
	
		 return "redirect:/voting/homepage";
	 }
	
	
	

	@GetMapping("/adminlist")
	public String listStudent(Model theModel) 
	{
		List<Candidate> theCandidate = candidateRepository.findAll();
		
			theModel.addAttribute("candidates", theCandidate);
			
		return "adminhome";
		
	}
	
	

}
