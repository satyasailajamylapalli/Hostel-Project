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
public class newbills extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String hmess= request.getParameter("hmess");
            int mess=Integer.parseInt(request.getParameter("mess"));
            int elec=Integer.parseInt(request.getParameter("elec"));
            String month= request.getParameter("month");
            String year=request.getParameter("year");
            
      
           
            Class.forName("com.mysql.jdbc.Driver");
           Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/hostel","root", "root");
           
           Statement stmt=con.createStatement();
           ResultSet rs=stmt.executeQuery("select rollno,sname from student where mess='"+hmess+"'");
           out.println("<html><head>\n" +
"<title>NEW BILLS</title>\n" +
"<link rel='stylesheet' href=\"w3.css\" />\n" +
                   "<script type='text/javascipt'>\n"+
                   "function copyText(){ src=document.getElementById('source'); "
                   + "dest=document.getElementById('dest');"+
                   "dest.value=src.value;}</script>"+
"</head>\n" +
"<body bgcolor='#736c6d'>\n" +
"<form id='form' name='form' method='post' action='submit'>\n" +
"<table width=\"1288\" height=\"95\"  align=\"center\" bgcolor=\"#FFFFFF\" class=\"w3-large\" border=\"1\">\n" +
"  <tr>\n" +
"    <th width='5%' height='42' scope=\"col\">S.NO.</th>\n" +
"    <th width='15%' scope=\"col\">ROLL NUMBER</th>\n" +
"    <th width='264' scope=\"col\">NAME</th>\n" +
"    <th width='15%' scope=\"col\">MESS CHARGE</th>\n" +
"    <th width='15%' scope=\"col\">ELE CHARGE</th>\n" +
"    <th width='15%' scope=\"col\">TOTAL CHARGE</th>\n" +
"  </tr>"); 
           
           int i=0;
            while(rs.next()){
                
                i++;
                String rollno= rs.getString("rollno");
                String sname=rs.getString("sname");
                out.println("<tr><td>"+i+"</td><td><input type='text' name='roll"+i+"' value='"+rollno+"' readonly></td><td><input type='text' size='30' name='name"+i+"' value='"+sname+"' readonly></td>"
                        + "<td><input type='text' size='15'value='"+mess+"' name='mess"+i+"'></td>"
                                + "<td><input type='text' size='15' id='source' value='"+elec+"' onKeyPress='copyText()' name='elect"+i+"'></td>"
                                + "<td><input type='text' size='15' id='dest'  name='total"+i+"'></td></tr>");
            }
            
            out.println("<caption align=\"bottom\" >\n" +
"<center>\n" +
"  <input type=\"submit\" name=\"Submit\" value=\"Submit\" class=\"w3-red w3-large w3-round w3-button\"/>\n" +
"</center></caption></table> <input type='hidden' value='"+i+"' name='i'> <input type='hidden' value='"+month+year+"' name='date'>"+
"</form></body></html>");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(newbills.class.getName()).log(Level.SEVERE, null, ex);
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
        processRequest(request, response);
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
        processRequest(request, response);
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
