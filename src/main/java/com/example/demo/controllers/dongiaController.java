package com.example.demo.controllers;

import java.util.Date;
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
import com.example.demo.models.dongiaDTO;
import com.example.demo.models.dongias;
import com.example.demo.models.khuvucDTO;
import com.example.demo.models.khuvucs;
import com.example.demo.services.dongiaRepository;

import jakarta.validation.Valid;


@Controller
@RequestMapping("/dongias")
public class dongiaController {
	@Autowired
    private dongiaRepository repo;
    
    @GetMapping({"", "/"})
    public String showdongiaList(Model model) {
        List<dongias> dongias = repo.findAll();
        model.addAttribute("dongias", dongias);
        return "dongias/index";
    }
    
    @GetMapping("/create")
    public String showCreatePage(Model model) {
        dongiaDTO dongiaDto = new dongiaDTO();
        model.addAttribute("dongiaDto", dongiaDto);
        return "dongias/CreateDongia";
    }

    @PostMapping("/create")
    public String createProduct(@Valid @ModelAttribute("dongiaDto") dongiaDTO dongiaDto, BindingResult result) {
        
    	if (result.hasErrors()) {
            return "dongias/CreateDanhmuc";
        }
    	
    	Date tungay = new Date();
    	Date denngay = new Date();
    	
        dongias dongia = new dongias();
        dongia.setTungay(tungay);
        dongia.setDenngay(denngay);
        dongia.setMota(dongiaDto.getMota());
        
        repo.save(dongia);
        return "redirect:/dongias";
    }
    
    @GetMapping("/edit")
    public String showEditPage(Model model, @RequestParam int dongia_id) {
		try {
			dongias dongia = repo.findById(dongia_id).get();
			model.addAttribute("dongia", dongia);
			 
			dongiaDTO dongiaDto = new dongiaDTO();
			dongiaDto.setTungay(dongia.getTungay());
			dongiaDto.setDenngay(dongia.getDenngay());
			dongiaDto.setMota(dongia.getMota());
			
			model.addAttribute("dongiaDto", dongiaDto);
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
		return "dongias/EditDongia";
	}
    @PostMapping("/edit")
	public String updateDongia(Model model,@RequestParam int dongia_id ,@ModelAttribute("dongiaDTo") @Valid dongiaDTO dongiaDto, BindingResult result) {
		try {
			dongias dongia = repo.findById(dongia_id).get();
			model.addAttribute("dongia", dongia);
			if(result.hasErrors()) {
				return "dongias/EditDongia";
			}
			
			Date tungay = new Date();
	    	Date denngay = new Date();
			
			dongia.setMota(dongiaDto.getMota());
			
			repo.save(dongia);
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
		return "redirect:/dongias";
	}
    @GetMapping("/delete")
	public String deleteDongia(@RequestParam("id") int id) {
		try {
			dongias dongia = repo.findById(id).get();
			
			repo.delete(dongia);
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
		return "redirect:/dongias"; 
	}
}
