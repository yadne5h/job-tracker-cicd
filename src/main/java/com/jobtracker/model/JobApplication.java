package com.jobtracker.model;

public class JobApplication {

    private int id;
    private String company;
    private String role;
    private String status;

    public JobApplication(int id, String company, String role, String status) {
        this.id = id;
        this.company = company;
        this.role = role;
        this.status = status;
    }

    public int getId() { return id; }
    public String getCompany() { return company; }
    public String getRole() { return role; }
    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }
}
