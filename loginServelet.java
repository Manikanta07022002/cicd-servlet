import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String user = req.getParameter("username");
        String pass = req.getParameter("password");

        try {
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://mysql-host:3306/login_app", "root", "rootpassword");

            PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM users WHERE username=? AND password=?");
            ps.setString(1, user);
            ps.setString(2, pass);

            PrintWriter out = res.getWriter();
            if (ps.executeQuery().next()) {
                out.println("Login successful");
            } else {
                out.println("Login failed");
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
