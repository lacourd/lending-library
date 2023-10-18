package lacourd.lendinglibrary.controllers;

import lacourd.lendinglibrary.data.TagRepository;
import lacourd.lendinglibrary.models.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

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
        model.addAttribute(new Tag());
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

    @PostMapping("delete")
    public String processDeleteTag(@RequestParam int tagId) {
        Tag tagToDelete = tagRepository.findById(tagId).orElse(null);

        if (tagToDelete != null) {
            tagRepository.deleteById(tagId);
        }
        return "redirect:";
    }

    @PostMapping("edit")
    public String processEditTag(@RequestParam int tagId, String name) {
        Tag tagToEdit = tagRepository.findById(tagId).orElse(null);
        String tagNameToSet;
        if (name.contains("#")) {
            tagNameToSet = name.substring(1);
        } else {
            tagNameToSet = name;
        }

        if (tagToEdit != null) {
            tagToEdit.setName(tagNameToSet);
            tagRepository.save(tagToEdit);
        }
        return "redirect:";
    }
}
