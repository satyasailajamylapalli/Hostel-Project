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
public class payment extends HttpServlet {

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
             
            String rollno=request.getParameter("rollno");
            String challan=request.getParameter("challan");            
            double deposit=Double.parseDouble(request.getParameter("amount"));
            String date=request.getParameter("date");
                       int x=0;
            Class.forName("com.mysql.jdbc.Driver");
           Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/hostel","root", "root");
           
           Statement stmt=con.createStatement();
           Statement stmt1=con.createStatement();
             Statement stmt2=con.createStatement();
            ResultSet rs1=stmt2.executeQuery("select count(rollno) as count from student where rollno='"+rollno+"'");
           int count;
           if(rs1.next())
           {
              count= Integer.parseInt(rs1.getString("count"));
               
           
           
           if(count == 0)
           {
             
                   out.println("<html><body><script>if(!alert('ERROR:  Roll number not regestered!'))document.location='payment.html';</script></body></html>");
                 
           }
             else
           {
           ResultSet rs=stmt.executeQuery("Select creditbal,duebal from "+rollno+"ldr where sno=(Select MAX(sno) from "+rollno+"ldr)");
        
       
        if(rs.next())
        {
          String creditbal=rs.getString("creditbal");
           String duebal=rs.getString("duebal");
           
           if(creditbal==null && duebal==null){
            x = stmt1.executeUpdate("insert into "+rollno+"ldr(challan,date,deposit,creditbal) values('"+challan+"','"+date+"',"+deposit+","+deposit+")");
           }
                                          
           else if(creditbal!=null && duebal==null){
                   
                    double credit=Double.parseDouble(creditbal); out.println("here");
                    double cred = credit + deposit; 
                    x = stmt.executeUpdate("insert into "+rollno+"ldr(challan,date,deposit,creditbal) values('"+challan+"','"+date+"',"+deposit+","+cred+")");
                    
                }
                else if(creditbal==null && duebal!=null){
                     double debit=Double.parseDouble(duebal);
                    double due=debit-deposit;
                    if (due==0)
                    {
                      x = stmt1.executeUpdate("insert into "+rollno+"ldr(challan,date,deposit) values('"+challan+"','"+date+"',"+deposit+")");
                    }
                    else if(due>0){
                     x = stmt1.executeUpdate("insert into "+rollno+"ldr(challan,date,deposit,duebal) values('"+challan+"','"+date+"',"+deposit+","+due+")");
                    }      
                    else{
                        
                      x = stmt1.executeUpdate("insert into "+rollno+"ldr(challan,date,deposit,creditbal) values('"+challan+"','"+date+"',"+deposit+","+due*(-1)+")");
                    }               
                }
        } 
        else{
            
           x = stmt1.executeUpdate("insert into "+rollno+"ldr(challan,date,deposit,creditbal) values('"+challan+"','"+date+"',"+deposit+","+deposit+")");
            
           
        
        } 
           }
           }
           else
           {
                out.println("<html><body><script>if(!alert('ERROR: Connection lost! '))document.location='index.html'</script></body></html>"); 
           }
           if(x>0)
           {
               out.println("<html><body><script>if(!alert('payment successful'))document.location='payment.html'</script></body></html>"); 
           }
           else{
                out.println("<html><body><script>if(!alert('payment not successful!! try again'))document.location='payment.html'</script></body></html>"); 
           }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(payment.class.getName()).log(Level.SEVERE, null, ex);
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
