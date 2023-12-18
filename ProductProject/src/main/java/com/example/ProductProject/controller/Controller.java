package com.example.ProductProject.controller;

import com.example.ProductProject.model.Item;
import com.example.ProductProject.model.Owner;
import com.example.ProductProject.repository.ItemRepository;
import com.example.ProductProject.service.ItemService;
import com.example.ProductProject.service.OwnerService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;
import java.util.Random;

@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    OwnerService ownerService;

    @Autowired
    ItemService itemService;

    @Autowired
    ItemRepository itemRepository;

    @GetMapping("/login")
    public ModelAndView login(){
        ModelAndView nav = new ModelAndView("login");
        nav.addObject("owner", new Owner());
        return nav;
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("owner") Owner owner){
        Owner oauthOwner = ownerService.login(owner.getUsername(), owner.getPassword());
        System.out.println(oauthOwner);
        if(Objects.nonNull(oauthOwner)){
            return "redirect:/";
        } else {
            return "redirect:/login";
        }
    }

    @RequestMapping(value = {"/logout"}, method = RequestMethod.POST)
    public String logoutDo(HttpServletRequest request, HttpServletResponse response){
        return "redirect:/login";
    }

    @GetMapping("/register")
    public ModelAndView register(){
        ModelAndView nav = new ModelAndView("register");
        nav.addObject("owner", new Owner());
        return nav;
    }

    @RequestMapping(value = {"/register"}, method = RequestMethod.POST)
    public String registerDo(HttpServletRequest request, HttpServletResponse response){
        return "redirect:/register";
    }

    @RequestMapping("/register")
    public ModelAndView getRegister(){
        Owner owner = new Owner();
        return new ModelAndView("register","obj",owner);
    }

    @RequestMapping("/addOwner")
    public String addOwner(@ModelAttribute("owner") Owner owner){
        Random rnd = new Random();
        Long n = rnd.nextLong(50);
        Long b = Long.valueOf(n);
        if(b == owner.getId()){
            b++;
        }
        owner.setId(b);
        ownerService.createOwner(owner);
        return "redirect:/login";
    }

    @GetMapping("/")
    public ModelAndView listProperties(){
        ModelAndView modelAndView = new ModelAndView("index");
        // List<Property> properties = propRepo.findAll();
        modelAndView.addObject("Item", itemService.getItem());
        return modelAndView;
    }

    @GetMapping("/addItem")
    public ModelAndView addItem(){
        ModelAndView model = new ModelAndView("addItem");
        model.addObject("item", new Item());
        return model;
    }

    @RequestMapping(value = {"/addItem"}, method = RequestMethod.POST)
    public String ItemAdd(HttpServletRequest request, HttpServletResponse response){
        return "redirect:/addItem";
    }

    @RequestMapping("/addItem")
    public ModelAndView getItem(){
        Item item = new Item();
        return new ModelAndView("register","obj",item);
    }

    @RequestMapping("/addItems")
    public String addItem(@ModelAttribute("item") Item item){
        itemService.createItem(item);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteItem(@PathVariable("id") long id, Model model) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        itemRepository.delete(item);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateProperty(@PathVariable("id") long id, Model model) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

        model.addAttribute("item", item);
        itemRepository.delete(item);
        return "update-item";
    }
}
