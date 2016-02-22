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
import net.daw.dao.implementation.AmistadDao;
import net.daw.dao.implementation.PublicacionesDao;
import net.daw.dao.implementation.UsuarioDao;
import net.daw.helper.statics.EncodingUtilHelper;

/**
 *
 * @author juliomiguel
 */
public class ComentariopublicacionesBean implements GenericBean {

    @Expose
    private Integer id;
    @Expose
    private String comentario;
    @Expose
    private Date fecha = new Date();
    @Expose(serialize = false)
    private Integer id_publicaciones = 0;
    @Expose(deserialize = false)
    private PublicacionesBean obj_publicaciones = null;
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
     * @return the comentario
     */
    public String getComentario() {
        return comentario;
    }

    /**
     * @param comentario the comentario to set
     */
    public void setComentario(String comentario) {
        this.comentario = comentario;
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
     * @return the id_publicaciones
     */
    public Integer getId_publicaciones() {
        return id_publicaciones;
    }

    /**
     * @param id_publicaciones the id_publicaciones to set
     */
    public void setId_publicaciones(Integer id_publicaciones) {
        this.id_publicaciones = id_publicaciones;
    }

    /**
     * @return the obj_publicaciones
     */
    public PublicacionesBean getObj_publicaciones() {
        return obj_publicaciones;
    }

    /**
     * @param obj_publicaciones the obj_publicaciones to set
     */
    public void setObj_publicaciones(PublicacionesBean obj_publicaciones) {
        this.obj_publicaciones = obj_publicaciones;
    }

    
    
     public String toJson(Boolean expand) {
        String strJson = "{";
        strJson += "id:" + id + ",";
        strJson += "comentario:" + comentario + ",";
        strJson += "fecha:" + fecha + ",";
        if (expand) {
            strJson += "obj_usuario:" + obj_usuario.toJson(false) + ",";
            strJson += "obj_publicaciones:" + obj_publicaciones.toJson(false) + ",";
        } else {
            strJson += "id_usuario:" + id_usuario + ",";
            strJson += "id_publicaciones:" + id_publicaciones + ",";
        }
        strJson += "}";
        return strJson;
    }
     
    @Override
    public String getColumns() {
        String strColumns = "";
        strColumns += "id,";
        strColumns += "comentario,";
        strColumns += "fecha,";
        strColumns += "id_publicaciones,";
        strColumns += "id_usuario";
        
        return strColumns;
    }
    
    @Override
    public String getValues() {
        String strColumns = "";
        strColumns += id + ",";        
        strColumns += '"'+comentario +'"'+ ",";        
        strColumns += EncodingUtilHelper.stringifyAndQuotate(fecha)+ ", ";
        strColumns += id_publicaciones + ","; 
        strColumns += id_usuario;
        return strColumns;
    }
    
    @Override
    public String toPairs() {
        String strPairs = "";
        strPairs += "id=" + id + ",";
        strPairs += "comentario=" + EncodingUtilHelper.quotate(comentario) + ",";
        strPairs += "fecha=" + EncodingUtilHelper.stringifyAndQuotate(fecha) + ",";
        strPairs += "id_publicaciones=" + id_publicaciones +",";
        strPairs += "id_usuario=" + id_usuario;
        return strPairs;
    }
    
    @Override
    public ComentariopublicacionesBean fill(ResultSet oResultSet, Connection pooledConnection, Integer expand) throws SQLException, Exception {
        this.setId(oResultSet.getInt("id"));
        this.setComentario(oResultSet.getString("comentario"));
        this.setFecha(oResultSet.getDate("fecha"));
        if (expand > 0) {
            PublicacionesBean oPublicacionesBean = new PublicacionesBean();
            PublicacionesDao oPublicacionesDao = new PublicacionesDao(pooledConnection);
            oPublicacionesBean.setId(oResultSet.getInt("id_publicaciones"));
            oPublicacionesBean = oPublicacionesDao.get(oPublicacionesBean, expand );
            this.setObj_publicaciones(oPublicacionesBean);
        } else {
            this.setId_publicaciones(oResultSet.getInt("id_publicaciones"));
        }
        if (expand > 0) {
            UsuarioBean oUsuarioBean = new UsuarioBean();
            UsuarioDao oUsuarioDao = new UsuarioDao(pooledConnection);
            oUsuarioBean.setId(oResultSet.getInt("id_usuario"));
            oUsuarioBean = oUsuarioDao.get(oUsuarioBean, expand );
            this.setObj_usuario(oUsuarioBean);
        } else {
            this.setId_usuario(oResultSet.getInt("id_usuario"));
        }
        return this;

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
}
