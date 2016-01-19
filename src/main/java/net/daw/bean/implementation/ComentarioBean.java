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

/**
 *
 * @author juliomiguel
 */
import net.daw.bean.publicinterface.GenericBean;
import com.google.gson.annotations.Expose;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import net.daw.dao.implementation.AmistadDao;

public class ComentarioBean implements GenericBean {

    @Expose
    private Integer id;
    @Expose
    private String texto;
    @Expose
    private Date fecha = new Date();
    @Expose(serialize = false)
    private Integer id_amistad = 0;
    @Expose(deserialize = false)
    private AmistadBean obj_amistad = null;

    public ComentarioBean() {
        this.id = 0;
    }

    public ComentarioBean(Integer id) {
        this.id = id;
    }
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the estado_civil
     */
    public String getTexto() {
        return texto;
    }

    /**
     * @param estado_civil the estado_civil to set
     */
    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the id_usuario
     */
    public Integer getId_amistad() {
        return id_amistad;
    }

    /**
     * @param id_usuario the id_usuario to set
     */
    public void setId_amistad(Integer id_amistad) {
        this.id_amistad = id_amistad;
    }

    /**
     * @return the obj_usuario
     */
    public AmistadBean getObj_amistad() {
        return obj_amistad;
    }

    /**
     * @param obj_usuario the obj_usuario to set
     */
    public void setObj_amistad(AmistadBean obj_amistad) {
        this.obj_amistad = obj_amistad;
    }
    
    public String toJson(Boolean expand) {
        String strJson = "{";
        strJson += "id:" + id + ",";
        strJson += "texto:" + texto + ",";
        strJson += "fecha:" + fecha + ",";
        if (expand) {
            strJson += "obj_amistad:" + obj_amistad.toJson(false) + ",";
        } else {
            strJson += "id_amistad:" + id_amistad + ",";
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
        strColumns += "id_amistad";
        
        return strColumns;
    }

    @Override
    public String getValues() {
        String strColumns = "";
        strColumns += id + ",";        
        strColumns += '"'+texto +'"'+ ",";        
        strColumns += fecha + ",";
        strColumns += id_amistad;
        return strColumns;
    }

    @Override
    public String toPairs() {
        String strPairs = "";
        strPairs += "id=" + id + ",";
        strPairs += "texto=" + texto + ",";
        strPairs += "fecha=" + fecha + ",";
        strPairs += "id_amistad=" + id_amistad;
        return strPairs;
    }

    @Override
    public ComentarioBean fill(ResultSet oResultSet, Connection pooledConnection, Integer expand) throws SQLException, Exception {
        this.setId(oResultSet.getInt("id"));
        this.setTexto(oResultSet.getString("texto"));
        this.setFecha(oResultSet.getDate("fecha"));
        if (expand > 0) {
            AmistadBean oAmistadBean = new AmistadBean();
            AmistadDao oAmistadDao = new AmistadDao(pooledConnection);
            oAmistadBean.setId(oResultSet.getInt("id_amistad"));
            oAmistadBean = oAmistadDao.get(oAmistadBean, expand - 1);
            this.setObj_amistad(oAmistadBean);
        } else {
            this.setId_amistad(oResultSet.getInt("id_amistad"));
        }
        return this;

    }
    
}
