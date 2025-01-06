package com.lengueCode.service;

import com.lengueCode.model.Compte;

import java.util.List;

public interface IClientService {
    List<Compte> getCompteByClient(String client_id);
}
