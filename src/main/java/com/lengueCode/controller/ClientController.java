package com.lengueCode.controller;

import com.lengueCode.model.Compte;
import com.lengueCode.service.ClientServiceImpl;
import com.lengueCode.service.IClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ClientController {

    private ClientServiceImpl clientService;

    @GetMapping("clients/{client_id}")
    private ResponseEntity<List<Compte>> findCompteByClientId(@PathVariable String client_id) {

        //le code pour recuperer les comptes par client.
        List<Compte> comptes = new ArrayList<>();
        try {
            comptes = clientService.getCompteByClient(client_id);


        } catch (Exception e) {
            e.printStackTrace();

        }

        return ResponseEntity.ok(comptes);
    }
}
