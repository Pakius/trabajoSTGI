import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;


public class crearUsuario extends HttpServlet{
    public void doGet(HttpServletRequest req,HttpServletResponse res){
        Connection con;
        Statement st;
        String sqlr,Nombre,Nick,Password,email;
        PrintWriter out;
        PreparedStatement pst = null;

        try{
            Nombre=req.getParameter("Nombre");
            Nick=req.getParameter("Nick");
            Password=req.getParameter("Password");
            email=req.getParameter("email");
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost/conecta4game","root","");
            st=con.createStatement();

            
            sqlr=" INSERT INTO usuarios ( Nombre, Nick, Password, email) values( ?, ?, ?, ?)";
            pst=con.prepareStatement(sqlr);
            pst.setString(1, Nombre);
            pst.setString(2, Nick);
            pst.setString(3, Password);
            pst.setString(4, email);
            pst.executeUpdate();
            
            out=res.getWriter();
            res.setContentType("text/html; charset=UTF-8");
            out.println("<HTML>");
            out.println("<head>");
            out.println("<meta charset=\"UTF-8\">");
            out.println("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">");
            out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
            out.println("<title>REGISTRO</title>");
            out.println("<style> .forma{width:400px;background:#24303c;padding:30px;margin:auto;margin-top:100px;border-radius:4px;font-family:'calibri';color:white; </style>");
            out.println("<style> .h3{font-size: 3em;text-align: center; } </style>");
            //out.println("<style> .control{width:100%;background: #24303c;color:white;} </style>");
            out.println("<style> .forma .boton{width:100%;background:#1f53c5;border:none;color:white;padding:12px;margin:16px 0;font-size:16px; </style>");
            out.println("</head>");
            
            out.println("<BODY bgcolor=\"#6495ED\">");
            out.println("<section class=\"forma\">");
            out.println("<h3>USUARIO CREADO CORRECTAMENTE</h3>");
            out.println("<FORM ACTION =inicio METHOD=GET>");
            out.println("<INPUT class=\"boton\" TYPE=\"SUBMIT\" VALUE=Enviar>");
            out.println("</FORM>");
            out.println("</section>");
            out.println("<br><br>");
            out.println("<center>");
            out.println("<img src=\"aplausos.gif\">");
            out.println("</center>");
            out.println("</BODY></HTML>");
            pst.close();
            con.close();
            out.close();
            st.close();
            }
        catch (Exception e){
            System.err.println(e);
        } 
    }
} 

