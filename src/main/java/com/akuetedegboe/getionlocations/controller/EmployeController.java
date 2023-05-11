package com.akuetedegboe.getionlocations.controller;

import com.akuetedegboe.getionlocations.model.Employe;
import com.akuetedegboe.getionlocations.service.EmployeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employe")
public class EmployeController {

    @Autowired
    private EmployeService employeService;

    // display list of employes
    @GetMapping("/")
    public String viewHomePage(Model model) {
        return findPaginated(1, "nom", "asc", model);
    }

    @GetMapping("/showNewEmployeForm")
    public String showNewEmployeForm(Model model) {
        // create model attribute to bind form data
        Employe employe = new Employe();
        model.addAttribute("employe", employe);
        return "employe/new_employe";
    }

    @PostMapping("/saveEmploye")
    public String saveEmploye(@ModelAttribute("employe") Employe employe) {
        // save employe to database
        employeService.saveEmploye(employe);
        return "redirect:/employe/";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable( value = "id") long id, Model model) {

        // get employe from the service
        Employe employe = employeService.getEmployeById(id);

        // set employe as a model attribute to pre-populate the form
        model.addAttribute("employe", employe);
        return "employe/update_employe";
    }

    @GetMapping("/deleteEmploye/{id}")
    public String deleteEmploye(@PathVariable (value = "id") long id) {

        // call delete employe method 
        this.employeService.deleteEmployeById(id);
        return "redirect:/employe/";
    }


    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable (value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = 5;

        Page<Employe> page = employeService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Employe> listEmployes = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listEmployes", listEmployes);
        return "employe/index";
    }
}
