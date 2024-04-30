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
public class roomrent extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
             String rollno=request.getParameter("rollno");
            String challan=request.getParameter("challan");            
           
            String date=request.getParameter("date");
             double est=Double.parseDouble(request.getParameter("est"));
             double rent=Double.parseDouble(request.getParameter("rent"));
              double wc=Double.parseDouble(request.getParameter("wc"));
               double ta=Double.parseDouble(request.getParameter("t_a"));
               
           int x=0;
             Class.forName("com.mysql.jdbc.Driver");
           Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/hostel","root", "root");
           Statement stmt=con.createStatement();
           Statement stmt1=con.createStatement();
           ResultSet rs=stmt1.executeQuery("select count(rollno) as count from student where rollno='"+rollno+"'");
           int count;
           if(rs.next())
           {
              count= Integer.parseInt(rs.getString("count"));
                out.println(count);
           
           
           if(count == 0)
           {
             
                   out.println("<html><body><script>if(!alert('ERROR:  Roll number not regestered!')) document.location='roomrent.html';</script></body></html>");
                 
           }
             else
           {
           
               
                    x=stmt.executeUpdate("insert into "+rollno+"rr (establish,roomrent,wc,tin_audit,challan,date) values("+est+","+rent+","+wc+","+ta+","+challan+",'"+date+"')");
                 
        
                   
                
            }
           }
           
          
           if(x >0)
           {
               out.println("<html><body><script>if(!alert('payment successful'))document.location='officdehome.html'</script></body></html>"); 
           }
            out.println("<html><body><script>if(!alert('ERROR: Something went wrong! Please try again.. '))window.history.back();</script></body></html>"); 
           stmt.close();
           con.commit();
           con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(roomrent.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(roomrent.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(roomrent.class.getName()).log(Level.SEVERE, null, ex);
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
