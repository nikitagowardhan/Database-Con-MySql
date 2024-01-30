import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class MyServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter p = response.getWriter();

        String uname = request.getParameter("name");
        String upass = request.getParameter("upass");
        String uemailid = request.getParameter("emailid");
        

        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "root", "abc123");
            Statement statement = c.createStatement();

            String query = "INSERT INTO my_table (name, password, emailid) VALUES " +
                           "('" + uname + "','" + upass + "','" + uemailid + "')";

            int a = statement.executeUpdate(query);

            if (a > 0) {
                p.print("DATA INSERTED");
            } else {
                p.print("DATA NOT INSERTED" );
            }

            statement.close();
            c.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            p.print("DATABASE ERROR: " + e.getMessage());        }
    }
}
