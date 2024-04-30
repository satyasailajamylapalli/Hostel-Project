/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author vijay
 */
public class studentdtl extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.lang.ClassNotFoundException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            Class.forName("com.mysql.jdbc.Driver");
           Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/hostel","root", "root");
           out.println("<html><head><head><body>"
                   + "<form id='form' name='form' method='post' action='officehome.html'>\n" 
                   + "<table border='1' width='100%' height='100%' bgcolor='skyblue'>");
               out.println("<th>ROLL NO</th><th width='10%'>NAME</th><th>FATHER'S NAME</th><th> DOB</th>");
               out.println("<th>COURSE</th><th>ADDRESS</th><th width='3%'>SEX</th><th width='3%'>CAST</th><th width='3%'>SCHO -LOR</th>");
               out.println("<th>HOSTEL</th><th width='3%'>ROOM NO</th><th> MESS</th><th>DOJ</th><th width='3%'>CMR NO</th>");
               out.println("<th> MOBILE NO</th><th>PARENT'S NO</th>");
           Statement stmt=con.createStatement();
        
          
            
                ResultSet  rs =stmt.executeQuery("select * from student order by rollno");
           while(rs.next())
           {
               String rollno=rs.getString("rollno");
               String sname=rs.getString("sname");
               String fname=rs.getString("fname");
               String dob=rs.getString("dob");
               String course=rs.getString("course");
               String address=rs.getString("address");
               String gender=rs.getString("gender");
               String cast=rs.getString("cast");
               String scholar=rs.getString("scholarship");
               String hostel=rs.getString("hostel");
               String roomno=rs.getString("roomno");
               String mess=rs.getString("mess");
               String doj=rs.getString("doj");
               String cmrno=rs.getString("cmrno");
               String smob=rs.getString("smobno");
               String pmob=rs.getString("pmobno");
               
               out.println("<tr><td>"+rollno+"</td><td>"+sname+"</td><td>"+fname+"</td><td>"+dob+"</td>");
               out.println("<td>"+course+"</td><td>"+address+"</td><td>"+gender+"</td><td>"+cast+"</td><td>"+scholar+"</td>");
               out.println("<td>"+hostel+"</td><td>"+roomno+"</td><td>"+mess+"</td><td>"+doj+"</td><td>"+cmrno+"</td>");
               out.println("<td>"+smob+"</td><td>"+pmob+"</td></tr>");
               
           }
           out.println("</table></form></body></html>");
        } catch (SQLException ex) {
            Logger.getLogger(studentdtl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(studentdtl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(studentdtl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
