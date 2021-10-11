package lk.ijse.dep7.file_upload_back_end;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@MultipartConfig(maxFileSize = 5000)
@WebServlet(name = "StudentServlet", value = "/Students")
public class StudentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("hello serverlet");
    }

    @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         String name = request.getParameter("name");
        String address = request.getParameter("address");
        String contact = request.getParameter("contact");

    }
}
