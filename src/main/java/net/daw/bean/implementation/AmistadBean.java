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

import net.daw.bean.publicinterface.GenericBean;
import com.google.gson.annotations.Expose;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import net.daw.dao.implementation.GrupoDao;
import net.daw.dao.implementation.TipodocumentoDao;
import net.daw.dao.implementation.UsuarioDao;
import net.daw.helper.statics.EncodingUtilHelper;

public class AmistadBean implements GenericBean {

    @Expose
    private Integer id;
    @Expose(serialize = false)
    private Integer id_usuario = 0;
    @Expose(deserialize = false)
    private UsuarioBean obj_usuario = null;
    @Expose(serialize = false)
    private Integer id_usuario2 = 0;
    @Expose(deserialize = false)
    private UsuarioBean obj_usuario2 = null;
    @Expose(serialize = false)
    private Integer id_grupo = 0;
    @Expose(deserialize = false)
    private GrupoBean obj_grupo = null;

    public AmistadBean() {
        this.id = 0;
    }

    public AmistadBean(Integer id) {
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
     * @return the id_usuario1
     */
    public Integer getId_usuario() {
        return id_usuario;
    }

    /**
     * @param id_usuario1 the id_usuario1 to set
     */
    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }

    /**
     * @return the obj_usuario1
     */
    public UsuarioBean getObj_usuario() {
        return obj_usuario;
    }

    /**
     * @param obj_usuario1 the obj_usuario1 to set
     */
    public void setObj_usuario(UsuarioBean obj_usuario) {
        this.obj_usuario = obj_usuario;
    }

    /**
     * @return the id_usuario2
     */
    public Integer getId_usuario2() {
        return id_usuario2;
    }

    /**
     * @param id_usuario2 the id_usuario2 to set
     */
    public void setId_usuario2(Integer id_usuario2) {
        this.id_usuario2 = id_usuario2;
    }

    /**
     * @return the obj_usuario2
     */
    public UsuarioBean getObj_usuario2() {
        return obj_usuario2;
    }

    /**
     * @param obj_usuario2 the obj_usuario2 to set
     */
    public void setObj_usuario2(UsuarioBean obj_usuario2) {
        this.obj_usuario2 = obj_usuario2;
    }

    /**
     * @return the id_grupo
     */
    public Integer getId_grupo() {
        return id_grupo;
    }

    /**
     * @param id_grupo the id_grupo to set
     */
    public void setId_grupo(Integer id_grupo) {
        this.id_grupo = id_grupo;
    }

    /**
     * @return the obj_grupo
     */
    public GrupoBean getObj_grupo() {
        return obj_grupo;
    }

    /**
     * @param obj_grupo the obj_grupo to set
     */
    public void setObj_grupo(GrupoBean obj_grupo) {
        this.obj_grupo = obj_grupo;
    }
    
    public String toJson(Boolean expand) {
        String strJson = "{";
        strJson += "id:" + getId() + ",";
        if (expand) {
            strJson += "obj_usuario:" + obj_usuario.toJson(false) + ",";
            strJson += "obj_usuario2:" + obj_usuario2.toJson(false) + ",";
            strJson += "obj_grupo:" + obj_grupo.toJson(false) + ",";
        } else {
            strJson += "id_usuario:" + id_usuario + ",";
            strJson += "id_usuario2:" + id_usuario2 + ",";
            strJson += "id_grupo:" + id_grupo + ",";
        }
        strJson += "}";
        return strJson;
    }
    

    public String getColumns() {
        String strColumns = "";
        strColumns += "id,";
        strColumns += "id_usuario,";
        strColumns += "id_usuario2,";
        strColumns += "id_grupo";
        return strColumns;
    }

    public String getValues() {
        String strColumns = "";
        strColumns += getId() + ",";
        strColumns += id_usuario + ",";
        strColumns += id_usuario2 + ",";
        strColumns += id_grupo;
        return strColumns;
    }
    
    public String getValues2(){
         String strColumns = "";
        strColumns += getId() + ",";
        strColumns += id_usuario2 + ",";
        strColumns += id_usuario + ",";
        strColumns += id_grupo;
        return strColumns;
    }

    @Override
    public String toPairs() {
        String strPairs = "";
        strPairs += "id=" + getId() + ",";
        strPairs += "id_usuario=" + id_usuario + ",";
        strPairs += "id_usuario2=" + id_usuario2 + ",";
        strPairs += "id_grupo=" + id_grupo;

        return strPairs;
    }

    @Override
    public AmistadBean fill(ResultSet oResultSet, Connection pooledConnection, Integer expand) throws SQLException, Exception {
        this.setId(oResultSet.getInt("id"));
        if (expand > 0) {
            UsuarioBean oUsuarioBean1 = new UsuarioBean();
            UsuarioDao oUsuarioDao1 = new UsuarioDao(pooledConnection);
            oUsuarioBean1.setId(oResultSet.getInt("id_usuario"));
            oUsuarioBean1 = oUsuarioDao1.get(oUsuarioBean1, expand - 1);
            this.setObj_usuario(oUsuarioBean1);
        } else {
            this.setId_usuario(oResultSet.getInt("id_usuario"));
        }
        if (expand > 0) {
            UsuarioBean oUsuarioBean2 = new UsuarioBean();
            UsuarioDao oUsuarioDao2 = new UsuarioDao(pooledConnection);
            oUsuarioBean2.setId(oResultSet.getInt("id_usuario2"));
            oUsuarioBean2 = oUsuarioDao2.get(oUsuarioBean2, expand - 1);
            this.setObj_usuario2(oUsuarioBean2);
        } else {
            this.setId_usuario2(oResultSet.getInt("id_usuario2"));
        }
        if (expand > 0) {
            GrupoBean oGrupoBean = new GrupoBean();
            GrupoDao oGrupoDao = new GrupoDao(pooledConnection);
            oGrupoBean.setId(oResultSet.getInt("id_grupo"));
            oGrupoBean = oGrupoDao.get(oGrupoBean, expand - 1);
            this.setObj_grupo(oGrupoBean);
        } else {
            this.setId_grupo(oResultSet.getInt("id_grupo"));
        }
        return this;
    }

    

}
