/*
 * Copyright (c) 2015 by Rafael Angel Aznar Aparici (rafaaznar at gmail dot com)
 * 
 * openAUSIAS: The stunning micro-library that helps you to develop easily 
 *             AJAX web applications by using Java and jQuery
 * openAUSIAS is distributed under the MIT License (MIT)
 * Sources at https://github.com/rafaelaznar/openAUSIAS
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package net.daw.dao.implementation;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import java.util.Random;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import net.daw.bean.implementation.UsuarioBean;
import static net.daw.dao.implementation.ServletEnviarMailConfirmacion.aleatoria;
import net.daw.dao.publicinterface.TableDaoInterface;
import net.daw.dao.publicinterface.ViewDaoInterface;
import net.daw.data.implementation.MysqlDataSpImpl;
import net.daw.helper.statics.AppConfigurationHelper;
import net.daw.helper.statics.ExceptionBooster;
import net.daw.helper.statics.FilterBeanHelper;
import net.daw.helper.statics.SqlBuilder;

public class UsuarioDao {

    private String strTable = "usuario";
    private String strSQL = "select * from usuario where 1=1 ";
    private MysqlDataSpImpl oMysql = null;
    private Connection oConnection = null;

    public UsuarioDao(Connection oPooledConnection) throws Exception {
        try {
            oConnection = oPooledConnection;
            oMysql = new MysqlDataSpImpl(oConnection);
        } catch (Exception ex) {
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":constructor ERROR: " + ex.getMessage()));
        }
    }

    public int getPages(int intRegsPerPag, ArrayList<FilterBeanHelper> hmFilter) throws Exception {
        strSQL += SqlBuilder.buildSqlWhere(hmFilter);
        int pages = 0;
        try {
            pages = oMysql.getPages(strSQL, intRegsPerPag);
        } catch (Exception ex) {
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":getPages ERROR: " + ex.getMessage()));
        }
        return pages;
    }
    
   public int getPagesusuarionoduplicado(int id_usuario,int intRegsPerPag, ArrayList<FilterBeanHelper> hmFilter) throws Exception {
        strSQL ="SELECT usuario.* FROM usuario WHERE usuario.id  NOT IN (SELECT amistad.id_usuario2 FROM amistad where amistad.id_usuario="+ id_usuario +") and usuario.id !="+id_usuario;
        strSQL += SqlBuilder.buildSqlWhere(hmFilter);
        int pages = 0;
        int num = 0 ;
        
        ResultSet oResulset = oMysql.getAllSql("SELECT COUNT(*) as id FROM usuario WHERE usuario.id  NOT IN (SELECT amistad.id_usuario2 FROM amistad where amistad.id_usuario="+ id_usuario +") and usuario.id !="+id_usuario);
        
        if(oResulset != null){
            while(oResulset.next()){
                num = oResulset.getInt("id");
            }
        }
        try {
            pages = oMysql.getPagesusuarionoduplicado(strSQL , intRegsPerPag, num);
        } catch (Exception ex) {
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":getPages ERROR: " + ex.getMessage()));
        }
        return pages;
    }
    
    public String getCadenaAlfanumAleatoria(int longitud) {
        String cadenaAleatoria = "";
        long milis = new java.util.GregorianCalendar().getTimeInMillis();
        Random r = new Random(milis);
        int i = 0;
        while (i < longitud) {
            char c = (char) r.nextInt(255);
            //System.out.println("char:"+c);
            if ((c >= '0' && c <= 9) || (c >= 'A' && c <= 'Z')) {
                cadenaAleatoria += c;
                i++;
            }
        }
        return cadenaAleatoria;
    }
    /*
       public String pruebaservicio() throws Exception {
     //   System.out.println("ENTRA2");
        String nombre = "julio";//request.getParameter("nombre");
        String usuario = "user";//request.getParameter("usuario");
        String contra = "contra";//request.getParameter("contra");
        String email = "mail";//request.getParameter("mail");
        aleatoria = getCadenaAlfanumAleatoria(8);
        System.out.println("ESTO SE GUARDA:\n"
                + "INSERT INTO TB_USUARIO(NOM,USU,CON,MAIL,ALE) VALUES(" + nombre + "," + usuario + "," + contra + "," + email + "," + aleatoria + ")");

        try {
            // Propiedades de la conexión
            Properties props = new Properties();
            props.setProperty("mail.smtp.host", "smtp.gmail.com");
            props.setProperty("mail.smtp.starttls.enable", "true");
            props.setProperty("mail.smtp.port", "587");
            props.setProperty("mail.smtp.user", "julio6164@gmail.com");
            props.setProperty("mail.smtp.auth", "true");

            // Preparamos la sesion
            Session session = Session.getDefaultInstance(props);

            // Construimos el mensaje
            MimeMessage message = new MimeMessage(session);
            //la persona k tiene k verificar
            message.setFrom(new InternetAddress("julio6164@gmail.com"));
            message.addRecipient(
                    Message.RecipientType.TO,
                    new InternetAddress("julio6164@gmail.com"));
            message.addHeader("Disposition-Notification-To", "julio6164@gmail.com");
            message.setSubject("Correo de verificacion, porfavor no responder");
            message.setText(
                    "Este es un correo de verificacion \n"
                    + "Gracias por escribirse a B2MINING.COM \n"
                    + "Porfavor haga click en el siguiente enlace\n"
                    + "para seguir con la verificacion de sus datos \n"
                    + "  Enlace  ",
                    "ISO-8859-1",
                    "html");

            // Lo enviamos.
            Transport t = session.getTransport("smtp");
            t.connect("julio6164@gmail.com", "CONTRASENA");
            t.sendMessage(message, message.getAllRecipients());

            // Cierre.
            t.close();
        } catch (Exception ex) {
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":getCount ERROR: " + ex.getMessage()));
        }
        // response.sendRedirect("respuesta.jsp");
       
        return "enviado";
    }
    */
    public int getCount(ArrayList<FilterBeanHelper> hmFilter) throws Exception {
        strSQL += SqlBuilder.buildSqlWhere(hmFilter);
        int pages = 0;
        
        
        try {
            pages = oMysql.getCount(strSQL);
        } catch (Exception ex) {
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":getCount ERROR: " + ex.getMessage()));
        }
        return pages;
    }
    
     public int getCountusuarionoduplicado(int id_usuario, ArrayList<FilterBeanHelper> hmFilter) throws Exception {
        strSQL ="SELECT usuario.* FROM usuario WHERE usuario.id  NOT IN (SELECT amistad.id_usuario2 FROM amistad where amistad.id_usuario="+ id_usuario +") and usuario.id !="+id_usuario;
        strSQL += SqlBuilder.buildSqlWhere(hmFilter);
        int pages = 0;
        int num = 0;
        ResultSet oResulset = oMysql.getAllSql("SELECT COUNT(*) as id FROM usuario WHERE usuario.id  NOT IN (SELECT amistad.id_usuario2 FROM amistad where amistad.id_usuario="+ id_usuario +") and usuario.id !="+id_usuario);
        
        if(oResulset != null){
            while(oResulset.next()){
                num = oResulset.getInt("id");
            }
        }
        
        try {
            pages = num;
        } catch (Exception ex) {
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":getCount ERROR: " + ex.getMessage()));
        }
        return pages;
    }
     
    public ArrayList<UsuarioBean> getPage(int intRegsPerPag, int intPage, ArrayList<FilterBeanHelper> hmFilter, HashMap<String, String> hmOrder, Integer expand) throws Exception {
        strSQL += SqlBuilder.buildSqlWhere(hmFilter);
        strSQL += SqlBuilder.buildSqlOrder(hmOrder);
        strSQL += SqlBuilder.buildSqlLimit(oMysql.getCount(strSQL), intRegsPerPag, intPage);
        ArrayList<UsuarioBean> arrUsuario = new ArrayList<>();
        try {
            ResultSet oResultSet = oMysql.getAllSql(strSQL);
            if (oResultSet != null) {
                while (oResultSet.next()) {
                    UsuarioBean oUsuarioBean = new UsuarioBean();
                    arrUsuario.add(oUsuarioBean.fill(oResultSet, oConnection, expand));
                }
            }
        } catch (Exception ex) {
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":getPage ERROR: " + ex.getMessage()));
        }
        return arrUsuario;
    }
    
      public ArrayList<UsuarioBean> getPageusuarionoduplicado(int id_usuario,int intRegsPerPag, int intPage, ArrayList<FilterBeanHelper> hmFilter, HashMap<String, String> hmOrder, Integer expand) throws Exception {
        strSQL ="SELECT usuario.* FROM usuario WHERE usuario.id  NOT IN (SELECT amistad.id_usuario2 FROM amistad where amistad.id_usuario="+ id_usuario +") and usuario.id !="+id_usuario;
        strSQL += SqlBuilder.buildSqlWhere(hmFilter);
        strSQL += SqlBuilder.buildSqlOrder(hmOrder);
        
        int num = 0;
        
        ResultSet oResulset = oMysql.getAllSql("SELECT COUNT(*) as id FROM usuario WHERE usuario.id  NOT IN (SELECT amistad.id_usuario2 FROM amistad where amistad.id_usuario="+ id_usuario +") and usuario.id !="+id_usuario);
        
        if(oResulset != null){
            while(oResulset.next()){
                num = oResulset.getInt("id");
            }
        }
        
        
        strSQL += SqlBuilder.buildSqlLimit(num, intRegsPerPag, intPage);
        ArrayList<UsuarioBean> arrUsuario = new ArrayList<>();
        try {
            ResultSet oResultSet = oMysql.getAllSql(strSQL);
            if (oResultSet != null) {
                while (oResultSet.next()) {
                    UsuarioBean oUsuarioBean = new UsuarioBean();
                    arrUsuario.add(oUsuarioBean.fill(oResultSet, oConnection, expand));
                }
            }
        } catch (Exception ex) {
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":getPage ERROR: " + ex.getMessage()));
        }
        return arrUsuario;
    }
    
      
    public ArrayList<UsuarioBean> getAll(ArrayList<FilterBeanHelper> alFilter, HashMap<String, String> hmOrder, Integer expand, int id_usuario) throws Exception {
        strSQL ="SELECT usuario.* FROM usuario WHERE usuario.id  NOT IN (SELECT amistad.id_usuario2 FROM amistad where amistad.id_usuario="+ id_usuario +") and usuario.id !="+id_usuario;
        strSQL += SqlBuilder.buildSqlOrder(hmOrder);
        ArrayList<UsuarioBean> arrUsuario = new ArrayList<>();
        try {
            ResultSet oResultSet = oMysql.getAllSql(strSQL);
            if (oResultSet != null) {
                while (oResultSet.next()) {
                    UsuarioBean oUsuarioBean = new UsuarioBean();
                    arrUsuario.add(oUsuarioBean.fill(oResultSet, oConnection, expand));
                }
            }
        } catch (Exception ex) {
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":getPage ERROR: " + ex.getMessage()));
        }
        return arrUsuario;
    }

    public UsuarioBean get(UsuarioBean oUsuarioBean, Integer expand) throws Exception {
        if (oUsuarioBean.getId() > 0) {
            try {
                ResultSet oResultSet = oMysql.getAllSql(strSQL + " And id= " + oUsuarioBean.getId() + " ");
                if (oResultSet != null) {
                    while (oResultSet.next()) {
                        oUsuarioBean = oUsuarioBean.fill(oResultSet, oConnection, expand);
                    }
                }
            } catch (Exception ex) {
                ExceptionBooster.boost(new Exception(this.getClass().getName() + ":get ERROR: " + ex.getMessage()));
            }
        } else {
            oUsuarioBean.setId(0);
        }
        return oUsuarioBean;
    }

    public Integer set(UsuarioBean oUsuarioBean) throws Exception {
          Integer iResult = null;
        try {
            if (oUsuarioBean.getId() == 0) {
                strSQL = "INSERT INTO " + strTable + " ";
                strSQL += "(" + oUsuarioBean.getColumns() + ")";
                strSQL += "VALUES(" + oUsuarioBean.getValues() + ")";
                iResult = oMysql.executeInsertSQL(strSQL);
            } else {
                strSQL = "UPDATE " + strTable + " ";
                strSQL += " SET " + oUsuarioBean.toPairs();
                strSQL += " WHERE id=" + oUsuarioBean.getId();
                iResult = oMysql.executeUpdateSQL(strSQL);
            }

        } catch (Exception ex) {
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":set ERROR: " + ex.getMessage()));
        }
        return iResult;
    }

    public Integer remove(Integer id) throws Exception {
        int result = 0;
        try {
            result = oMysql.removeOne(id, strTable);
        } catch (Exception ex) {
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":remove ERROR: " + ex.getMessage()));
        }
        return result;
    }

public UsuarioBean getFromLogin(UsuarioBean oUsuario) throws Exception {
        try {
            String strId = oMysql.getId("usuario", "email", oUsuario.getEmail());
            if (strId == null) {
                oUsuario.setId(0);
            } else {
                Integer intId = Integer.parseInt(strId);
                oUsuario.setId(intId);
                String pass = oUsuario.getPassword();
                oUsuario.setPassword(oMysql.getOne(strSQL, "password", oUsuario.getId()));
                if (!pass.equals(oUsuario.getPassword())) {
                    oUsuario.setId(0);
                }
                oUsuario = this.get(oUsuario, AppConfigurationHelper.getJsonDepth());
            }
            return oUsuario;
        } catch (Exception e) {
            throw new Exception("UsuarioDao.getFromLogin: Error: " + e.getMessage());
        }
    }

}


/*SELECT usuario.*
FROM usuario
WHERE usuario.id  NOT IN (SELECT amistad.id_usuario2 FROM amistad where amistad.id_usuario=1) and usuario.id !=1*/