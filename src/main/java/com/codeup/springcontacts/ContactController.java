package com.codeup.springcontacts;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContactController {

    private final ContactRepository contactDao;

    public ContactController(ContactRepository contactDao) {
        this.contactDao = contactDao;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("contacts", contactDao.findAll());
        return "/index";
    }

}
