package peaksoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Session;
import peaksoft.service.impl.SessionService;

@Controller
@RequestMapping("/session")
public class SessionController {

    private SessionService sessionService;

    @Autowired
    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @GetMapping("/save")
    public String save(Model model) {
        model.addAttribute("session", new Session());
        return "/sess/session_login";
    }

    @PostMapping("/save_session")
    public String saveCinema(@ModelAttribute Session session) {
        sessionService.save(session);
        return "redirect:/sess/find_all";
    }
    @GetMapping("/find_all")
    public String findAll(Model model) {
        model.addAttribute("all_session", sessionService.findAll());
        return "/sess/all_session";
    }
    @GetMapping("/find_by_id/{id}")
    public String findById(@PathVariable("id") int id, Model model) {
        model.addAttribute("session", sessionService.findById(id));
        return "/sess/one_session";
    }
    @GetMapping("/delete_session/{id}")
    public String deleteById(@PathVariable int id) {
        sessionService.deleteById(id);
        return "redirect:/sess/find_all";
    }

    @GetMapping("/update/{movie_id}")
    public String update(@PathVariable("session_id") int id, Model model) {
        model.addAttribute("session", sessionService.findById(id));
        return "/sess/update_session";
    }

    @PostMapping("/merge_update/{id}")
    public String mergeUpdate(@ModelAttribute Session session, @PathVariable int id){
        sessionService.update(id, session);
        return "redirect:/sess/find_all";
    }
}
