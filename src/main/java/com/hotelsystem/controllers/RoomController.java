package com.hotelsystem.controllers;

import com.hotelsystem.models.Room;
import com.hotelsystem.services.api.RoomServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RoomController {
    @Autowired
    private RoomServiceAPI roomServiceAPI;

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("list", roomServiceAPI.getAll());

        return "roomList";
    }

    @GetMapping("/save/{id}")
    public String showSave(@PathVariable("id") Long id, Model model){
        if(id != null && id != 0) {
            model.addAttribute("room", roomServiceAPI.get(id));
        } else {
            model.addAttribute("room", new Room());
        }

        return "roomSave";
    }

    @PostMapping("/save")
    public String save(Room room, Model model) {
        roomServiceAPI.save(room);

        return "redirect:/";
    }

    @GetMapping("/delete")
    public String delete(@PathVariable Long id, Model model) {
        roomServiceAPI.delete(id);

        return "redirect:/";
    }
}
