import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;


public class partida extends HttpServlet{
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
            //sql="SELECT * FROM usuarios ORDER BY idUsuario";
            //rs=st.executeQuery(sql);
            out=res.getWriter();

            res.setContentType("text/html; charset=UTF-8");
            out.println("<HTML>");

            out.println("<head>");
            out.println("<meta charset=\"UTF-8\">");
            out.println("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">");
            out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
            out.println("<title>CONECTA 4</title>");
            //out.println("<style> .forma{width:400px;background:#24303c;padding:30px;margin:auto;margin-top:40px;border-radius:4px;font-family:'calibri';color:white;margin-left:35%; </style>");
            //out.println("<style> .h3{font-size: 3em;text-align: center; } </style>");
            //out.println("<style> .control{width:100%;background: #24303c;color:white;} </style>");
            //out.println("<style> .forma .boton{text-align:center; width:100%;background:#1f53c5;border:none;color:white;padding:12px;margin:16px 0;font-size:16px; </style>");
            //out.println("<style> header{width:auto;height:150px;background:#003448;}</style>");
            //out.println("<style> header h11{text-align:left; color:white; line-height:30px; font-size:2em;color: #fff;}</style>");            

            //out.println("<style> header h12{text-align:right; color:white; line-height:150px; font-size:5em;color: #fff;}</style>");            
            //out.println("<style> body{background: url(fondo.jpg) #CCC no-repeat;background-size: cover;background-position:center center;background-attachment: fixed;}</style>");
            //out.println("<style> .t2 {text-align:left; line-height:150px; font-size:5em;color: #fff;}</style>");            


            out.println("</head>");


            out.println("<body>");

            out.println("nombre partida");
            out.println("codigo partida");
            out.println("primer jugador");
            out.println("segundo jugador");
            out.println("<br><br><br><br>");


            out.println("<form>\n" +
"                <table align=\"center\">\n" +
"                  <tr>\n" +
"                    <th id=\"primera\"  width=\"50\" scope=\"col\"><img name=\"0\" src=\"ficha_vacia.JPG\" width=\"70\" height=\"70\"></th>\n" +
"                    <th width=\"50\" scope=\"col\"><img name=\"1\" src=\"ficha_vacia.JPG\" width=\"70\" height=\"70\"></th>\n" +
"                    <th width=\"50\" scope=\"col\"><img name=\"2\" src=\"ficha_vacia.JPG\" width=\"70\" height=\"70\"></th>\n" +
"                    <th width=\"50\" scope=\"col\"><img name=\"3\" src=\"ficha_vacia.JPG\" width=\"70\" height=\"70\"></th>\n" +
"                    <th width=\"50\" scope=\"col\"><img name=\"4\" src=\"ficha_vacia.JPG\" width=\"70\" height=\"70\"></th>\n" +
"                    <th width=\"50\" scope=\"col\"><img name=\"5\" src=\"ficha_vacia.JPG\" width=\"70\" height=\"70\"></th>\n" +
"                    <th width=\"50\" scope=\"col\"><img name=\"6\" src=\"ficha_vacia.JPG\" width=\"70\" height=\"70\"></th>\n" +
"                  </tr>\n" +
"                  <tr>\n" +
"                    <td><img name=\"7\" src=\"ficha_vacia.JPG\" width=\"70\" height=\"70\"></td>\n" +
"                    <td><img name=\"8\" src=\"ficha_vacia.JPG\" width=\"70\" height=\"70\"></td>\n" +
"                    <td><img name=\"9\" src=\"ficha_vacia.JPG\" width=\"70\" height=\"70\"></td>\n" +
"                    <td><img name=\"10\" src=\"ficha_vacia.JPG\" width=\"70\" height=\"70\"></td>\n" +
"                    <td><img name=\"11\" src=\"ficha_vacia.JPG\" width=\"70\" height=\"70\"></td>\n" +
"                    <td><img name=\"12\" src=\"ficha_vacia.JPG\" width=\"70\" height=\"70\"></td>\n" +
"                    <td><img name=\"13\" src=\"ficha_vacia.JPG\" width=\"70\" height=\"70\"></td>\n" +
"                  </tr>\n" +
"                  <tr>\n" +
"                    <td><img name=\"14\" src=\"ficha_vacia.JPG\" width=\"70\" height=\"70\"></td>\n" +
"                    <td><img name=\"15\" src=\"ficha_vacia.JPG\" width=\"70\" height=\"70\"></td>\n" +
"                    <td><img name=\"16\" src=\"ficha_vacia.JPG\" width=\"70\" height=\"70\"></td>\n" +
"                    <td><img name=\"17\" src=\"ficha_vacia.JPG\" width=\"70\" height=\"70\"></td>\n" +
"                    <td><img name=\"18\" src=\"ficha_vacia.JPG\" width=\"70\" height=\"70\"></td>\n" +
"                    <td><img name=\"19\" src=\"ficha_vacia.JPG\" width=\"70\" height=\"70\"></td>\n" +
"                    <td><img name=\"20\" src=\"ficha_vacia.JPG\" width=\"70\" height=\"70\"></td>\n" +
"                  </tr>\n" +
"                  <tr>\n" +
"                    <td><img name=\"21\" src=\"ficha_vacia.JPG\" width=\"70\" height=\"70\"></td>\n" +
"                    <td><img name=\"22\" src=\"ficha_vacia.JPG\" width=\"70\" height=\"70\"></td>\n" +
"                    <td><img name=\"23\" src=\"ficha_vacia.JPG\" width=\"70\" height=\"70\"></td>\n" +
"                    <td><img name=\"24\" src=\"ficha_vacia.JPG\" width=\"70\" height=\"70\"></td>\n" +
"                    <td><img name=\"25\" src=\"ficha_vacia.JPG\" width=\"70\" height=\"70\"></td>\n" +
"                    <td><img name=\"26\" src=\"ficha_vacia.JPG\" width=\"70\" height=\"70\"></td>\n" +
"                    <td><img name=\"27\" src=\"ficha_vacia.JPG\" width=\"70\" height=\"70\"></td>\n" +
"                  </tr>\n" +
"                  <tr>\n" +
"                    <td><img name=\"28\" src=\"ficha_vacia.JPG\" width=\"70\" height=\"70\"></td>\n" +
"                    <td><img name=\"29\" src=\"ficha_vacia.JPG\" width=\"70\" height=\"70\"></td>\n" +
"                    <td><img name=\"30\" src=\"ficha_vacia.JPG\" width=\"70\" height=\"70\"></td>\n" +
"                    <td><img name=\"31\" src=\"ficha_vacia.JPG\" width=\"70\" height=\"70\"></td>\n" +
"                    <td><img name=\"32\" src=\"ficha_vacia.JPG\" width=\"70\" height=\"70\"></td>\n" +
"                    <td><img name=\"33\" src=\"ficha_vacia.JPG\" width=\"70\" height=\"70\"></td>\n" +
"                    <td><img name=\"34\" src=\"ficha_vacia.JPG\" width=\"70\" height=\"70\"></td>\n" +
"                  </tr>\n" +
"                  <tr>\n" +
"                    <td><img name=\"35\" src=\"ficha_vacia.JPG\" width=\"70\" height=\"70\"></td>\n" +
"                    <td><img name=\"36\" src=\"ficha_vacia.JPG\" width=\"70\" height=\"70\"></td>\n" +
"                    <td><img name=\"37\" src=\"ficha_vacia.JPG\" width=\"70\" height=\"70\"></td>\n" +
"                    <td><img name=\"38\" src=\"ficha_vacia.JPG\" width=\"70\" height=\"70\"></td>\n" +
"                    <td><img name=\"39\" src=\"ficha_vacia.JPG\" width=\"70\" height=\"70\"></td>\n" +
"                    <td><img name=\"40\" src=\"ficha_vacia.JPG\" width=\"70\" height=\"70\"></td>\n" +
"                    <td><img name=\"41\" src=\"ficha_vacia.JPG\" width=\"70\" height=\"70\"></td>\n" +
"                  </tr>\n" +
"                  </table>\n" +
"            </form>");   





            out.println("<script>  document.getElementById(\"primera\").onclick = function() {  fun()  };  function fun() {  document.getElementById(\"primera\").innerHTML = '<img src=\"ficha_roja.JPG\" width=\"70\" height=\"70\"/>';    }  </script>  ");

                
            out.println("</BODY></HTML>");
           // rs.close();
            con.close();
            out.close();
            st.close();
            }
        catch (Exception e){
            System.err.println(e);
        } 
    }
} 
