import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

//Esta sin hacer
public class empezarPartida extends HttpServlet{
    public void doGet(HttpServletRequest req,HttpServletResponse res){
        Connection con;
        Statement st;
        String sql,idUsuario1,idPartida;
        ResultSet rs;
        PrintWriter out;
        try{
            idUsuario1=req.getParameter("idUsuario");
            idPartida=req.getParameter("idUsuario");
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost/conecta4game","root","");
            st=con.createStatement();
            sql="SELECT idUsuario,Nick FROM usuarios WHERE idUsuario<>idUsuario1";
            rs=st.executeQuery(sql);
            out=res.getWriter();
            
            res.setContentType("text/html");
            out.println("<HTML><BODY>");
            out.println("<FORM ACTION=crearPartida METHOD=GET>");
            out.println("<INPUT TYPE=\"TEXT\" NAME=\"BuscarJugador\"><BR>");
            out.println("<INPUT TYPE=\"SUBMIT\" VALUE=CrearPartida>");
            out.println("<BR>");         
            out.println("</FORM></BODY></HTML>");
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
