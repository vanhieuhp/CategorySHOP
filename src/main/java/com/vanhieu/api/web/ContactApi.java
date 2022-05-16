package com.vanhieu.api.web;

import com.vanhieu.dto.ContactDto;
import com.vanhieu.service.IContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ContactApi {

    @Autowired
    private IContactService contactService;

    @PostMapping("/api/contact")
    public ContactDto createContact(@RequestBody ContactDto contactDto) {
        return contactService.save(contactDto);
    }

    @PutMapping("/api/contact")
    public ContactDto updateContact(@RequestBody ContactDto contactDto) {
        return contactService.save(contactDto);
    }

    @DeleteMapping("/api/contact")
    public ContactDto deleteContact(@RequestBody ContactDto contactDto) {
        return contactService.save(contactDto);
    }
}
