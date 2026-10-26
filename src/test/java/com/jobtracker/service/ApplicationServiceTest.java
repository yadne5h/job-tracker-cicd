package com.jobtracker.service;

import com.jobtracker.model.JobApplication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationServiceTest {

    private ApplicationService service;

    @BeforeEach
    void setUp() {
        service = new ApplicationService();
    }

    @Test
    void addCreatesApplicationWithAppliedStatus() {
        JobApplication app = service.add("Google", "DevOps Intern");
        assertEquals(1, app.getId());
        assertEquals("Applied", app.getStatus());
    }

    @Test
    void addRejectsEmptyCompany() {
        assertThrows(IllegalArgumentException.class, () -> service.add("  ", "DevOps Intern"));
    }

    @Test
    void updateStatusChangesStatus() {
        JobApplication app = service.add("Google", "DevOps Intern");
        assertTrue(service.updateStatus(app.getId(), "Interview"));
        assertEquals("Interview", service.getById(app.getId()).getStatus());
    }

    @Test
    void updateStatusRejectsInvalidStatus() {
        JobApplication app = service.add("Google", "DevOps Intern");
        assertThrows(IllegalArgumentException.class, () -> service.updateStatus(app.getId(), "Maybe"));
    }

    @Test
    void countByStatusCountsCorrectly() {
        service.add("A", "x");
        service.add("B", "y");
        service.updateStatus(1, "Offer");
        assertEquals(1, service.countByStatus("Offer"));
        assertEquals(1, service.countByStatus("Applied"));
    }
}
