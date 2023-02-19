import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;


public class inicio extends HttpServlet{
    public void doGet(HttpServletRequest req,HttpServletResponse res){
        Connection con;
        Statement st;
        String sql;
        ResultSet rs;
        PrintWriter out;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost/conecta4game","root","");
            st=con.createStatement();
            sql="SELECT * FROM usuarios ORDER BY idUsuario";
            rs=st.executeQuery(sql);
            out=res.getWriter();

            res.setContentType("text/html; charset=UTF-8");
            out.println("<HTML>");

            out.println("<head>");
            out.println("<meta charset=\"UTF-8\">");
            out.println("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">");
            out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
            out.println("<title>CONECTA 4</title>");
            out.println("<style> .forma{width:400px;background:#24303c;padding:30px;margin:auto;margin-top:40px;border-radius:4px;font-family:'calibri';color:white;margin-left:35%; </style>");
            out.println("<style> .h3{font-size: 3em;text-align: center; } </style>");
            out.println("<style> .control{width:100%;background: #24303c;color:white;} </style>");
            out.println("<style> .forma .boton{text-align:center; width:100%;background:#1f53c5;border:none;color:white;padding:12px;margin:16px 0;font-size:16px; </style>");
            out.println("<style> header{width:auto;height:150px;background:#003448;}</style>");
            out.println("<style> header h1{text-align:center; color:white; line-height:150px; font-size:5em;color: #fff;}</style>");            
            out.println("<style> body{background: url(fondo.jpg) #CCC no-repeat;background-size: cover;background-position:center center;background-attachment: fixed;}</style>");
            out.println("</head>");


            out.println("<body>");

            
            out.println("<header>");
                out.println("<h1>CONECTA 4</h1>");
            out.println("</header>");
            
            out.println("<section class=\"forma\">");
            
            out.println("<h3>Inicio sesion</h3>");

            out.println("<FORM ACTION=partidasJugadas METHOD=GET>");
            out.println("Nick: ");
            out.println("<INPUT class=\"control\" TYPE=\"TEXT\" NAME=\"Nick\" placeholder=\"Acronimo\"><BR>");
            out.println("Contraseña: ");
            out.println("<INPUT class=\"control\" TYPE=\"PASSWORD\" NAME=\"Password\" placeholder=\"Contraseña\"><BR>");
            out.println("<INPUT class=\"boton\" TYPE=\"SUBMIT\" VALUE=Enviar>");
            out.println("</FORM>");
            out.println("</section>");

            out.println("<section class=\"forma\">");
            out.println("<h3>Usuario nuevo</h3>");
            out.println("<FORM ACTION= registro METHOD=GET>");
            out.println("<INPUT  class=\"boton\" TYPE=\"SUBMIT\" VALUE=\"Crear usuario\">");
            out.println("</FORM>");
            out.println("</section>");

            out.println("</BODY></HTML>");
            rs.close();
            con.close();
            out.close();
            st.close();
            }
        catch (Exception e){
            System.err.println(e);
        } 
    }
} 
