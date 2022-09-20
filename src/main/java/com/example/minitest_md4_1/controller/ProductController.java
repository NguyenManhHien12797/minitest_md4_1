package com.example.minitest_md4_1.controller;

import com.example.minitest_md4_1.model.Product;
import com.example.minitest_md4_1.model.ProductForm;
import com.example.minitest_md4_1.service.product.IProductService;
import org.apache.commons.fileupload.FileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Controller
public class ProductController {
    @Value("C:\\Users\\Acer\\OneDrive\\Desktop\\CG\\Project\\Md4\\img\\")
    private String fileUpload;

    @Autowired
    IProductService productService;

    @GetMapping("/home")
    public ModelAndView home(){
        List<Product> productList = productService.findAll();
        ModelAndView modelAndView = new ModelAndView("/product/index");
        modelAndView.addObject("productList",productList);
        return modelAndView;
    }


    @GetMapping("/create")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("/product/create");
        modelAndView.addObject("product", new Product());
        return modelAndView;
    }
    @PostMapping("/create")
    public ModelAndView createProduct(@ModelAttribute ProductForm productForm){
        List<MultipartFile> multipartFile = productForm.getImg();
        for(int i=0; i<multipartFile.size();i++){
            String fileName= multipartFile.get(i).getOriginalFilename();
            try{
                FileCopyUtils.copy(productForm.getImg().get(i).getBytes(), new File(fileUpload+ fileName));
            }catch (IOException e){
                e.printStackTrace();
            }
            Product product = new Product(productForm.getId(), productForm.getName().get(i),productForm.getCost().get(i),fileName);
            productService.insert(product);
        }
        ModelAndView modelAndView = new ModelAndView("/product/create");
        modelAndView.addObject("product", new Product());
        modelAndView.addObject("mess","Đã thêm thành công!!");

        return modelAndView;
    }


//    @PostMapping("/create")
//    public ModelAndView createProduct(@ModelAttribute ProductForm productForm){
//        MultipartFile multipartFile = productForm.getImg();
//        String fileName= multipartFile.getOriginalFilename();
//        try{
//            FileCopyUtils.copy(productForm.getImg().getBytes(), new File(fileUpload+ fileName));
//        }catch (IOException e){
//            e.printStackTrace();
//        }
//        Product product = new Product(productForm.getId(), productForm.getName(),productForm.getCost(),fileName);
//        ModelAndView modelAndView = new ModelAndView("/product/create");
//        modelAndView.addObject("product", new Product());
//        modelAndView.addObject("mess","Đã thêm thành công!!");
//        productService.insert(product);
//        return modelAndView;
//    }
    @GetMapping("/details/{id}")
    public ModelAndView showDetails(@PathVariable int id){
        ModelAndView modelAndView = new ModelAndView("/product/details");
        Product product = productService.findById(id);
        modelAndView.addObject("product", product);
        return modelAndView;
    }

    @GetMapping("/update/{id}")
    public ModelAndView showFormUpdate(@PathVariable int id){
        ModelAndView modelAndView = new ModelAndView("/product/update");
        Product product = productService.findById(id);
        modelAndView.addObject("product", product);
        return modelAndView;
    }

    @PostMapping("/update/{id}")
    public ModelAndView updateProduct(@ModelAttribute ProductForm productForm){
        List<MultipartFile> multipartFile = productForm.getImg();
        for(int i=0; i<multipartFile.size();i++){
            String fileName= multipartFile.get(i).getOriginalFilename();
            try{
                FileCopyUtils.copy(productForm.getImg().get(i).getBytes(), new File(fileUpload+ fileName));
            }catch (IOException e){
                e.printStackTrace();
            }
            Product product = new Product(productForm.getId(), productForm.getName().get(i),productForm.getCost().get(i),fileName);
            productService.update(product);
        }
        ModelAndView modelAndView =new ModelAndView("/product/update");
        modelAndView.addObject("product", new Product());
        modelAndView.addObject("mess","Đã Update thành công!!");

        return modelAndView;
    }



//    @GetMapping("/delete/{id}")
//    public ModelAndView showDeleteForm(@PathVariable int id) {
//        Product product = productService.findById(id);
//        if (product != null) {
//            ModelAndView modelAndView = new ModelAndView("/product/delete");
//            modelAndView.addObject("product", product);
//            return modelAndView;
//
//        } else {
//            ModelAndView modelAndView = new ModelAndView("/error.404");
//            return modelAndView;
//        }
//    }
//
//    @PostMapping("/delete")
//    public String deleteCustomer(@ModelAttribute("product") Product product) {
//        productService.remove(product.getId());
//        return "redirect:home";
//    }


    @GetMapping("/delete/{id}")
    public ModelAndView showDeleteForm(@PathVariable int id) {
       productService.remove(id);
        List<Product> productList = productService.findAll();
        ModelAndView modelAndView = new ModelAndView("/product/index");
        modelAndView.addObject("productList",productList);
        return modelAndView;
    }

}

