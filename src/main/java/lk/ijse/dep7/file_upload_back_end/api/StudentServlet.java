package lk.ijse.dep7.file_upload_back_end.api;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import lk.ijse.dep7.file_upload_back_end.dto.StudentDTO;
import lk.ijse.dep7.file_upload_back_end.service.StudentService;
import javax.annotation.Resource;
import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;

@MultipartConfig
@WebServlet(name = "StudentServlet", value = "/students", loadOnStartup = 0)
public class StudentServlet extends HttpServlet {

    @Resource(name = "java:comp/env/jdbc/cp")
    private DataSource dataSource;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Hello Servlet");
        System.out.println(request.getParameter("q"));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String contact = request.getParameter("contact");
        Part picture = request.getPart("picture");

        try (Connection connection = dataSource.getConnection()) {
            StudentService studentService = new StudentService(connection);
            InputStream is = picture.getInputStream();
            byte[] bytes = new byte[is.available()];
            is.read(bytes);

            StudentDTO student = new StudentDTO(name,address, contact, bytes);
            studentService.saveStudent(student);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}
