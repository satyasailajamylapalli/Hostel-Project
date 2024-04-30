/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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



public class regester extends HttpServlet {

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
          
          try {
            Class.forName("com.mysql.jdbc.Driver");
               Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/hostel","root", "root");
               
            String rollno =request.getParameter("rollno");
            String sname =request.getParameter("sname");
            String fname =request.getParameter("fname");
            String course =request.getParameter("course");
           //date formate
            String sample =request.getParameter("dob");                  
              DateFormat forma=new SimpleDateFormat("yyyy-MM-dd");
              java.util.Date date = forma.parse(sample);           
              java.sql.Date dob=new java.sql.Date(date.getTime());
            String gender =request.getParameter("gender");
            String cast =request.getParameter("cast");
            String address =request.getParameter("address");
        
             String scholar =request.getParameter("scholar");
            String hostel =request.getParameter("hostel");
            int roomno=Integer.parseInt(request.getParameter("roomno"));
            String mess =request.getParameter("mess");
            String sample1 =request.getParameter("doj");
              java.util.Date date1=forma.parse(sample1);
              java.sql.Date doj=new java.sql.Date(date1.getTime());
               String cmrno =request.getParameter("cmrno");
            double cm=Double.parseDouble(request.getParameter("cm")); 
               String email =request.getParameter("email");
            String smobno =request.getParameter("smobno");
            String pmobno =request.getParameter("pmobno");  
           
         out.println(fname+gender+address+scholar+hostel+roomno+mess+doj+cmrno+cm+email+smobno+pmobno+course);
        Statement stmt=con.createStatement();
           Statement stmt1=con.createStatement(); 
          
        Statement stmt2=con.createStatement();
        Statement stmt3=con.createStatement();
            
           ResultSet rs3=stmt3.executeQuery("select count(rollno) as count from student where rollno='"+rollno+"'");
           int count;
           if(rs3.next())
           {
              count= Integer.parseInt(rs3.getString("count"));
                
           
           
           if(count != 0)
           {
             
                   out.println("<html><body><script>if(!alert('ERROR:  Roll number already regestered!')) window.history.back();</script></body></html>");
                 
           }
             else
           {
            
         int x = stmt.executeUpdate("insert into student (rollno,sname,fname,course,dob,gender,cast,address,scholarship,hostel,roomno,mess,doj,cmrno,cautionmoney,email,smobno,pmobno) values ('"+rollno+"','"+sname+"','"+fname+"','"+course+"','"+dob+"','"+gender+"','"+cast+"','"+address+"','"+scholar+"','"+hostel+"',"+roomno+",'"+mess+"','"+doj+"','"+cmrno+"',"+cm+",'"+email+"','"+smobno+"','"+pmobno+"')");
  

       if(x > 0){
           
          
    

            stmt1.execute("create table "+rollno+"ldr( sno int NOT NULL AUTO_INCREMENT,date Date,challan varchar(10), month varchar(10),messcharge decimal(10,2),eleccharge decimal(10,2), deposit Decimal(10,2),boardingcharges decimal(10,2),creditbal Decimal(10,2),duebal decimal(10,2),remarks varchar(40),PRIMARY KEY(sno))");
            stmt2.execute("create table "+rollno+"rr(establish Decimal(10,2),roomrent Decimal(10,2),wc Decimal(10,2),tin_audit Decimal(10,2),challan varchar(5),date Date)");
              //  response.sendRedirect("index.html");
           out.println("<html><body><script>if(!alert('Registered Successfully'))document.location='registration.html'</script></body></html>"); 
       }
           
       else{    
        
           out.println("<html><body><script>if(!alert('ERROR: Something went Wrong! Please try again..'))window.history.back();</script></body></html>"); 
       }      
       }
      }
           else
           {
                out.println("<html><body><script>if(!alert('ERROR: Connection lost! '))document.location='index.html'</script></body></html>"); 
           }
             
           stmt2.close();  
           stmt1.close();
           stmt.close();
           con.commit();
           con.close();
       
          }
           catch (SQLException | ParseException ex) {
            Logger.getLogger(regester.class.getName()).log(Level.SEVERE, null, ex);
        }
          } catch (ClassNotFoundException ex) {
            Logger.getLogger(regester.class.getName()).log(Level.SEVERE, null, ex);
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
