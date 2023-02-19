import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;


public class crearPartida extends HttpServlet{
    public void doGet(HttpServletRequest req,HttpServletResponse res){
        Connection con;
        String sqlr,idUsuario, Nombre, sqlr2, sql, idPartida;
        PrintWriter out;
        PreparedStatement pst = null;
        PreparedStatement pst2 = null;
        HttpSession session;
        ResultSet rs;
        Statement st;

        try{
            Nombre=req.getParameter("Nombre");
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost/conecta4game","root","");
            
            sqlr=" INSERT INTO partidas (Nombre) values(?)";
            pst=con.prepareStatement(sqlr);
            pst.setString(1, Nombre);
            pst.executeUpdate();
            pst.close();
            con.close();

            
            con=DriverManager.getConnection("jdbc:mysql://localhost/conecta4game","root","");
            st=con.createStatement();
            sql="SELECT idPartida FROM partidas ORDER BY idPartida DESC LIMIT 1";
            rs=st.executeQuery(sql);
            out=res.getWriter();
            rs.next();
            
            

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
            
            out.println("<BODY bgcolor=\"#6495ED\">");

            
            idPartida = rs.getString(1);

            session = req.getSession(false);
            idUsuario = (String)session.getAttribute("idUsuario");
            
           con.close();
           st.close();
           rs.close();

            con=DriverManager.getConnection("jdbc:mysql://localhost/conecta4game","root","");
            sqlr2=" INSERT INTO detallespartida (idPartida, idUsuario) values(?, ?)";
            pst2=con.prepareStatement(sqlr2);
            pst2.setString(1, idPartida);
            pst2.setString(2, idUsuario);
            pst2.executeUpdate();
            pst2.close();
            con.close();
            
            out.println("<section class=\"forma\">");
            out.println("<h3>¡¡¡YA PUEDES EMPEZAR TU PARTIDA!!!</h3>");
            out.println("<FORM ACTION =inicio METHOD=GET>");
            out.println("<INPUT class=\"boton\" TYPE=\"SUBMIT\" VALUE=Comenzar>");
            out.println("</FORM>");
            out.println("</section>");
            out.println("<br><br>");
            out.println("<center>");
            out.println("<img src=\"partidacreada.gif\">");
            out.println("</center>");

            out.println("</BODY></HTML>");

            out.close();

            }
        catch (Exception e){
            System.err.println(e);
        }
    }
}