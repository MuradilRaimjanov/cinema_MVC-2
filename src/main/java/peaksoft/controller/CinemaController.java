package peaksoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Cinema;
import peaksoft.model.Room;
import peaksoft.service.impl.CinemaService;
import peaksoft.service.impl.RoomService;

@Controller
@RequestMapping("/cinema")
public class CinemaController {

    private final CinemaService cinemaService;

    private final RoomService roomService;


    @Autowired
    public CinemaController(CinemaService cinemaService, RoomService roomService) {
        this.roomService = roomService;
        this.cinemaService = cinemaService;
    }
    @GetMapping("/main_page")
    public String mainPage(){
        return "/cin/main_page";
    }

    @GetMapping("/save")
    public String save(Model model) {
        model.addAttribute("cinema", new Cinema());
        return "/cin/cinema_login";
    }

    @PostMapping("/save_cinema")
    public String saveCinema(@ModelAttribute Cinema cinema) {
        cinemaService.save(cinema);
        return "redirect:/cinema/find_all";
    }
    @GetMapping("/find_all")
    public String findAll(Model model) {
        model.addAttribute("all_cinema", cinemaService.findAll());
        return "/cin/all_cinema";
    }
    @GetMapping("/find_by_id/{id}")
    public String findById(@PathVariable("id") int id, Model model) {
        model.addAttribute("cinema", cinemaService.findById(id));
        return "/cin/one_cinema";
    }
    @GetMapping("/delete_cinema/{id}")
    public String deleteById(@PathVariable int id) {
        cinemaService.deleteById(id);
        return "redirect:/cinema/find_all";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable("id") int id, Model model) {
        model.addAttribute("cinema", cinemaService.findById(id));
        return "/cin/update_cinema";
    }

    @PostMapping("/merge_update/{id}")
    public String mergeUpdate(@ModelAttribute Cinema cinema, @PathVariable int id){
        cinemaService.update(id, cinema);
        return "redirect:/cinema/find_all";
    }
    @GetMapping ("/find_by_name")
    public String findByName(Model model, @RequestParam(value = "text") String name) {
        model.addAttribute("cinema", cinemaService.findByName(name));
        return "/cin/cinema_by_name";
    }

}
