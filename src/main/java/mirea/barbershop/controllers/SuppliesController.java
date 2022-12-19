package mirea.barbershop.controllers;

import mirea.barbershop.models.Supplies;
import mirea.barbershop.models.TypeOfSupplies;
import mirea.barbershop.services.SuppliesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/supplies")
public class SuppliesController {
    private final SuppliesService suppliesService;
    private final TypeOfSupplies typeOfSupp liesService;

    public SuppliesController(SuppliesService suppliesService, TypeOfSupplies typeOfSuppliesService) {
        this.suppliesService = suppliesService;
        this.typeOfSuppliesService = typeOfSuppliesService;
    }

    @GetMapping()
    public String index(Model model, @RequestParam(value = "page", required = false) Integer page,
                        @RequestParam(value = "count", required = false) Integer suppliesPerPage,
                        @RequestParam(value = "sort", required = false) boolean sortByName) {
        if (page == null || suppliesPerPage == null)
            model.addAttribute("supplies", suppliesService.findAll(sortByName));
        else
            model.addAttribute("supplies", suppliesService.findWithPagination(page, suppliesPerPage, sortByName));
        return "supplies/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, @ModelAttribute("supplies")Supplies supplies) {
        model.addAttribute("supply", suppliesService.findOne(id));
        model.addAttribute("services", suppliesService.getServicesBySupplyId(id));
        TypeOfSupplies owner = suppliesService.getSupplyOwner(id);

        if (owner != null)
            model.addAttribute("owner", owner);
        else
            model.addAttribute("typeOfSupplies", typeOfSuppliesService.findAll());

        return "repairMaterials/show";
    }

    @GetMapping("/new")
    public String newRepairMaterial(@ModelAttribute("repairMaterial")RepairMaterial repairMaterial) {
        return "repairMaterials/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("repairMaterial") @Valid RepairMaterial repairMaterial,
                         BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return "repairMaterials/new";

        suppliesService.save(repairMaterial);
        return "redirect:/repairMaterials";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("repairMaterial", suppliesService.findOne(id));
        return "repairMaterials/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("repairMaterial") @Valid RepairMaterial repairMaterial, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "repairMaterials/edit";

        suppliesService.update(id, repairMaterial);
        return "redirect:/repairMaterials";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        suppliesService.delete(id);
        return "redirect:/repairMaterials";
    }

    @PatchMapping("/{id}/release")
    public String release(@PathVariable("id") int id) {
        suppliesService.release(id);
        return "redirect:/repairMaterials/" + id;
    }

    @PatchMapping("/{id}/assign")
    public String assign(@PathVariable("id") int id, @ModelAttribute("provider") Provider selectedProvider) {
        suppliesService.assign(id, selectedProvider);
        return "redirect:/repairMaterials/" + id;
    }

    @GetMapping("/search")
    public String searchPage() {
        return "repairMaterials/search";
    }

    @PostMapping("/search")
    public String makeSearch(Model model, @RequestParam("query") String query) {
        model.addAttribute("repairMaterials", suppliesService.searchByName(query));
        return "repairMaterials/search";
    }
}
