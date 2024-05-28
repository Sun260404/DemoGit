package com.example.demo.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.models.dongiaDTO;
import com.example.demo.models.khuvucDTO;
import com.example.demo.models.khuvucs;
import com.example.demo.services.khuvucRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/khuvucs")
public class khuvucController {
	@Autowired
	private khuvucRepository repo;
	
	@GetMapping({"", "/"})
	public String showkhuvuclist(Model model) {
		List<khuvucs> khuvucs = repo.findAll();
		model.addAttribute("khuvucs",khuvucs);
		return "khuvucs/index";
	}
	
	@GetMapping("/create")
    public String showCreatePage(Model model) {
        khuvucDTO khuvucDto = new khuvucDTO();
        model.addAttribute("khuvucDto", khuvucDto);
        return "khuvucs/CreateKhuvuc";
    }

    @PostMapping("/create")
    public String createProduct(@Valid @ModelAttribute("khuvucDto") khuvucDTO khuvucDto, BindingResult result) {
    	
        if (result.hasErrors()) {
            return "khuvucs/CreateKhuvuc";
        }
        
       
        
        khuvucs khuvuc = new khuvucs();
        khuvuc.setTenkhuvuc(khuvucDto.getTenkhuvuc());
        khuvuc.setMota(khuvucDto.getMota());
        
        repo.save(khuvuc);
        return "redirect:/khuvucs";
    }
  
    @GetMapping("/edit")
    public String showEditPage(Model model, @RequestParam int makhuvuc_id) {
		try {
			khuvucs khuvuc = repo.findById(makhuvuc_id).get();
			model.addAttribute("khuvuc", khuvuc);
			 
			khuvucDTO khuvucDto = new khuvucDTO();
			khuvucDto.setTenkhuvuc(khuvuc.getTenkhuvuc());
			khuvucDto.setMota(khuvuc.getMota());
			
			model.addAttribute("khuvucDto", khuvucDto);
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
		return "khuvucs/EditKhuvuc";
	}
	
	@PostMapping("/edit")
	public String updateKhuvuc(Model model,@RequestParam int makhuvuc_id ,@ModelAttribute("khuvucDTo") @Valid khuvucDTO khuvucDTo, BindingResult result) {
		try {
			khuvucs khuvuc = repo.findById(makhuvuc_id).get();
			model.addAttribute("khuvuc", khuvuc);
			if(result.hasErrors()) {
				return "khuvucs/EditKhuvuc";
			}
			
			khuvuc.setTenkhuvuc(khuvucDTo.getTenkhuvuc());
			khuvuc.setMota(khuvucDTo.getMota());
			
			repo.save(khuvuc);
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
		return "redirect:/khuvucs";
	}
	
	@GetMapping("/delete")
	public String deleteProduct(@RequestParam("id") int id) {
		try {
			khuvucs khuvuc = repo.findById(id).get();
			
			repo.delete(khuvuc);
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
		return "redirect:/khuvucs"; 
	}
}
