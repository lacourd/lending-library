package lacourd.lendinglibrary.controllers;

import lacourd.lendinglibrary.data.TagRepository;
import lacourd.lendinglibrary.models.Tag;
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
@RequestMapping("tags")
public class TagController {

    @Autowired
    TagRepository tagRepository;

    @GetMapping
    public String displayTags(Model model) {
        model.addAttribute("title", "All Tags");
        model.addAttribute("tags", tagRepository.findAll());
        return "tags/index";
    }

    @GetMapping("add")
    public String displayCreateTagForm(Model model) {
        model.addAttribute("title", "Add Tag");
        model.addAttribute(new Tag());
        return "tags/add";
    }

    @PostMapping("add")
    public String processCreateTagForm(@ModelAttribute @Valid Tag tag,
                                       Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Tag");
            model.addAttribute(tag);
            return "tags/add";
        }

        tagRepository.save(tag);
        return "redirect:";
    }
}
