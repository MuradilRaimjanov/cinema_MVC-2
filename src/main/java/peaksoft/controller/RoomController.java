package peaksoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Cinema;
import peaksoft.model.Room;
import peaksoft.service.impl.CinemaService;
import peaksoft.service.impl.RoomService;

import java.util.List;

@Controller
@RequestMapping("/room")
public class RoomController {

    private RoomService roomService;
    private CinemaService cinemaService;


    @Autowired
    public RoomController(RoomService roomService, CinemaService cinemaService) {
        this.roomService = roomService;
        this.cinemaService = cinemaService;
    }
    @ModelAttribute("cinemaList")
    public List<Cinema> cinemaList() {
        return cinemaService.findAll();
    }

    @GetMapping("/save")
    public String save(Model model) {
        model.addAttribute("room", new Room());
        return "/room/room_login";
    }

    @PostMapping("/save_room")
    public String saveCinema(@ModelAttribute Room room) {
        roomService.save(room);
        return "redirect:/room/find_all";
    }
    @GetMapping("/find_all")
    public String findAll(Model model) {
        model.addAttribute("all_room", roomService.findAll());
        return "/room/all_room";
    }
    @GetMapping("/find_by_id/{id}")
    public String findById(@PathVariable("id") int id, Model model) {
        model.addAttribute("room", roomService.findById(id));
        return "/room/one_room";
    }
    @GetMapping("/delete_room/{id}")
    public String deleteById(@PathVariable int id) {
        roomService.deleteById(id);
        return "redirect:/room/find_all";
    }

    @GetMapping("/update/{room_id}")
    public String update(@PathVariable("room_id") int id, Model model) {
        model.addAttribute("room", roomService.findById(id));
        return "/room/update_room";
    }

    @PostMapping("/merge_update/{id}")
    public String mergeUpdate(@ModelAttribute Room room, @PathVariable int id){
        roomService.update(id, room);
        return "redirect:/room/find_all";
    }
}
