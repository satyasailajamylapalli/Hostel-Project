/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
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
public class studentldr extends HttpServlet {

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

            
            String student_no=request.getParameter("rollno");
          String[][] sed ;
            sed = new String[8][8];
            Class.forName("com.mysql.jdbc.Driver");
           Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/hostel","root", "root");
           
           Statement stmt=con.createStatement();
           Statement stmt1=con.createStatement();
           Statement stmt2=con.createStatement();
            Statement stmt3=con.createStatement();
            
           ResultSet rs3=stmt3.executeQuery("select count(rollno) as count from student where rollno='"+student_no+"'");
           int count;
           if(rs3.next())
           {
              count= Integer.parseInt(rs3.getString("count"));
                
           
           
           if(count == 0)
           {
             
                   out.println("<html><body><script>if(!alert('ERROR:  Roll number not regestered!'))document.location='details.html';</script></body></html>");
                 
           }
             else
           {
            ResultSet rs=stmt.executeQuery("select * from "+student_no+"ldr");
            ResultSet rs1=stmt1.executeQuery("Select * from "+student_no+"rr");
            
            ResultSet rs2=stmt2.executeQuery("SELECT rollno, sname, course, scholarship, doj,cmrno,cautionmoney FROM student WHERE rollno='"+student_no+"'");
             
            
        
            out.println("<html>"
                    + "<head>"
                 +"   <link rel='stylesheet' href='css/w3.css' />"
                    + "<script type='text/javascript'>" +
"            function redirect()" +
"            {" +
"                window.location.href='stdldr.html';" +
"            }"
                    +"</script>" +
"    </head>"
                    + "<body bgcolor='#E9EE80'>");
            while(rs2.next()){
            String rollno=rs2.getString("rollno");
             
           String sname=rs2.getString("sname");
           String course=rs2.getString("course");
           String scholar=rs2.getString("scholarship");
          String doj=rs2.getString("doj");
           String cmrno=rs2.getString("cmrno");
          String cm=rs2.getString("cautionmoney");
            
            
            out.println("<table height='40%' width='100%'><caption><b><font size='5'>UNIVERSITY COLLEGE OF ENGINEERING HOSTELS,J.N.T.U.K. KAKINADA-3<br><hr></font></B></CAPTION>"
                    + "<td width='35%'>C.M.P.R No: <b>"+cmrno+"&nbsp;&nbsp;&nbsp;&nbsp;</b> &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; Caution Money Rs. <b>"+cm+"</b> <br><br> Name : <b>"+sname+"</b><br><br>Roll No : <b>"+rollno+"</b><br><br> Branch : <b>"+course+"</b><br><br>Name of the Scholarship : <b>"+scholar+"</b><br><br>Date of Admission : <b>"+doj+"</b><br><br>Date of Vacation:</td>"
                    + "<td width='10%'><br><br><br>Establishment<br><br>Room Rent ...<br><br>W.C. ...<br><br>Tin & Audit ...<br><hr>Challan No<br><br>Date</td>"
                    + "<td width='55%'><center><font size='3'><b>1yr&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;2yr&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;3yr&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;4yr&emsp;</b></font></center>"
                    + "<table border='1' width='100%' height='100%'><tr height='10%'><th width='12%'>I</th><th width='13%'>II</th><th width='12%'>I</th><th width='13%'>II</th><th width='12%'>I</th><th width='13%'>II</th><th width='12%'>I</th><th width='13&'>II</th></tr>");
            }
        
            for(int i=0;i<=7;i++){
               
              if(rs1.next()){
               
                  sed[i][0]=rs1.getString("establish");
                  sed[i][1]=rs1.getString("roomrent");
                  sed[i][2]=rs1.getString("wc");
                  sed[i][3]=rs1.getString("tin_audit");
                  sed[i][4]=rs1.getString("challan");
                  sed[i][5]=rs1.getString("date");
                 
                }
              else{
                 sed[i][0]="";
                 sed[i][1]="";
                 sed[i][2]="";
                 sed[i][3]="";
                 sed[i][4]="";
                 sed[i][5]="";
                   
              }
                
           }
                
            out.println("<tr><td>"+sed[0][0]+"</td><td>"+sed[1][0]+"</td><td>"+sed[2][0]+"</td><td>"+sed[3][0]+"</td><td>"+sed[4][0]+"</td><td>"+sed[5][0]+"</td><td>"+sed[6][0]+"</td><td>"+sed[6][0]+"</td></tr>");
            out.println("<tr><td>"+sed[0][1]+"</td><td>"+sed[1][1]+"</td><td>"+sed[2][1]+"</td><td>"+sed[3][1]+"</td><td>"+sed[4][1]+"</td><td>"+sed[5][1]+"</td><td>"+sed[6][1]+"</td><td>"+sed[6][1]+"</td></tr>");
            out.println("<tr><td>"+sed[0][2]+"</td><td>"+sed[1][2]+"</td><td>"+sed[2][2]+"</td><td>"+sed[3][2]+"</td><td>"+sed[4][2]+"</td><td>"+sed[5][2]+"</td><td>"+sed[6][2]+"</td><td>"+sed[6][2]+"</td></tr>");
            out.println("<tr><td>"+sed[0][3]+"</td><td>"+sed[1][3]+"</td><td>"+sed[2][3]+"</td><td>"+sed[3][3]+"</td><td>"+sed[4][3]+"</td><td>"+sed[5][3]+"</td><td>"+sed[6][3]+"</td><td>"+sed[6][3]+"</td></tr>");
            out.println("<tr><td>"+sed[0][4]+"</td><td>"+sed[1][4]+"</td><td>"+sed[2][4]+"</td><td>"+sed[3][4]+"</td><td>"+sed[4][4]+"</td><td>"+sed[5][4]+"</td><td>"+sed[6][4]+"</td><td>"+sed[6][4]+"</td></tr>");
            out.println("<tr><td>"+sed[0][5]+"</td><td>"+sed[1][5]+"</td><td>"+sed[2][5]+"</td><td>"+sed[3][5]+"</td><td>"+sed[4][5]+"</td><td>"+sed[5][5]+"</td><td>"+sed[6][5]+"</td><td>"+sed[6][5]+"</td></tr>");
          
            out.println("</table></td></table><br>");
            
            out.println("<table border='2' width='100%' height='100%'>");
            out.println("<tr height='8%'><th>Date</th><th>Challan N0</th><th>Month</th><th>Mess Charge</th><th>Elec Charge</th><th>Deposit</th><th>Boarding Charges</th><th>Credit Balance</th><th>Due Balance</th><th>Remarks</th>");
            while(rs.next()){ 
                String d=rs.getString("date");
                if(d==null){
                   d="";
               }
                String ch=rs.getString("challan");
                if(ch==null){
                   ch="";
               }
                String mn=rs.getString("month");
               if(mn==null){
                   mn="";
               }
               String mc=rs.getString("messcharge");
                if(mc==null){
                   mc="";
               }
                String ec=rs.getString("eleccharge");
                if(ec==null){
                   ec="";
               }
                String dp =rs.getString("deposit");
                if(dp==null){
                   dp="";
                }
                String bc =rs.getString("boardingCharges");
                if(bc==null){
                   bc="";
               }
                String cb =rs.getString("creditbal");
                if(cb==null){
                   cb="";
               }
                String due =rs.getString("duebal");
                if(due==null){
                   due="";
               }
                String remarks=rs.getString("remarks");
                if(remarks==null){
                   remarks="";
               }
               out.println("<tr height='5%'><td>"+d+"</td><td>"+ch+"</td><td>"+mn+"</td><td>"+mc+"</td><td>"+ec+"</td><td>"+dp+"</td><td>"+bc+"</td><td>"+cb+"</td><td>"+due+"</td><td>"+remarks+"</td></tr>");
                
            }
           
            out.println("<tr height='5%'><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>");
               out.println("<tr height='5%'><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>");
                  out.println("<tr height='5%'><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>");
                     out.println("<tr height='5%'><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>");
                        out.println("<tr height='5%'><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>");
            out.println("</table>");
            out.println("<center><button type='button' style='width:150px' class='w3-xxlarger w3-button w3-round w3-red' value='back' onClick='redirect()' >back</button></center><br><br>");
           
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
        
        
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(student_details.class.getName()).log(Level.SEVERE, null, ex);
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
