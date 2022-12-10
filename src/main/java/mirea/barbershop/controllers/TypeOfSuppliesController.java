package mirea.barbershop.controllers;

import mirea.barbershop.models.TypeOfSupplies;
import mirea.barbershop.services.TypeOfSuppliesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/typeOfSupplies")
public class TypeOfSuppliesController {
    private final TypeOfSuppliesService typeOfSuppliesService;

    public TypeOfSuppliesController(TypeOfSuppliesService typeOfSuppliesService) {
        this.typeOfSuppliesService = typeOfSuppliesService;
    }

    @GetMapping()
    public String index(Model model, @RequestParam(value = "page", required = false) Integer page,
                        @RequestParam(value = "count", required = false) Integer typesPerPage,
                        @RequestParam(value = "sort", required = false) boolean sortByName) {
        if (page == null || typesPerPage == null)
            model.addAttribute("types", typeOfSuppliesService.findAll(sortByName));
        else
            model.addAttribute("types", typeOfSuppliesService.findWithPagination(page, typesPerPage, sortByName));
        return "typeOfSupplies/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("typeOfSupplies", typeOfSuppliesService.findOne(id));
        model.addAttribute("supplies", typeOfSuppliesService.getSuppliesByTypeId(id));
        return "typeOfSupplies/show";
    }

    @GetMapping("/new")
    public String newTypeOfSupplies(@ModelAttribute("typeOfSupplies")TypeOfSupplies typeOfSupplies) {
        return "typeOfSupplies/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("typeOfSupplies") @Valid TypeOfSupplies typeOfSupplies,
                         BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return "typeOfSupplies/new";

        typeOfSuppliesService.save(typeOfSupplies);
        return "redirect:/typeOfSupplies";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("typeOfSupplies", typeOfSuppliesService.findOne(id));
        return "typeOfSupplies/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("typeOfSupplies") @Valid TypeOfSupplies typeOfSupplies, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "typeOfSupplies/edit";

        typeOfSuppliesService.update(id, typeOfSupplies);
        return "redirect:/typeOfSupplies";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        typeOfSuppliesService.delete(id);
        return "redirect:/typeOfSupplies";
    }

    @GetMapping("/search")
    public String searchPage() {
        return "typeOfSupplies/search";
    }

    @PostMapping("/search")
    public String makeSearch(Model model, @RequestParam("query") String query) {
        model.addAttribute("typeOfSupplies", typeOfSuppliesService.searchByName(query));
        return "typeOfSupplies/search";
    }
}
