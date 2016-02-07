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
import net.daw.dao.implementation.ProvinciasDao;
import net.daw.helper.statics.EncodingUtilHelper;

public class MunicipiosBean implements GenericBean {

    @Expose
    private Integer id;
    @Expose(serialize = false)
    private Integer provincia = 0;
    @Expose(deserialize = false)
    private ProvinciasBean obj_provincia = null;
    @Expose
    private String municipio;

    public MunicipiosBean() {
        this.id = 0;
    }

    public MunicipiosBean(Integer id) {
        this.id = id;
    }
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

   
    /**
     * @return the provincia
     */
    public Integer getProvincia() {
        return provincia;
    }

    /**
     * @param provincia the provincia to set
     */
    public void setProvincia(Integer provincia) {
        this.provincia = provincia;
    }

    /**
     * @return the obj_provincia
     */
    public ProvinciasBean getObj_provincia() {
        return obj_provincia;
    }

    /**
     * @param obj_provincia the obj_provincia to set
     */
    public void setObj_provincia(ProvinciasBean obj_provincia) {
        this.obj_provincia = obj_provincia;
    }

    /**
     * @return the municipio
     */
    public String getMunicipio() {
        return municipio;
    }

    /**
     * @param municipio the municipio to set
     */
    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }
    
    
    public String toJson(Boolean expand) {
        String strJson = "{";
        strJson += "id:" + id + ",";
        strJson += "municipio:" + municipio + ",";
        if (expand) {
            strJson += "obj_provincia:" + obj_provincia.toJson(false) + ",";
        } else {
            strJson += "provincia:" + provincia + ",";
        }
        strJson += "}";
        return strJson;
    }

    @Override
    public String getColumns() {
        String strColumns = "";
        strColumns += "id,";
        strColumns += "provincia,";
        strColumns += "municipio";
        
        return strColumns;
    }

    @Override
    public String getValues() {
        String strColumns = "";
        strColumns += id + ", ";        
        strColumns += provincia+ ", ";        
        strColumns += '"'+municipio+'"';
        return strColumns;
    }

    @Override
    public String toPairs() {
        String strPairs = "";
        strPairs += "id=" + id + ",";
        strPairs += "provincia=" + provincia;
        strPairs += "municipio=" + EncodingUtilHelper.quotate(municipio) + ",";
        return strPairs;
    }

    @Override
    public MunicipiosBean fill(ResultSet oResultSet, Connection pooledConnection, Integer expand) throws SQLException, Exception {
        this.setId(oResultSet.getInt("id"));
        if (expand > 0) {
            ProvinciasBean oProvinciasBean = new ProvinciasBean();
            ProvinciasDao oProvinciasDao = new ProvinciasDao(pooledConnection);
            oProvinciasBean.setId(oResultSet.getInt("provincia"));
            oProvinciasBean = oProvinciasDao.get(oProvinciasBean, expand - 1);
            this.setObj_provincia(oProvinciasBean);
        } else {
            this.setProvincia(oResultSet.getInt("provincia"));
        }
        this.setMunicipio(oResultSet.getString("municipio"));
        return this;

    }

}
