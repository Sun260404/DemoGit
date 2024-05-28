package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.models.bans;
import com.example.demo.models.danhmucs;
import com.example.demo.models.dongias;
import com.example.demo.models.khuvucDTO;
import com.example.demo.models.khuvucs;
import com.example.demo.models.menudouongDTO;
import com.example.demo.models.menudouongs;
import com.example.demo.services.danhmucRepository;
import com.example.demo.services.dongiaRepository;
import com.example.demo.services.menudouongRepository;

import jakarta.validation.Valid;


@Controller
@RequestMapping("/menudouongs")
public class menudouongController {
	
	private dongias dongia;
	private danhmucs danhmuc;
	@Autowired
    private menudouongRepository repo;
	
	@Autowired
    private dongiaRepository repo2;
	
	@Autowired
    private danhmucRepository repo3;
    
    @GetMapping({"", "/"})
    public String showmenudouongList(Model model) {
        List<menudouongs> menudouongs = repo.findAll();
        model.addAttribute("menudouongs", menudouongs);
        return "menudouongs/index";
    }
    
    @GetMapping("/create")
    public String showCreatePage(Model model) {
        menudouongDTO menudouongDto = new menudouongDTO();
        model.addAttribute("menudouongDto", menudouongDto);
        return "menudouongs/CreateMenuDoUong";
    }

    @PostMapping("/create")
    public String createProduct(@Valid @ModelAttribute("menudouongDto") menudouongDTO menudouongDto, BindingResult result) {
    	
    	 if (result.hasErrors()) {
             return "menudouongs/CreateMenuDoUong";
         }
			
				try {
					dongia = repo2.findById(menudouongDto.getDongia_id())
					         .orElseThrow(() -> new NotFoundException());
					 danhmuc = repo3.findById(menudouongDto.getMadanhmuc_id())
					         .orElseThrow(() -> new NotFoundException());
					 menudouongs menudouong = new menudouongs();

					    menudouong.setTendouong(menudouongDto.getTendouong());
					    menudouong.setDongia(dongia);
					    menudouong.setDanhmuc(danhmuc);
					    repo.save(menudouong);
				} catch (NotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        
        return "redirect:/menudouongs";
    }
  
    @GetMapping("/edit")
    public String showEditPage(Model model, @RequestParam int madouong_id) {
		try {
			menudouongs menudouong = repo.findById(madouong_id).get();
			model.addAttribute("menudouong", menudouong);
			 
			menudouongDTO menudouongDto = new menudouongDTO();
			menudouongDto.setTendouong(menudouong.getTendouong());
			menudouongDto.setMadanhmuc_id(2);
			menudouongDto.setDongia_id(2);
			
			
			model.addAttribute("menudouongDto", menudouongDto);
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
		return "menudouongs/EditMenuDoUong";
	}
	
	@PostMapping("/edit")
	public String updateKhuvuc(Model model,@RequestParam int madouong_id ,@ModelAttribute("menudouongDTo") @Valid menudouongDTO menudouongDto, BindingResult result) {
		try {
			menudouongs menudouong = repo.findById(madouong_id).get();
			model.addAttribute("menudouong", menudouong);
			if(result.hasErrors()) {
				return "menudouongs/EditMenuDoUong";
			}
			dongia = repo2.findById(menudouongDto.getDongia_id())
			         .orElseThrow(() -> new NotFoundException());
			 danhmuc = repo3.findById(menudouongDto.getMadanhmuc_id())
			         .orElseThrow(() -> new NotFoundException());
			menudouong.setTendouong(menudouongDto.getTendouong());
			menudouong.setDanhmuc(danhmuc);
			menudouong.setDongia(dongia);
			
			repo.save(menudouong);
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
		return "redirect:/menudouongs";
	}
    
    @GetMapping("/delete")
	public String deleteMenudouong(@RequestParam("id") int id) {
		try {
			menudouongs menudouong = repo.findById(id).get();
			
			repo.delete(menudouong);
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
		return "redirect:/menudouongs"; 
	}
}
