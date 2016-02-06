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
import net.daw.dao.implementation.UsuarioDao;

public class PerfilBean implements GenericBean {

    @Expose
    private Integer id;
    @Expose
    private String estado_civil;
    @Expose
    private String direccion;
    @Expose
    private String ocupacion;
    @Expose
    private String estudio;
    @Expose
    private Boolean sexo = false;
    @Expose(serialize = false)
    private Integer id_usuario = 0;
    @Expose(deserialize = false)
    private UsuarioBean obj_usuario = null;

    public PerfilBean() {
        this.id = 0;
    }

    public PerfilBean(Integer id) {
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

    /**
     * @return the sexo
     */
    public Boolean getSexo() {
        return sexo;
    }

    /**
     * @param sexo the sexo to set
     */
    public void setSexo(Boolean sexo) {
        this.sexo = sexo;
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
        strJson += "direccion:" + direccion + ",";
        strJson += "estado_civil:" + estado_civil + ",";
        strJson += "ocupacion:" + ocupacion + ",";
        strJson += "estudio:" + estudio + ",";
        strJson += "sexo:" + sexo + ",";
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
        strColumns += "direccion,";
        strColumns += "estado_civil,";
        strColumns += "ocupacion,";
        strColumns += "estudio,";
        strColumns += "sexo,";
        strColumns += "id_usuario";
        
        return strColumns;
    }

    @Override
    public String getValues() {
        String strColumns = "";
        strColumns += id + ",";        
        strColumns += '"'+direccion +'"'+ ",";        
        strColumns += '"'+estado_civil +'"'+ ",";        
        strColumns += '"'+ocupacion +'"'+ ",";
        strColumns += '"'+estudio +'"'+ ",";
        strColumns += sexo +",";
        strColumns += id_usuario;
        return strColumns;
    }

    @Override
    public String toPairs() {
        String strPairs = "";
        strPairs += "id=" + getId() + ",";
        strPairs += "direccion= " +'"'+ direccion +'"'+ ",";
        strPairs += "estado_civil=" +'"'+ estado_civil +'"'+ ",";
        strPairs += "ocupacion=" +'"'+ ocupacion + '"'+",";
        strPairs += "estudio=" +'"'+ estudio +'"'+ ",";
        strPairs += "sexo=" + sexo + ",";
        strPairs += "id_usuario=" + id_usuario;
        return strPairs;
    }

    @Override
    public PerfilBean fill(ResultSet oResultSet, Connection pooledConnection, Integer expand) throws SQLException, Exception {
        this.setId(oResultSet.getInt("id"));
        this.setDireccion(oResultSet.getString("direccion"));
        this.setEstado_civil(oResultSet.getString("estado_civil"));
        this.setOcupacion(oResultSet.getString("ocupacion"));
        this.setEstudio(oResultSet.getString("estudio"));
        this.setSexo(oResultSet.getBoolean("sexo"));
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
