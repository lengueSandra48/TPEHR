package com.lengueCode.server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class SimpleHttpServer {
    public static void main(String[] args) throws IOException {
        //creer un serveur sur le port 8080
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        //Definissez un contexte pour "/clients"
        server.createContext("/clients", new ClientHandler());

        //Demarrez le serveur
        server.setExecutor(null);
        System.out.println("Serveur demarrer sur le http://localhost:8080/");
        server.start();
    }
    public static class ClientHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String method = exchange.getRequestMethod(); //recuper la method http (get, post, update, delete)
            String path = exchange.getRequestURI().getPath();
            String[] parts = path.split("/");

            if (parts.length == 3){
                String client_id = parts[2];
                String response = "Comptes pour le client ID" + client_id;

                exchange.sendResponseHeaders(200, response.getBytes().length);
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }else {
                String response = "URL invalide. Utilisez /clients/{id}";
                exchange.sendResponseHeaders(400, response.getBytes().length);
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }

        }
    }

}
