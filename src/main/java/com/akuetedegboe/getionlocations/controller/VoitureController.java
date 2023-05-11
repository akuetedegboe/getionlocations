package com.akuetedegboe.getionlocations.controller;

import com.akuetedegboe.getionlocations.model.Voiture;
import com.akuetedegboe.getionlocations.service.VoitureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/voiture")
public class VoitureController {

    @Autowired
    private VoitureService voitureService;

    // display list of voitures
    @GetMapping("/")
    public String viewHomePage(Model model) {
        return findPaginated(1, "numMat", "asc", model);
    }

    @GetMapping("/showNewVoitureForm")
    public String showNewVoitureForm(Model model) {
        // create model attribute to bind form data
        Voiture voiture = new Voiture();
        model.addAttribute("voiture", voiture);
        return "voiture/new_voiture";
    }

    @PostMapping("/saveVoiture")
    public String saveVoiture(@ModelAttribute("voiture") Voiture voiture) {
        // save voiture to database
        voitureService.saveVoiture(voiture);
        return "redirect:/voiture/";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable( value = "id") long id, Model model) {

        // get voiture from the service
        Voiture voiture = voitureService.getVoitureById(id);

        // set voiture as a model attribute to pre-populate the form
        model.addAttribute("voiture", voiture);
        return "voiture/update_voiture";
    }

    @GetMapping("/deleteVoiture/{id}")
    public String deleteVoiture(@PathVariable (value = "id") long id) {

        // call delete voiture method 
        this.voitureService.deleteVoitureById(id);
        return "redirect:/voiture/";
    }


    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable (value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = 5;

        Page<Voiture> page = voitureService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Voiture> listVoitures = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listVoitures", listVoitures);
        return "voiture/index";
    }
}
