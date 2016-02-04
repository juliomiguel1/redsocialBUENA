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
import java.util.Date;
import net.daw.bean.publicinterface.GenericBean;
import net.daw.dao.implementation.UsuarioDao;
import net.daw.helper.statics.EncodingUtilHelper;

/**
 *
 * @author a020864288e
 */
public class PublicacionesBean implements GenericBean {
    @Expose
    private Integer id;
    @Expose
    private String texto;
    @Expose
    private Date fecha = new Date();
    @Expose(serialize = false)
    private Integer id_usuario = 0;
    @Expose(deserialize = false)
    private UsuarioBean obj_usuario = null;

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
     * @return the texto
     */
    public String getTexto() {
        return texto;
    }

    /**
     * @param texto the texto to set
     */
    public void setTexto(String texto) {
        this.texto = texto;
    }

    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the id_usuario
     */
    public Integer getId_usuario() {
        return id_usuario;
    }

    /**
     * @param id_usuario the id_usuario to set
     */
    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }

    /**
     * @return the obj_usuario
     */
    public UsuarioBean getObj_usuario() {
        return obj_usuario;
    }

    /**
     * @param obj_usuario the obj_usuario to set
     */
    public void setObj_usuario(UsuarioBean obj_usuario) {
        this.obj_usuario = obj_usuario;
    }
    
    public String toJson(Boolean expand) {
        String strJson = "{";
        strJson += "id:" + id + ",";
        strJson += "texto:" + texto + ",";
        strJson += "fecha:" + fecha + ",";
        if (expand) {
            strJson += "obj_usuario:" + obj_usuario.toJson(false) + ",";
        } else {
            strJson += "id_usuario:" + id_usuario + ",";
        }
        strJson += "}";
        return strJson;
    }

    @Override
    public String getColumns() {
        String strColumns = "";
        strColumns += "id,";
        strColumns += "texto,";
        strColumns += "fecha,";
        strColumns += "id_usuario";
        
        return strColumns;
    }

    @Override
    public String getValues() {
        String strColumns = "";
        strColumns += id + ",";        
        strColumns += '"'+texto +'"'+ ",";        
        strColumns += EncodingUtilHelper.stringifyAndQuotate(fecha)+ ",";
        strColumns += id_usuario;
        return strColumns;
    }

    @Override
    public String toPairs() {
        String strPairs = "";
        strPairs += "id=" + id + ",";
        strPairs += "texto=" + EncodingUtilHelper.quotate(texto) + ",";
        strPairs += "fecha=" + EncodingUtilHelper.stringifyAndQuotate(fecha) + ",";
        strPairs += "id_usuario=" + id_usuario;
        return strPairs;
    }

    @Override
    public PublicacionesBean fill(ResultSet oResultSet, Connection pooledConnection, Integer expand) throws SQLException, Exception {
        this.setId(oResultSet.getInt("id"));
        this.setTexto(oResultSet.getString("texto"));
        this.setFecha(oResultSet.getDate("fecha"));
        if (expand > 0) {
            UsuarioBean oUsuarioBean = new UsuarioBean();
            UsuarioDao oUsuarioDao = new UsuarioDao(pooledConnection);
            oUsuarioBean.setId(oResultSet.getInt("id_usuario"));
            oUsuarioBean = oUsuarioDao.get(oUsuarioBean, expand - 1);
            this.setObj_usuario(oUsuarioBean);
        } else {
            this.setId_usuario(oResultSet.getInt("id_usuario"));
        }
        return this;

    }
    
}
