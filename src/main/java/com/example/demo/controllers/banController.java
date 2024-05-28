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

import com.example.demo.models.banDTO;
import com.example.demo.models.bans;
import com.example.demo.models.danhmucDTO;
import com.example.demo.models.danhmucs;
import com.example.demo.models.dongias;
import com.example.demo.models.khuvucDTO;
import com.example.demo.models.khuvucs;
import com.example.demo.models.menudouongs;
import com.example.demo.services.banRepository;
import com.example.demo.services.khuvucRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/bans")
public class banController {
    private khuvucs khuvuc;
    private banDTO banDto;
	
	@Autowired
	private banRepository repo;
	
	@Autowired
	private khuvucRepository repo2;
	
	@GetMapping({"", "/"})
	public String showbanlist(Model model) {
		List<bans> bans = repo.findAll();
		model.addAttribute("bans",bans);
		return "bans/index";
	}
	@GetMapping("/create")
    public String showCreatePage(Model model) {
        banDTO banDto = new banDTO();
        model.addAttribute("banDto", banDto);
        return "bans/CreateBan";
    }

	@PostMapping("/create")
	public String createBan(@Valid @ModelAttribute("banDto") banDTO banDto, BindingResult result) {

	    if (result.hasErrors()) {
	        return "bans/CreateBan";
	    }



	    try {
	        khuvuc = repo2.findById(banDto.getMakhuvuc_id())
	                      .orElseThrow(() -> new NotFoundException());

	        bans ban = new bans();
	        ban.setTenban(banDto.getTenban());
	        ban.setTrangthai(banDto.getTrangthai());
	        ban.setMota(banDto.getMota());
	        ban.setKhuvuc(khuvuc);
	        repo.save(ban);
	    } catch (NotFoundException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }

	    return "redirect:/bans";
	}

	 @GetMapping("/edit")
	    public String showEditPage(Model model, @RequestParam int maban_id) {
			try {
				bans ban = repo.findById(maban_id).get();
				model.addAttribute("ban", ban);
			    banDTO banDto = new banDTO();
			    banDto.setTenban(ban.getTenban());
				banDto.setTrangthai(ban.getTrangthai());
				banDto.setMota(ban.getMota());
				banDto.setMakhuvuc_id(2);
				model.addAttribute("banDto", banDto);
			} catch (Exception e) {
				System.out.println("Exception: " + e.getMessage());
			}
			return "bans/EditBan";
		}
	 @PostMapping("/edit")
		public String updateBan(Model model,@RequestParam int maban_id ,@ModelAttribute("banDTo") @Valid banDTO banDTo, BindingResult result) {
			try {
				bans ban = repo.findById(maban_id).get();
				model.addAttribute("ban", ban);
				if(result.hasErrors()) {
					return "bans/EditBan";
				}
			    khuvuc = repo2.findById(banDto.getMakhuvuc_id())
			                      .orElseThrow(() -> new NotFoundException());
				
				ban.setTenban(banDTo.getTenban());
				ban.setTrangthai(banDTo.getTrangthai());
				ban.setMota(banDTo.getMota());
				ban.setKhuvuc(khuvuc);
				
				repo.save(ban);
			} catch (Exception e) {
				System.out.println("Exception: " + e.getMessage());
			}
			return "redirect:/bans";
		}
//	
	@GetMapping("/delete")
	public String deleteBan(@RequestParam("id") int id) {
		try {
			bans ban = repo.findById(id).get();
			
			repo.delete(ban);
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
		return "redirect:/bans"; 
	}
}
