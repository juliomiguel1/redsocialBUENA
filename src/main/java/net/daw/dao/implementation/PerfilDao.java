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

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import net.daw.bean.implementation.PerfilBean;
import net.daw.bean.implementation.PerfilBean;
import net.daw.dao.publicinterface.TableDaoInterface;
import net.daw.dao.publicinterface.ViewDaoInterface;
import net.daw.data.implementation.MysqlDataSpImpl;
import net.daw.helper.statics.ExceptionBooster;
import net.daw.helper.statics.FilterBeanHelper;
import net.daw.helper.statics.SqlBuilder;

/**
 *
 * @author juliomiguel
 */
public class PerfilDao implements ViewDaoInterface<PerfilBean>, TableDaoInterface<PerfilBean>{
private String strTable = "perfil";
    private String strSQL = "select * from perfil where 1=1 ";
    private String actualizarusuario = "  ";
    private MysqlDataSpImpl oMysql = null;
    private Connection oConnection = null;

    public PerfilDao(Connection oPooledConnection) throws Exception {
        try {
            oConnection = oPooledConnection;
            oMysql = new MysqlDataSpImpl(oConnection);
        } catch (Exception ex) {
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":constructor ERROR: " + ex.getMessage()));
        }
    }

    @Override
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

    @Override
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

    @Override
    public ArrayList<PerfilBean> getPage(int intRegsPerPag, int intPage, ArrayList<FilterBeanHelper> hmFilter, HashMap<String, String> hmOrder, Integer expand) throws Exception {
        strSQL += SqlBuilder.buildSqlWhere(hmFilter);
        strSQL += SqlBuilder.buildSqlOrder(hmOrder);
        strSQL += SqlBuilder.buildSqlLimit(oMysql.getCount(strSQL), intRegsPerPag, intPage);
        ArrayList<PerfilBean> arrPerfil = new ArrayList<>();
        try {
            ResultSet oResultSet = oMysql.getAllSql(strSQL);
            if (oResultSet != null) {
                while (oResultSet.next()) {
                    PerfilBean oPerfilBean = new PerfilBean();
                    arrPerfil.add(oPerfilBean.fill(oResultSet, oConnection, expand));
                }
            }
        } catch (Exception ex) {
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":getPage ERROR: " + ex.getMessage()));
        }
        return arrPerfil;
    }

    @Override
    public ArrayList<PerfilBean> getAll(ArrayList<FilterBeanHelper> alFilter, HashMap<String, String> hmOrder, Integer expand) throws Exception {
        strSQL += SqlBuilder.buildSqlOrder(hmOrder);
        ArrayList<PerfilBean> arrPerfil = new ArrayList<>();
        try {
            ResultSet oResultSet = oMysql.getAllSql(strSQL);
            if (oResultSet != null) {
                while (oResultSet.next()) {
                    PerfilBean oPerfilBean = new PerfilBean();
                    arrPerfil.add(oPerfilBean.fill(oResultSet, oConnection,expand));
                }
            }
        } catch (Exception ex) {
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":getPage ERROR: " + ex.getMessage()));
        }
        return arrPerfil;
    }

    @Override
    public PerfilBean get(PerfilBean oPerfilBean, Integer expand) throws Exception {
        if (oPerfilBean.getId() > 0) {
            try {
                ResultSet oResultSet = oMysql.getAllSql(strSQL + " And id= " + oPerfilBean.getId() + " ");
                if (oResultSet != null) {
                    while (oResultSet.next()) {
                        oPerfilBean = oPerfilBean.fill(oResultSet, oConnection,expand);
                    }
                }
            } catch (Exception ex) {
                ExceptionBooster.boost(new Exception(this.getClass().getName() + ":get ERROR: " + ex.getMessage()));
            }
        } else {
            oPerfilBean.setId(0);
        }
        return oPerfilBean;
    }
    /*
      public PerfilBean get(PerfilBean oPerfilBean, Integer expand) throws Exception {
        if (oPerfilBean.getId_usuario()> 0) {
            try {
                ResultSet oResultSet = oMysql.getAllSql(strSQL + " And id_usuario= " + oPerfilBean.getId_usuario() + " ");
                if (oResultSet != null) {
                    while (oResultSet.next()) {
                        oPerfilBean = oPerfilBean.fill(oResultSet, oConnection,expand);
                    }
                }
            } catch (Exception ex) {
                ExceptionBooster.boost(new Exception(this.getClass().getName() + ":get ERROR: " + ex.getMessage()));
            }
        } else {
            oPerfilBean.setId(0);
        }
        return oPerfilBean;
    }
    */
    @Override
    public Integer set(PerfilBean oPerfilBean) throws Exception {
          Integer iResult = null;
        try {
            if (oPerfilBean.getId() == 0) {
                strSQL = "INSERT INTO " + strTable + " ";
                strSQL += "(" + oPerfilBean.getColumns() + ")";
                strSQL += "VALUES(" + oPerfilBean.getValues() + ")";
                iResult = oMysql.executeInsertSQL(strSQL);
                ResultSet oResultSet = oMysql.getAllSql("SELECT MAX(id) AS id FROM usuario");
                if(oResultSet != null){
                while (oResultSet.next()){
                    actualizarusuario += "UPDATE usuario " + " ";
                    actualizarusuario += "SET id_perfil="+oResultSet.getInt("id") +" "; 
                    actualizarusuario += "WHERE id_perfil=0";
                    oMysql.executeUpdateSQL(actualizarusuario);
                    }
                 }
                
                
            } else {
                strSQL = "UPDATE " + strTable + " ";
                strSQL += " SET " + oPerfilBean.toPairs();
                strSQL += " WHERE id=" + oPerfilBean.getId();
                iResult = oMysql.executeUpdateSQL(strSQL);
                
              
            }

        } catch (Exception ex) {
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":set ERROR: " + ex.getMessage()));
        }
        return iResult;
    }

    @Override
    public Integer remove(Integer id) throws Exception {
        int result = 0;
        try {
            result = oMysql.removeOne(id, strTable);
        } catch (Exception ex) {
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":remove ERROR: " + ex.getMessage()));
        }
        return result;
    }
    
}
