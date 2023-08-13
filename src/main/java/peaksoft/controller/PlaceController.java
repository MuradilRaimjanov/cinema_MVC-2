package peaksoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Cinema;
import peaksoft.model.Place;
import peaksoft.model.Room;
import peaksoft.service.impl.MovieService;
import peaksoft.service.impl.PlaceService;
import peaksoft.service.impl.RoomService;

import java.util.List;

@Controller
@RequestMapping("/place")
public class PlaceController {

    private final PlaceService placeService;

    private final RoomService roomService;
    @Autowired
    public PlaceController(PlaceService placeService, RoomService roomService) {
        this.placeService = placeService;
        this.roomService = roomService;
    }

    @ModelAttribute("roomList")
    public List<Room> roomList() {
        return roomService.findAll();
    }


    @GetMapping("/save")
    public String save(Model model) {
        model.addAttribute("place", new Place()
        );
        return "/plac/place_login";
    }

    @PostMapping("/save_place")
    public String saveCinema(@ModelAttribute Place place) {
        placeService.save(place);
        return "redirect:/place/find_all";
    }
    @GetMapping("/find_all")
    public String findAll(Model model) {
        model.addAttribute("all_place", placeService.findAll());
        return "/plac/all_place";
    }
    @GetMapping("/find_by_id/{id}")
    public String findById(@PathVariable("id") int id, Model model) {
        model.addAttribute("place", placeService.findById(id));
        return "/plac/one_place";
    }
    @GetMapping("/delete_place/{id}")
    public String deleteById(@PathVariable int id) {
        placeService.deleteById(id);
        return "redirect:/place/find_all";
    }

    @GetMapping("/update/{place_id}")
    public String update(@PathVariable("place_id") int id, Model model) {
        model.addAttribute("place", placeService.findById(id));
        return "/plac/update_place";
    }

    @PostMapping("/merge_update/{id}")
    public String mergeUpdate(@ModelAttribute Place place, @PathVariable int id){
        placeService.update(id, place);
        return "redirect:/place/find_all";
    }
    @GetMapping("/buy")
    public String buy(){
        return "/plac/buy";
    }
}
