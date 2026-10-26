package com.jobtracker.service;

import com.jobtracker.model.JobApplication;

import java.util.ArrayList;
import java.util.List;

public class ApplicationService {

    private final List<JobApplication> applications = new ArrayList<>();
    private int nextId = 1;

    public JobApplication add(String company, String role) {
        if (company == null || company.trim().isEmpty()) {
            throw new IllegalArgumentException("Company name is required");
        }
        if (role == null || role.trim().isEmpty()) {
            throw new IllegalArgumentException("Role is required");
        }
        JobApplication app = new JobApplication(nextId++, company.trim(), role.trim(), "Applied");
        applications.add(app);
        return app;
    }

    public List<JobApplication> getAll() {
        return new ArrayList<>(applications);
    }

    public JobApplication getById(int id) {
        for (JobApplication app : applications) {
            if (app.getId() == id) {
                return app;
            }
        }
        return null;
    }

    public boolean updateStatus(int id, String newStatus) {
        if (!isValidStatus(newStatus)) {
            throw new IllegalArgumentException("Invalid status: " + newStatus);
        }
        JobApplication app = getById(id);
        if (app == null) {
            return false;
        }
        app.setStatus(newStatus);
        return true;
    }

    public int countByStatus(String status) {
        int count = 0;
        for (JobApplication app : applications) {
            if (app.getStatus().equals(status)) {
                count++;
            }
        }
        return count;
    }

    private boolean isValidStatus(String status) {
        return "Applied".equals(status)
                || "Interview".equals(status)
                || "Offer".equals(status)
                || "Rejected".equals(status);
    }
}
