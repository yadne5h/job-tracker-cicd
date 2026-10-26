package com.jobtracker.web;

import com.jobtracker.service.ApplicationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/applications")
public class ApplicationServlet extends HttpServlet {

    private final ApplicationService service = new ApplicationService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("applications", service.getAll());
        req.setAttribute("applied", service.countByStatus("Applied"));
        req.setAttribute("interview", service.countByStatus("Interview"));
        req.setAttribute("offer", service.countByStatus("Offer"));
        req.setAttribute("rejected", service.countByStatus("Rejected"));
        req.getRequestDispatcher("/WEB-INF/views/list.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getParameter("action");
        try {
            if ("add".equals(action)) {
                service.add(req.getParameter("company"), req.getParameter("role"));
            } else if ("status".equals(action)) {
                int id = Integer.parseInt(req.getParameter("id"));
                service.updateStatus(id, req.getParameter("status"));
            }
        } catch (IllegalArgumentException e) {
            req.setAttribute("error", e.getMessage());
            doGet(req, resp);
            return;
        }
        resp.sendRedirect(req.getContextPath() + "/applications");
    }
}
