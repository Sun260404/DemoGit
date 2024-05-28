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

import com.example.demo.models.bans;
import com.example.demo.models.danhmucDTO;
import com.example.demo.models.danhmucs;
import com.example.demo.models.khuvucDTO;
import com.example.demo.models.khuvucs;
import com.example.demo.services.danhmucRepository;

import jakarta.validation.Valid;


@Controller
@RequestMapping("/danhmucs")
public class danhmucController {
	@Autowired
    private danhmucRepository repo;
    
    @GetMapping({"", "/"})
    public String showDanhmucList(Model model) {
        List<danhmucs> danhmucs = repo.findAll();
        model.addAttribute("danhmucs", danhmucs);
        return "danhmucs/index";
    }
    
    @GetMapping("/create")
    public String showCreatePage(Model model) {
        danhmucDTO danhmucDto = new danhmucDTO();
        model.addAttribute("danhmucDto", danhmucDto);
        return "danhmucs/CreateDanhmuc";
    }

    @PostMapping("/create")
    public String createDanhmuc(@Valid @ModelAttribute("danhmucDto") danhmucDTO danhmucDto, BindingResult result) {
    	
        if (result.hasErrors()) {
            return "danhmucs/CreateDanhmuc";
        }
        
        danhmucs danhmuc = new danhmucs();
        danhmuc.setTendanhmuc(danhmucDto.getTendanhmuc());
        
        repo.save(danhmuc);
        return "redirect:/danhmucs";
    }
    @GetMapping("/edit")
    public String showEditPage(Model model, @RequestParam int madanhmuc_id) {
		try {
			danhmucs danhmuc = repo.findById(madanhmuc_id).get();
			model.addAttribute("danhmuc", danhmuc);
			 
			danhmucDTO danhmucDto = new danhmucDTO();
			danhmucDto.setTendanhmuc(danhmuc.getTendanhmuc());
			
			model.addAttribute("danhmucDto", danhmucDto);
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
		return "danhmucs/EditDanhmuc";
	}
	
	@PostMapping("/edit")
	public String updateDanhmuc(Model model,@RequestParam int madanhmuc_id ,@ModelAttribute("danhmucDTo") @Valid danhmucDTO danhmucDTo, BindingResult result) {
		try {
			danhmucs danhmuc = repo.findById(madanhmuc_id).get();
			model.addAttribute("danhmuc", danhmuc);
			if(result.hasErrors()) {
				return "danhmucs/EditDanhmuc";
			}
			
			danhmuc.setTendanhmuc(danhmucDTo.getTendanhmuc());
			
			repo.save(danhmuc);
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
		return "redirect:/danhmucs";
	}
    @GetMapping("/delete")
	public String deleteDanhmuc(@RequestParam("id") int id) {
		try {
			danhmucs danhmuc = repo.findById(id).get();
			
			repo.delete(danhmuc);
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
		return "redirect:/danhmucs"; 
	}
}
