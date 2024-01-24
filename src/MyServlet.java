

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
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.setContentType("text/html");
		PrintWriter p = response.getWriter();
		
		String uname = request.getParameter("name");
		String upass = request.getParameter("upass");
		String uemailid = request.getParameter("emailid");
		String gender = request.getParameter("gender");
        String ueligibility = request.getParameter("ueligibility");
		
		p.print("hello");
		p.print("hello "+uname);
			
	
		
		 try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/db", "root", "abc123");
	            Statement s = c.createStatement();

	            String query = "INSERT INTO db_table (Name, Password, Emailid, Gender, Eligibility) VALUES " +
	                           "('" + uname + "','" + upass + "','" + uemailid + "','" + gender + "','" + ueligibility + "')";

	            int a = s.executeUpdate(query);

	            if (a > 0) {
	                p.print("DATA INSERTED");
	            } else {
	                p.print("DATA NOT INSERTED");
	            }

	            // Close the connection
	            s.close();
	            c.close();

	        } catch (ClassNotFoundException | SQLException e) {
	            e.printStackTrace();
	            p.print("DATABASE ERROR");
	        }
	}

}
