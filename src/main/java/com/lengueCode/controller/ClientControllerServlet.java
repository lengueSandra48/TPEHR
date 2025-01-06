package com.lengueCode.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lengueCode.model.Compte;
import com.lengueCode.service.ClientServiceImpl;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.ServiceConfigurationError;

//@WebServlet(name = "cs", urlPatterns = {"/api/client"})
public class ClientControllerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ClientServiceImpl clientService;

    @Override
    public void init(){
        //Inintialisation du service
        //Executee juste apres instanciation de la servlet par le serveur Tomcat
        this.clientService = new ClientServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServiceConfigurationError, IOException{

        //traitement effectue si une requete http est envoye avec Get

        String clientId = request.getParameter("clientId");
        List<Compte> comptes = clientService.getCompteByClient(clientId);
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.write(new ObjectMapper().writeValueAsString(comptes));
        out.flush();

    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServiceConfigurationError, IOException{

        //traitement effectue si une requete http est envoye avec post
    }

    @Override
    public void destroy(){
        //Executee juste avant la destruction de la servlet
        //Au moment de l'arret de l'application
    }





}
