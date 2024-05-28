package com.example.demo.controllers;

import java.util.Date;
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
import com.example.demo.models.chitiethoadonDTO;
import com.example.demo.models.chitiethoadons;
import com.example.demo.models.hoadonDTO;
import com.example.demo.models.hoadons;
import com.example.demo.models.menudouongs;
import com.example.demo.services.chitiethoadonRepository;
import com.example.demo.services.hoadonRepository;
import com.example.demo.services.menudouongRepository;

import jakarta.validation.Valid;


@Controller
@RequestMapping("/chitiethoadons")
public class chitiethoadonController {
	@Autowired
    private chitiethoadonRepository repo;
	
	@Autowired
    private hoadonRepository repo1;
	
	@Autowired
    private menudouongRepository repo2;
    
    @GetMapping({"", "/"})
    public String showchitiethoadonList(Model model) {
        List<chitiethoadons> chitiethoadons = repo.findAll();
        model.addAttribute("chitiethoadons", chitiethoadons);
        return "chitiethoadons/index";
    }
    
    @GetMapping("/create")
    public String showCreatePage(Model model) {
        chitiethoadonDTO chitiethoadonDto = new chitiethoadonDTO();
        model.addAttribute("chitiethoadonDto", chitiethoadonDto);
        return "chitiethoadons/CreateCTHD";
    }

    @PostMapping("/create")
    public String createProduct(@Valid @ModelAttribute("chitiethoadonDto") chitiethoadonDTO chitiethoadonDto, BindingResult result) {
    	if (result.hasErrors()) {
            return "chitiethoadons/CreateCTHD";
        }

			hoadons hoadon;
			menudouongs menudouong;
				try {
					hoadon = repo1.findById(chitiethoadonDto.getMahoadon_id())
					         .orElseThrow(() -> new NotFoundException());
					menudouong = repo2.findById(chitiethoadonDto.getMadouong_id())
					         .orElseThrow(() -> new NotFoundException());
					
					 chitiethoadons chitiethoadon = new chitiethoadons();

					 	chitiethoadon.setHoadon(hoadon);
					 	chitiethoadon.setMenudouong(menudouong);
					 	chitiethoadon.setDongia(chitiethoadonDto.getDongia());
					 	chitiethoadon.setSouong(chitiethoadonDto.getSouong());
					 	
					    repo.save(chitiethoadon);
				} catch (NotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
       
       return "redirect:/chitiethoadons";
    }
//    @GetMapping("/edit")
//    public String showEditPage(Model model, @RequestParam("id") Long id) {
//		try {
//			Product prod = repo.findById(id).get();
//			model.addAttribute("prod", prod);
//			 
//			ProductDTO prodDTo = new  ProductDTO();
//			prodDTo.setName(prod.getName());
//			prodDTo.setBrand(prod.getBrand());
//			prodDTo.setCategory(prod.getCategory());
//			prodDTo.setPrice(prod.getPrice());
//			prodDTo.setDescription(prod.getDescription());
//			model.addAttribute("prodDTo", prodDTo);
//		} catch (Exception e) {
//			System.out.println("Exception: " + e.getMessage());
//		}
//		return "products/EditProduct";
//	}
//	
//	@PostMapping("/edit")
//	public String updateProduct(Model model,@RequestParam("id") Long id,@ModelAttribute("productDTo") @Valid ProductDTO productDTo, BindingResult result) {
//		try {
//			Product prod = repo.findById(id).get();
//			model.addAttribute("prod", prod);
//			if(result.hasErrors()) {
//				return "products/EditProduct";
//			}
//			if(!productDTo.getImageFile().isEmpty()) {
//				String uploadDir = "public/images/";
//				Path oldImagePath = Paths.get(uploadDir + prod.getImageFileName());
//				try {
//					Files.delete(oldImagePath);
//				} catch (Exception e) {
//					System.out.println("Exception: " + e.getMessage());
//				}
//				
//				MultipartFile image = productDTo.getImageFile();
//				Date createdAt = new Date();
//				String storageFileName = image.getOriginalFilename(); 
//				try(InputStream inputstream = image.getInputStream()){
//					Files.copy(inputstream,Paths.get(uploadDir + storageFileName),StandardCopyOption.REPLACE_EXISTING);
//				}
//				prod.setImageFileName(storageFileName);
//				
//			}
//			prod.setName(productDTo.getName());
//			prod.setBrand(productDTo.getBrand());
//			prod.setCategory(productDTo.getCategory());
//			prod.setDescription(productDTo.getDescription());
//			prod.setPrice(productDTo.getPrice());
//			
//			repo.save(prod);
//		} catch (Exception e) {
//			System.out.println("Exception: " + e.getMessage());
//		}
//		return "redirect:/products";
//	}
//	
    @GetMapping("/delete")
	public String deleteCTHD(@RequestParam("id") int id) {
		try {
			chitiethoadons chitiethoadon = repo.findById(id).get();
			
			repo.delete(chitiethoadon);
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
		return "redirect:/chitiethoadons"; 
	}
}
