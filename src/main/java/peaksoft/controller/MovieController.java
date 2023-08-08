package peaksoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Movie;
import peaksoft.service.impl.MovieService;

@Controller
@RequestMapping("/movie")
public class MovieController {

    private final MovieService movieService;
    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/save")
    public String save(Model model) {
        model.addAttribute("movie", new Movie());
        return "/mov/movie_login";
    }

    @PostMapping("/save_move")
    public String saveCinema(@ModelAttribute Movie movie) {
        movieService.save(movie);
        return "redirect:/movie/find_all";
    }
    @GetMapping("/find_all")
    public String findAll(Model model) {
        model.addAttribute("all_movie", movieService.findAll());
        return "/mov/all_movie";
    }
    @GetMapping("/find_by_id/{id}")
    public String findById(@PathVariable("id") int id, Model model) {
        model.addAttribute("movie", movieService.findById(id));
        return "/mov/one_movie";
    }
    @GetMapping("/delete_movie/{id}")
    public String deleteById(@PathVariable int id) {
        movieService.deleteById(id);
        return "redirect:/movie/find_all";
    }

    @GetMapping("/update/{movie_id}")
    public String update(@PathVariable("movie_id") int id, Model model) {
        model.addAttribute("movie", movieService.findById(id));
        return "/mov/update_movie";
    }

    @PostMapping("/merge_update/{id}")
    public String mergeUpdate(@ModelAttribute Movie movie, @PathVariable int id){
        movieService.update(id, movie);
        return "redirect:/movie/find_all";
    }


}

