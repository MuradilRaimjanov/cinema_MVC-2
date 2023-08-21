package peaksoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Cinema;
import peaksoft.model.Movie;
import peaksoft.model.Room;
import peaksoft.model.Session;
import peaksoft.service.impl.MovieService;
import peaksoft.service.impl.RoomService;
import peaksoft.service.impl.SessionService;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/session")
public class SessionController {

    private final SessionService sessionService;

    private final MovieService movieService;

    private final RoomService roomService;

    @Autowired
    public SessionController(SessionService sessionService, MovieService movieService, RoomService roomService) {
        this.sessionService = sessionService;
        this.movieService = movieService;
        this.roomService = roomService;
    }

    @ModelAttribute("movieList")
    public List<Movie> movieList() {
        return movieService.findAll();
    }

    @ModelAttribute("roomList")
    public List<Room> roomList() {
        return roomService.findAll();
    }

    @GetMapping("/save")
    public String save(Model model) {
        Session session = new Session();
        model.addAttribute("ses", session);
        return "/sess/session_login";
    }

    @PostMapping("/save_session")
    public String saveCinema(@ModelAttribute Session session) {
        session.setFinish(LocalDateTime.now().plusHours(session.getDuration()));
        sessionService.save(session);
        return "redirect:find_all";
    }
    @GetMapping("/find_all")
    public String findAll(Model model) {
        model.addAttribute("all_session", sessionService.findAll());
        return "/sess/all_session";
    }

    @GetMapping("/findAllId/{id}")
    public String findAllId(@PathVariable("id") int id, Model model) {
        model.addAttribute("all_session_id", sessionService.findAllId(id));
        return "/sess/all_session_id";
    }


    @GetMapping("/find_by_id/{id}")
    public String findById(@PathVariable("id") int id, Model model) {
        model.addAttribute("ses", sessionService.findById(id));
        return "sess/one_session";
    }
    @GetMapping("/delete_session/{id}")
    public String deleteById(@PathVariable int id) {
        sessionService.deleteById(id);
        return "redirect:/session/find_all";
    }

    @GetMapping("/update/{movie_id}")
    public String update(@PathVariable("session_id") int id, Model model) {
        model.addAttribute("ses", sessionService.findById(id));
        return "/sess/update_session";
    }

    @PostMapping("/merge_update/{id}")
    public String mergeUpdate(@ModelAttribute Session session, @PathVariable int id){
        sessionService.update(id, session);
        return "redirect:/sess/find_all";
    }
}
