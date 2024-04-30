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
public class rooms extends HttpServlet {

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
            String hostel=request.getParameter("hostel");
            int rooms=Integer.parseInt(request.getParameter("roomno"));
            
            
             Class.forName("com.mysql.jdbc.Driver");
           Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/hostel","root", "root");
           Statement stmt=con.createStatement();
             Statement stmt1=con.createStatement();
               ResultSet rs = null;
               
            if(rooms==0)
            {
            rs =stmt.executeQuery("select rollno,sname,course,roomno from student where hostel='"+hostel+"' order by roomno");
            }
            else{
                ResultSet rs1=stmt1.executeQuery("select count(roomno) as cnt from student where hostel='"+hostel+"' and roomno="+rooms);
                
                if(rs1.next()){
                int count=Integer.parseInt(rs1.getString("cnt"));
                if(count>0){
                rs =stmt.executeQuery("select rollno,sname,course,roomno from student where hostel='"+hostel+"' and roomno="+rooms);
                }
                else{
                     out.println("<html><body><script>if(!alert('Room number "+rooms+" didnt existed '))window.history.back();</script></body></html>"); 
                }
            }}
           out.println("<html><head>" +
"<title> rooms</title>" +
"<link rel='stylesheet' href='w3.css' />" +                   
"</head>" +
"<body bgcolor='#736c6d'>"+
"<form id='form' name='form' method='post' action='officehome.html'><center>" +
"<table width='1288' height='95'  align='center' bgcolor='#FFFFFF' class='w3-large' border='1'>" +
" <tr>" +
"    <th width='5%' height='42' scope='col'>S.NO.</th>" +
"    <th width='15%' scope='col'>ROLL NUMBER</th>" +
"    <th width='26%' scope='col'>NAME</th>" +
"    <th width='15%' scope='col'>BRANCH</th>" +
"    <th width='15%' scope='col'>ROOM NUMBER</th>" +
" </tr>");
           
         int i=0;
            while(rs.next()){ i++;
                  String rollno= rs.getString("rollno");
                String sname=rs.getString("sname");      
                String branch=rs.getString("course");
                String room=rs.getString("roomno");
                out.println("<tr><td>"+i+"</td><td>"+rollno+"</td><td>"+sname+"</td><td>"+branch+"</td><td>"+room+"</td></tr>");
            }
           out.println("</table><b><input type='submit' name='submit' value='back' class='w3-red w3-large w3-round w3-button'></center></form></body></html>"); 
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(rooms.class.getName()).log(Level.SEVERE, null, ex);
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
