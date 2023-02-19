import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;


public class partidasJugadas extends HttpServlet{
    public void doGet(HttpServletRequest req,HttpServletResponse res){
        Connection con;
        Statement st, st2;
        String sql, idUsuario, Nick, pass, sql2;
        ResultSet rs, rs2;
        PrintWriter out;
        HttpSession session;

        try{
            Nick=req.getParameter("Nick");
            pass=req.getParameter("Password");
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost/conecta4game","root","");
            st=con.createStatement();
            st2=con.createStatement();
            
            sql2="SELECT idUsuario, Nick, Password FROM usuarios WHERE Nick = '" + Nick + "' AND Password = '" + pass + "'";
            rs2=st2.executeQuery(sql2);
            
            out=res.getWriter();
            
            
            res.setContentType("text/html; charset=UTF-8");
            out.println("<HTML>");
            out.println("<head>");
            out.println("<meta charset=\"UTF-8\">");
            out.println("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">");
            out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
            out.println("<title>CONECTA 4</title>");
            out.println("<style> .forma{width:400px;background:#24303c;padding:30px;margin:auto;margin-top:50px;border-radius:4px;font-family:'calibri';color:white; </style>");
            out.println("<style> .h3{font-size: 3em;text-align: center; } </style>");
            out.println("<style> .forma .boton{width:100%;background:#1f53c5;border:none;color:white;padding:12px;margin:16px 0;font-size:16px; </style>");

            out.println("</head>");


        
            out.println("<BODY bgcolor=\"#6495ED\">");
            


            if(rs2.next()){
                idUsuario = rs2.getString(1);
                session = req.getSession(true);
                session.setAttribute("idUsuario", idUsuario);
                
                sql="SELECT partidas.idPartida,partidas.Nombre FROM partidas INNER JOIN detallespartida ON partidas.idPartida=detallespartida.idPartida WHERE detallespartida.idUsuario= "+idUsuario+" AND detallespartida.terminada=0";
                rs=st.executeQuery(sql);


                out.println("<section class=\"forma\">");
                out.println("<h3>Continuar partida en juego</h3>");
                out.println("<SELECT NAME= idPartida>");
                out.println("</section>");

                while(rs.next()){
                
                    out.println("<OPTION VALUE= "+rs.getString(1)+">"+rs.getString(2));
                }
                out.println("</SELECT><BR>");
                out.println("<FORM ACTION=continuarPartida METHOD=GET>");
                out.println("<INPUT class=\"boton\" TYPE=\"SUBMIT\" VALUE=\"Reanudar Partida\">");
                out.println("</FORM><BR>");
                out.println("---------------------------------------------------------------------------------");

                out.println("<FORM ACTION=unirsePartida METHOD=GET>");
                out.println("<h3>Introduzca codigo de la partida a la que desea unirse</h3>");
                out.println("<INPUT TYPE=\"TEXT\" NAME=\"CodigoPartida\">");
                out.println("<INPUT class=\"boton\" TYPE=\"SUBMIT\" VALUE=\"Unirse a Partida\">");
                out.println("</FORM><BR>");
                out.println("---------------------------------------------------------------------------------");

                out.println("<FORM ACTION=crearPartida METHOD=GET>");
                out.println("<h3>Introduzca nombre de partida nueva</h3>");
                out.println("<INPUT TYPE=\"TEXT\" NAME=\"Nombre\">");
                out.println("<INPUT class=\"boton\" TYPE=\"SUBMIT\" VALUE=\"Crear Partida\">");
                out.println("</FORM>");
                rs.close();
                st.close();
            } else {
                out.println("<section class=\"forma\">");
                out.println("<h3>USUARIO O CONTRASEÑA INCORRECTOS</h3>");
                out.println("<FORM ACTION=inicio METHOD=GET>");
                out.println("<INPUT class=\"boton\" TYPE=\"SUBMIT\" VALUE=\"Volver a Inicio\">");
                out.println("</FORM>");
                out.println("</section>");
                out.println("<br><br>");
                out.println("<center>");
                out.println("<img src=\"golpeado.gif\" width = \"450\" height = \"200\">");
                out.println("</center>");
                }
            out.println("</BODY></HTML>");
                
            rs2.close();
            con.close();
            out.close();
            st2.close();
            }
        catch (Exception e){
            System.err.println(e);
        } 
    }
} 