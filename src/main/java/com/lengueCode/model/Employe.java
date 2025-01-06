package com.lengueCode.model;

public class Employe {
    private String employeId;
    private String nom;
    private String role;

    public Employe() {
    }

    public Employe(String employeId, String nom, String role) {
        this.employeId = employeId;
        this.nom = nom;
        this.role = role;
    }

    public String getEmployeId() {
        return employeId;
    }

    public void setEmployeId(String employeId) {
        this.employeId = employeId;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
