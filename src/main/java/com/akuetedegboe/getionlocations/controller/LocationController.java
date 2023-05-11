package com.akuetedegboe.getionlocations.controller;

import com.akuetedegboe.getionlocations.model.Client;
import com.akuetedegboe.getionlocations.model.Employe;
import com.akuetedegboe.getionlocations.model.Location;
import com.akuetedegboe.getionlocations.model.Voiture;
import com.akuetedegboe.getionlocations.service.ClientService;
import com.akuetedegboe.getionlocations.service.EmployeService;
import com.akuetedegboe.getionlocations.service.LocationService;
import com.akuetedegboe.getionlocations.service.VoitureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/location")
public class LocationController {
    @Autowired
    private VoitureService voitureService;
    @Autowired
    private ClientService clientService;
    @Autowired
 private EmployeService employeService;
    @Autowired
    private LocationService locationService;

    // display list of locations
    @GetMapping("/")
    public String viewHomePage(Model model) {
        return findPaginated(1, "dateLocation", "asc", model);
    }

    @GetMapping("/showNewLocationForm")
    public String showNewLocationForm(Model model) {
        List<Voiture> voitures=voitureService.getAllVoitures();
        List<Client> clients=clientService.getAllClients();
        List<Employe> employes=employeService.getAllEmployes();
        // create model attribute to bind form data
        Location location = new Location();
        model.addAttribute("location", location);
        model.addAttribute("voitures", voitures);
        model.addAttribute("clients", clients);
        model.addAttribute("employes", employes);
        return "location/new_location";
    }

    @PostMapping("/saveLocation")
    public String saveLocation(@ModelAttribute("location") Location location) {
        // save location to database


        locationService.saveLocation(location);
        return "redirect:/location/";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable( value = "id") long id, Model model) {

        // get location from the service
        Location location = locationService.getLocationById(id);

        // set location as a model attribute to pre-populate the form
        model.addAttribute("location", location);
        return "location/update_location";
    }

    @GetMapping("/deleteLocation/{id}")
    public String deleteLocation(@PathVariable (value = "id") long id) {

        // call delete location method 
        this.locationService.deleteLocationById(id);
        return "redirect:/location/";
    }


    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable (value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = 5;

        Page<Location> page = locationService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Location> listLocations = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listLocations", listLocations);
        return "location/index";
    }
}
