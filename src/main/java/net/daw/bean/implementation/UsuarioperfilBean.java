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
package net.daw.bean.implementation;

import com.google.gson.annotations.Expose;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import net.daw.bean.publicinterface.GenericBean;
import net.daw.helper.statics.EncodingUtilHelper;

/**
 *
 * @author a020864288e
 */
public class UsuarioperfilBean implements GenericBean{
    
    @Expose
    private Integer id;
    @Expose
    private String nombre = "";
    @Expose
    private String apellido = "";
    @Expose
    private String email = "";    
    @Expose
    private String password = "";
    @Expose 
    private String direccion="";
    @Expose 
    private String estado_civil="";
    @Expose 
    private String ocupacion="";
    @Expose 
    private String estudio="";

    /**
     * @return the id_usuario
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id_usuario to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the apellido
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * @param apellido the apellido to set
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

  
    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * @return the estado_civil
     */
    public String getEstado_civil() {
        return estado_civil;
    }

    /**
     * @param estado_civil the estado_civil to set
     */
    public void setEstado_civil(String estado_civil) {
        this.estado_civil = estado_civil;
    }

    /**
     * @return the ocupacion
     */
    public String getOcupacion() {
        return ocupacion;
    }

    /**
     * @param ocupacion the ocupacion to set
     */
    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }

    /**
     * @return the estudio
     */
    public String getEstudio() {
        return estudio;
    }

    /**
     * @param estudio the estudio to set
     */
    public void setEstudio(String estudio) {
        this.estudio = estudio;
    }
    
       public String toJson(Boolean expand) {
        String strJson = "{";
        strJson += "id:" + id + ",";
        strJson += "nombre:" + nombre + ",";
        strJson += "apellido:" + apellido + ",";
        strJson += "email:" + email + ",";
        strJson += "password:" + getPassword() + ",";
        strJson += "direccion:" + direccion + ",";
        strJson += "esado_civil:" + estado_civil + ",";
        strJson += "ocupacion:" + ocupacion + ",";
        strJson += "estudio:" + estudio+ ",";
        strJson += "}";
        return strJson;
    }

    
    
     @Override
    public String getColumns() {
        
        String strColumns = "";
        strColumns += "id,";
        strColumns += "nombre,";
        strColumns += "apellido,";
        strColumns += "email,";
        strColumns += "password,";
        strColumns += "direccion";
        strColumns += "estado_civil";
        strColumns += "ocupacion";
        strColumns += "estudio";
        

        return strColumns;
    }

    @Override
    public String getValues() {
        
        String strColumns = "";
        strColumns += id + ",";
        strColumns += '"'+nombre +'"'+ ",";
        strColumns += '"'+apellido +'"'+ ",";
        strColumns += '"'+email + '"'+",";
        strColumns += '"'+password + '"'+",";
        strColumns += '"'+direccion + '"'+",";        
        strColumns += '"'+estado_civil + '"'+",";
        strColumns += '"'+ocupacion + '"'+",";
        strColumns += '"'+estudio + '"'+","; 

        return strColumns;
    }

    @Override
    public String toPairs() {
         String strPairs = "";
        strPairs += "id=" + id + ",";
        strPairs += "nombre=" + EncodingUtilHelper.quotate(nombre) + ",";
        strPairs += "password=" + EncodingUtilHelper.quotate(getPassword()) + ",";
        strPairs += "apellido=" + EncodingUtilHelper.quotate(apellido) + ",";
        strPairs += "email=" + EncodingUtilHelper.quotate(email) + ",";
        strPairs += "direccion=" + EncodingUtilHelper.quotate(direccion) + ",";
        strPairs += "estado_civil=" + EncodingUtilHelper.quotate(estado_civil) + ",";
        strPairs += "ocupacion=" + EncodingUtilHelper.quotate(ocupacion) + ",";
        strPairs += "estudio=" + EncodingUtilHelper.quotate(estudio) + ",";
        return strPairs;
    }

    @Override
    public GenericBean fill(ResultSet oResultSet, Connection pooledConnection, Integer expand) throws SQLException, Exception {
        this.setId(oResultSet.getInt("id"));
        this.setNombre(oResultSet.getString("nombre"));
        this.setPassword(oResultSet.getString("password"));
        this.setApellido(oResultSet.getString("apellido"));
        this.setEmail(oResultSet.getString("email"));        
        this.setDireccion(oResultSet.getString("direccion"));
        this.setEstado_civil(oResultSet.getString("estado_civil"));
        this.setOcupacion(oResultSet.getString("ocupacion"));
        this.setEstudio(oResultSet.getString("estudio"));
        
        return this; 
    }
}
