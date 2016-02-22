/*
 * Copyright (c) 2015 by Rafael Angel Aznar Aparici (rafaaznar at gmail dot com)
 * 
 * openAUSIAS: The stunning micro-library that helps you to develop easily 
 *             AJAX web applications by using Java and jQuery
 * openAUSIAS is distributed under the MIT License (MIT)
 * Sources at https://github.com/rafaelaznar/
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
 * 
 */
package net.daw.dao.implementation;

import java.io.IOException;
import java.util.Properties;
import java.util.Random;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author juliomiguel
 */
public class ServletEnviarMailConfirmacion {

    private static final long serialVersionUID = 1L;
    public static String aleatoria = "";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletEnviarMailConfirmacion() {
        System.out.println("ENTRA");

        // TODO Auto-generated constructor stub
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

    protected void service() throws ServletException, IOException {
        System.out.println("ENTRA2");
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        // response.sendRedirect("respuesta.jsp");
       

    }

}
