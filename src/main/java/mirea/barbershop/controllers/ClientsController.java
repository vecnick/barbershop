package mirea.barbershop.controllers;

import mirea.barbershop.models.Client;
import mirea.barbershop.services.ClientsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/clients")
public class ClientsController {
    private final ClientsService clientsService;

    public ClientsController(ClientsService clientsService) {
        this.clientsService = clientsService;
    }

    @GetMapping()
    public String index(Model model, @RequestParam(value = "page", required = false) Integer page,
                        @RequestParam(value = "count", required = false) Integer clientsPerPage,
                        @RequestParam(value = "sort", required = false) boolean sortBySurname) {
        if (page == null || clientsPerPage == null)
            model.addAttribute("clients", clientsService.findAll(sortBySurname));
        else
            model.addAttribute("clients", clientsService.findWithPagination(page, clientsPerPage, sortBySurname));
        return "clients/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("client", clientsService.findOne(id));
        model.addAttribute("services", clientsService.getServicesByClientId(id));
        return "clients/show";
    }

    @GetMapping("/new")
    public String newClient(@ModelAttribute("client") Client client) {
        return "clients/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("client") @Valid Client client,
                         BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return "clients/new";

        clientsService.save(client);
        return "redirect:/clients";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("client", clientsService.findOne(id));
        return "clients/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("client") @Valid Client client, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "clients/edit";

        clientsService.update(id, client);
        return "redirect:/clients";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        clientsService.delete(id);
        return "redirect:/clients";
    }

    @GetMapping("/search")
    public String searchPage() {
        return "clients/search";
    }

    @PostMapping("/search")
    public String makeSearch(Model model, @RequestParam("query") String query) {
        model.addAttribute("clients", clientsService.searchByName(query));
        return "clients/search";
    }
}
