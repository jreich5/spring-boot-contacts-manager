package com.codeup.springcontacts;

import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.FileInputStream;
import java.io.IOException;


@Controller
public class ContactController {

    private final ContactRepository contactDao;

    public ContactController(ContactRepository contactDao) {
        this.contactDao = contactDao;
    }

    @GetMapping("/translate/{word}")
    @ResponseBody
    public String translateToGerman(@PathVariable String word, @Value("${GOOGLE_APPLICATION_CREDENTIALS}") String gJson, @Value("${GOOGLE_CLOUD_PROJECT}") String gProj) {
        Translate trans = null;
        try {
            trans = TranslateOptions.newBuilder()
                    .setCredentials(ServiceAccountCredentials.fromStream(new FileInputStream(gJson)))
                    .setProjectId(gProj)
                    .build()
                    .getService();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Translation translation =
                trans.translate(
                        word,
                        Translate.TranslateOption.sourceLanguage("en"),
                        Translate.TranslateOption.targetLanguage("ger"));
        return translation.getTranslatedText();
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
