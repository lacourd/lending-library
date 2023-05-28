package lacourd.lendinglibrary.controllers;

import lacourd.lendinglibrary.data.StorageLocationRepository;
import lacourd.lendinglibrary.models.StorageLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("storageLocations")
public class StorageLocationController {
    @Autowired
    private StorageLocationRepository storageLocationRepository;

    @GetMapping
    public String displayAllLocations(Model model){
        model.addAttribute("title", "All Storage Locations");
        model.addAttribute("locations", storageLocationRepository.findAll());
        return "storageLocations/index";
    };

    @GetMapping("add")
    public String renderAddStorageLocationForm(Model model){
        model.addAttribute("title", "Add Storage Location");
        model.addAttribute(new StorageLocation());
        return "storageLocations/add";
    };

    @PostMapping("add")
    public String processAddStorageLocationForm(@ModelAttribute @Valid StorageLocation newStorageLocation, Errors errors, Model model){
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Storage Location");
            model.addAttribute(new StorageLocation());
            return "storageLocations/add";
        }
        storageLocationRepository.save(newStorageLocation);
        return "redirect:";
    };
}
