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
package net.daw.bean.implementation;

import com.google.gson.annotations.Expose;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import net.daw.bean.publicinterface.GenericBean;
import net.daw.dao.implementation.PerfilDao;
import net.daw.helper.statics.EncodingUtilHelper;

public class UsuarioBean implements GenericBean{

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
    @Expose(serialize = false)
    private Integer id_perfil = 0;
    @Expose(deserialize = false)
    private PerfilBean obj_perfil = null;

    public UsuarioBean() {
        this.id = 0;
    }

    public UsuarioBean(Integer id) {
        this.id = id;
    }
    
      /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
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
     * @return the apellido1
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * @param apellido1 the apellido1 to set
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
     * @return the id_perfil
     */
    public Integer getId_perfil() {
        return id_perfil;
    }

    /**
     * @param id_perfil the id_perfil to set
     */
    public void setId_perfil(Integer id_perfil) {
        this.id_perfil = id_perfil;
    }

    /**
     * @return the obj_perfil
     */
    public PerfilBean getObj_perfil() {
        return obj_perfil;
    }

    /**
     * @param obj_perfil the obj_perfil to set
     */
    public void setObj_perfil(PerfilBean obj_perfil) {
        this.obj_perfil = obj_perfil;
    }

    public String toJson(Boolean expand) {
        String strJson = "{";
        strJson += "id:" + getId() + ",";
        strJson += "nombre:" + nombre + ",";
        strJson += "apellido:" + apellido + ",";
        strJson += "email:" + email + ",";
        strJson += "password:" + getPassword() + ",";
        if (expand) {
            strJson += "obj_perfil:" + obj_perfil.toJson(false) + ",";
        } else {
            strJson += "id_perfil:" + id_perfil + ",";
        }
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
        strColumns += "id_perfil";

        return strColumns;
    }

    @Override
    public String getValues() {
        String strColumns = "";
        strColumns += getId() + ",";
        strColumns += '"'+nombre +'"'+ ",";
        strColumns += '"'+apellido +'"'+ ",";
        strColumns += '"'+email + '"'+",";
        strColumns += '"'+password + '"'+",";
        strColumns += id_perfil;

        return strColumns;
    }

    @Override
    public String toPairs() {
        String strPairs = "";
        strPairs += "id=" + getId() + ",";
        strPairs += "nombre=" + EncodingUtilHelper.quotate(nombre) + ",";
        strPairs += "password=" + EncodingUtilHelper.quotate(getPassword()) + ",";
        strPairs += "apellido=" + EncodingUtilHelper.quotate(apellido) + ",";
        strPairs += "email=" + EncodingUtilHelper.quotate(email) + ",";
        strPairs += "id_perfil=" + id_perfil;

        return strPairs;
    }
    
    
    @Override
    public UsuarioBean fill(ResultSet oResultSet, Connection pooledConnection, Integer expand) throws SQLException, Exception {
        this.setId(oResultSet.getInt("id"));
        this.setNombre(oResultSet.getString("nombre"));
        this.setPassword(oResultSet.getString("password"));
        this.setApellido(oResultSet.getString("apellido"));
        this.setEmail(oResultSet.getString("email"));
        if (expand > 0) {
            PerfilBean oPerfilBean = new PerfilBean();
            PerfilDao oPerfilDao = new PerfilDao(pooledConnection);
            oPerfilBean.setId(oResultSet.getInt("id_perfil"));
            oPerfilBean = oPerfilDao.get(oPerfilBean, expand - 1);
            this.setObj_perfil(oPerfilBean);
        } else {
            this.setId_perfil(oResultSet.getInt("id_perfil"));
        }
        return this;

    }

  
}
