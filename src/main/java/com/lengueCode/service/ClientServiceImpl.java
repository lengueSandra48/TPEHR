package com.lengueCode.service;


import com.lengueCode.dao.Clientdao;
import com.lengueCode.model.Client;
import com.lengueCode.model.Compte;

import java.util.List;

public class ClientServiceImpl implements IClientService{

    Clientdao clientdao;
   // Comptedao comptedao;

    @Override
    public List<Compte> getCompteByClient(String client_id) {

        Client client = clientdao.getClientById(client_id);
        if (client == null) {
            throw new IllegalArgumentException("Le client avec l'ID " + client_id + " n'existe pas.");
        }

        List<Compte> comptes = clientdao.getComptesByClientId(client_id);
        if (comptes == null || comptes.isEmpty()) {
            throw new IllegalArgumentException("Le client avec l'ID " + client_id + " n'a pas de comptes associés.");
        }else {
            comptes.forEach(System.out::println);
        }

        return comptes;
    }
}
