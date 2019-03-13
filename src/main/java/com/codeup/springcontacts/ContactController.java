package com.codeup.springcontacts;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ContactController {

    private final ContactRepository contactDao;

    public ContactController(ContactRepository contactDao) {
        this.contactDao = contactDao;
    }

    @GetMapping({"/", "/contacts"})
    public String index(Model model) {
        model.addAttribute("contacts", contactDao.findAll());
        return "index";
    }

    @PostMapping("/contacts/create")
    public String save(
            @RequestParam String firstName,
            @RequestParam char middleInitial,
            @RequestParam String lastName,
            @RequestParam String suffix,
            @RequestParam String email,
            @RequestParam String phoneNumber,
            @RequestParam String address
            ) {
        Contact c = new Contact(firstName, middleInitial, lastName, suffix, email, phoneNumber, address);
        contactDao.save(c);
        return "redirect:/";
    }

}
