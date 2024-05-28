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
import com.example.demo.models.danhmucs;
import com.example.demo.models.dongias;
import com.example.demo.models.hoadonDTO;
import com.example.demo.models.hoadons;
import com.example.demo.models.menudouongs;
import com.example.demo.services.banRepository;
import com.example.demo.services.hoadonRepository;

import jakarta.validation.Valid;


@Controller
@RequestMapping("/hoadons")
public class hoadonController {
	@Autowired
    private hoadonRepository repo;
	
	@Autowired
    private banRepository repo2;
    
    @GetMapping({"", "/"})
    public String showhoadonList(Model model) {
        List<hoadons> hoadons = repo.findAll();
        model.addAttribute("hoadons", hoadons);
        return "hoadons/index";
    }
    
    @GetMapping("/create")
    public String showCreatePage(Model model) {
        hoadonDTO hoadonDto = new hoadonDTO();
        model.addAttribute("hoadonDto", hoadonDto);
        return "hoadons/CreateHoadon";
    }

    @PostMapping("/create")
    public String createProduct(@Valid @ModelAttribute("hoadonDto") hoadonDTO hoadonDto, BindingResult result) {
    	if (result.hasErrors()) {
            return "hoadons/CreateHoadon";
        }

			bans ban;
			Date ngayban = new Date();
				try {
					ban = repo2.findById(hoadonDto.getMaban_id())
					         .orElseThrow(() -> new NotFoundException());
					 
					 hoadons hoadon = new hoadons();

					    hoadon.setBan(ban);
					    hoadon.setTongtien(hoadonDto.getTongtien());
					    hoadon.setNgayban(ngayban);
					    repo.save(hoadon);
				} catch (NotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
       
       return "redirect:/hoadons";
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
       @GetMapping("/delete")
   	public String deleteHoadon(@RequestParam("id") int id) {
   		try {
   			hoadons hoadon = repo.findById(id).get();
   			
   			repo.delete(hoadon);
   		} catch (Exception e) {
   			System.out.println("Exception: " + e.getMessage());
   		}
   		return "redirect:/hoadons"; 
   	}
}
     

