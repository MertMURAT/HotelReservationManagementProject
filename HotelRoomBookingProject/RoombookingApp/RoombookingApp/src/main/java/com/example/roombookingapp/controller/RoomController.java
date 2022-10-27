package com.example.roombookingapp.controller;

import com.example.roombookingapp.entities.Room;
import com.example.roombookingapp.repositories.RoomRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/rooms")
public class RoomController {

    @Autowired
    private RoomRepository roomRepository;

    @RequestMapping("")
    public ModelAndView listRooms(){
        return new ModelAndView("rooms/list", "rooms", roomRepository.findAll());
    }

    @RequestMapping("/add")
    public ModelAndView addRoom(){
        Map<String, Object> model = new HashMap<>();
        model.put("room", new Room("", ""));
        return new ModelAndView("rooms/edit", model);
    }


    @RequestMapping("/edit/{id}")
    public ModelAndView editRoom(@PathVariable Long id){
        Room room = roomRepository.findById(id).get();
        Map<String, Object> model = new HashMap<>();
        model.put("room", room);
        return new ModelAndView("rooms/edit", model);
    }

    @PostMapping("/save")
    public Object saveRoom(@Valid Room room, BindingResult bindingResult, RedirectAttributes attributes){

        if(bindingResult.hasErrors()){
            Map<String, Object> model = new HashMap<>();
            model.put("room", room);
            return new ModelAndView("rooms/edit", model);
        }

        roomRepository.save(room);
        return "redirect:/rooms";
    }

    @RequestMapping("/delete/{id}")
    public String deleteRoom(@PathVariable Long id){
        roomRepository.deleteById(id);
        return "redirect:/rooms";
    }



}
