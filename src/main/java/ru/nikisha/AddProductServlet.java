package ru.nikisha;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "AddProductServlet", urlPatterns = "/add")
public class AddProductServlet implements Servlet {

    private static final Logger LOG = LoggerFactory.getLogger(AddProductServlet.class);
    private transient ServletConfig config;
    private final List<Product> productList = new ArrayList<>();

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.config = config;
    }

    @Override
    public ServletConfig getServletConfig() {
        return this.config;
    }

    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        int value = Integer.parseInt(request.getParameter("r"));
        if (value > 10) {
            response.getWriter().println("Values have to less than 10");
            return;
        }
        String[] titles = {
                "Title of Hibernate",
                "Title of Spring",
                "Title of Jdbc",
                "Title of Java",
                "Title of HTML",
                "Title of CSS",
                "Title of POSTGRESQL",
                "Title of SQLlite",
                "Title of JS",
                "Title of CSS",
        };
        for (int i = 0; i < titles.length; i++) {
            int r = (int) (Math.random() * value);
            productList.add(new Product(i, titles[r], i * titles.length + 31));
        }

        try (PrintWriter writer = new PrintWriter(response.getWriter())) {
            for (Product product : productList) {
                writer.println("<html><body><p>" + product + "</html></body></p>");
            }
        }

    }

    @Override
    public String getServletInfo() {
        return "ProductServlet";
    }

    @Override
    public void destroy() {
        LOG.info("Servlet {} destroyed", getServletInfo());
    }
}
