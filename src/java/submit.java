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
public class submit extends HttpServlet {

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
             Class.forName("com.mysql.jdbc.Driver");
           Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/hostel","root", "root");
        int i=Integer.parseInt(request.getParameter("i"));
        String date=request.getParameter("date");
        int x = 0;
       
        for(int j=1;j<=i;j++)
        {            
           Statement stmtj=con.createStatement();
             Statement stmt1j=con.createStatement();
            String rollj=request.getParameter("roll"+j);
            double messj=Double.parseDouble(request.getParameter("mess"+j));        
         double elecj=Double.parseDouble(request.getParameter("elect"+j));   
        double bc=messj+elecj;

        ResultSet rsj=stmt1j.executeQuery("Select creditbal,duebal from "+rollj+"ldr where sno=(Select MAX(sno) from "+rollj+"ldr)");
        
        
        if(rsj.next())
        {
          String creditbal=rsj.getString("creditbal");
           String duebal=rsj.getString("duebal");
          if(creditbal==null && duebal==null){
              x=  stmtj.executeUpdate("Insert into "+rollj+"ldr(month,messcharge,eleccharge,boardingcharges,duebal) values ('"+date+"',"+messj+","+elecj+","+bc+","+bc+")");
          }
           else if(creditbal==null && duebal!=null){
                     
                    double debit=Double.parseDouble(duebal); out.println("here");
                    double deb = debit + bc; out.println(deb);
                  x=  stmtj.executeUpdate("Insert into "+rollj+"ldr(month,messcharge,eleccharge,boardingcharges,duebal) values ('"+date+"',"+messj+","+elecj+","+bc+","+deb+")");
                    
                }
                else if(duebal==null && creditbal!=null){
                     double credit=Double.parseDouble(creditbal);
                    double cred=credit-bc;
                    if (cred==0)
                    {
                     x=   stmtj.executeUpdate("Insert into "+rollj+"ldr(month,messcharge,eleccharge,boardingcharges) values ('"+date+"',"+messj+","+elecj+","+bc+")");
                    }
                    else if(cred>0){
                   x= stmtj.executeUpdate("Insert into "+rollj+"ldr(month,messcharge,eleccharge,boardingcharges,creditbal) values ('"+date+"',"+messj+","+elecj+","+bc+","+cred+")");
                    }      
                    else{
                        
                     x=   stmtj.executeUpdate("Insert into "+rollj+"ldr(month,messcharge,eleccharge,boardingcharges,duebal) values ('"+date+"',"+messj+","+elecj+","+bc+","+cred*(-1)+")");
                    }               
                }
        } 
        else{
            
          
                  
         x=  stmtj.executeUpdate("Insert into "+rollj+"ldr(month,messcharge,eleccharge,boardingcharges,duebal) values ('"+date+"',"+messj+","+elecj+","+bc+","+bc+")");
            
           
        
        }
        }
        if(x > 0){
        out.println("<html><body><script>if(!alert('sudmitted successfully!'))document.location='index.html'</script></body></html>"); 
        }   
        else
        {
          out.println("<html><body><script>if(!alert('not sudmitted!'))document.location='monthlybills.html'</script></body></html>");   
        }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(submit.class.getName()).log(Level.SEVERE, null, ex);
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
