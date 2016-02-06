
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
package net.daw.dao.implementation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import net.daw.bean.implementation.AmistadBean;
import net.daw.dao.publicinterface.TableDaoInterface;
import net.daw.dao.publicinterface.ViewDaoInterface;
import net.daw.data.implementation.MysqlDataSpImpl;
import net.daw.helper.statics.ExceptionBooster;
import net.daw.helper.statics.FilterBeanHelper;
import net.daw.helper.statics.SqlBuilder;

public class AmistadDao   {

    private String strTable = "amistad";
    private String strSQL = "select * from amistad where 1=1 ";
    private MysqlDataSpImpl oMysql = null;
    private Connection oConnection = null;

    public AmistadDao(Connection oPooledConnection) throws Exception {
        try {
            oConnection = oPooledConnection;
            oMysql = new MysqlDataSpImpl(oConnection);
        } catch (Exception ex) {
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":constructor ERROR: " + ex.getMessage()));
        }
    }

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
    
    
    public int getPagesxidusuario(int id_usuario,int intRegsPerPag, ArrayList<FilterBeanHelper> hmFilter) throws Exception {
        strSQL += SqlBuilder.buildSqlWhere(hmFilter);
        int pages = 0;
        try {
            pages = oMysql.getPages(strSQL + " and amistad.id_usuario="+id_usuario+" GROUP by id_usuario2 " , intRegsPerPag);
        } catch (Exception ex) {
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":getPages ERROR: " + ex.getMessage()));
        }
        return pages;
    }
    
    
    public int getCountxidusuario(int id_usuario, ArrayList<FilterBeanHelper> hmFilter) throws Exception {
        strSQL += SqlBuilder.buildSqlWhere(hmFilter);
        int pages = 0;
        try {
            pages = oMysql.getCount(strSQL+ " and amistad.id_usuario="+id_usuario);
        } catch (Exception ex) {
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":getCount ERROR: " + ex.getMessage()));
        }
        return pages;
    }
    
    
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
    
    
    public ArrayList<AmistadBean> getPagexidusuario(int id_usuario,int intRegsPerPag, int intPage, ArrayList<FilterBeanHelper> hmFilter, HashMap<String, String> hmOrder, Integer expand) throws Exception {
        strSQL += SqlBuilder.buildSqlWhere(hmFilter);
        strSQL += SqlBuilder.buildSqlOrder(hmOrder);
        strSQL += "and amistad.id_usuario="+id_usuario+" GROUP by id_usuario2 " ;
        strSQL += SqlBuilder.buildSqlLimit(oMysql.getCount(strSQL), intRegsPerPag, intPage);
        ArrayList<AmistadBean> arrAmistad = new ArrayList<>();
        try {
            ResultSet oResultSet = oMysql.getAllSql(strSQL);
            if (oResultSet != null) {
                while (oResultSet.next()) {
                    AmistadBean oAmistadBean = new AmistadBean();
                    arrAmistad.add(oAmistadBean.fill(oResultSet, oConnection, expand));
                }
            }
        } catch (Exception ex) {
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":getPage ERROR: " + ex.getMessage()));
        }
        return arrAmistad;
    }
    

    public ArrayList<AmistadBean> getPage(int intRegsPerPag, int intPage, ArrayList<FilterBeanHelper> hmFilter, HashMap<String, String> hmOrder, Integer expand) throws Exception {
        strSQL += SqlBuilder.buildSqlWhere(hmFilter);
        strSQL += SqlBuilder.buildSqlOrder(hmOrder);
        strSQL += SqlBuilder.buildSqlLimit(oMysql.getCount(strSQL), intRegsPerPag, intPage);
        ArrayList<AmistadBean> arrAmistad = new ArrayList<>();
        try {
            ResultSet oResultSet = oMysql.getAllSql(strSQL);
            if (oResultSet != null) {
                while (oResultSet.next()) {
                    AmistadBean oAmistadBean = new AmistadBean();
                    arrAmistad.add(oAmistadBean.fill(oResultSet, oConnection, expand));
                }
            }
        } catch (Exception ex) {
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":getPage ERROR: " + ex.getMessage()));
        }
        return arrAmistad;
    }

    
    public ArrayList<AmistadBean> getAll(ArrayList<FilterBeanHelper> alFilter, HashMap<String, String> hmOrder, Integer expand,int id) throws Exception {
        strSQL = "select * from amistad where id_usuario ="+id; 
        strSQL += SqlBuilder.buildSqlOrder(hmOrder);
        ArrayList<AmistadBean> arrAmistad = new ArrayList<>();
        try {
            ResultSet oResultSet = oMysql.getAllSql(strSQL);
            if (oResultSet != null) {
                while (oResultSet.next()) {                    
                    AmistadBean oAmistadBean = new AmistadBean();
                    arrAmistad.add(oAmistadBean.fill(oResultSet, oConnection, expand));
                }
            }
        } catch (Exception ex) {
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":getPage ERROR: " + ex.getMessage()));
        }
        return arrAmistad;
    }


    public AmistadBean get(AmistadBean oAmistadBean, Integer expand) throws Exception {
        if (oAmistadBean.getId() > 0) {
            try {
                ResultSet oResultSet = oMysql.getAllSql(strSQL + " And id= " + oAmistadBean.getId() + " ");
                if (oResultSet != null) {
                    while (oResultSet.next()) {
                        oAmistadBean = oAmistadBean.fill(oResultSet, oConnection, expand);
                    }
                }
            } catch (Exception ex) {
                ExceptionBooster.boost(new Exception(this.getClass().getName() + ":get ERROR: " + ex.getMessage()));
            }
        } else {
            oAmistadBean.setId(0);
        }
        return oAmistadBean;
    }

    
    public Integer set(AmistadBean oAmistadBean) throws Exception {
          Integer iResult = null;
        try {
            if (oAmistadBean.getId() == 0) {
                strSQL = "INSERT INTO " + strTable + " ";
                strSQL += "(" + oAmistadBean.getColumns() + ")";
                strSQL += "VALUES(" + oAmistadBean.getValues() + ")";
                iResult = oMysql.executeInsertSQL(strSQL);
                
                if(iResult != null){
                strSQL = "INSERT INTO " + strTable + " ";
                strSQL += "(" + oAmistadBean.getColumns() + ")";
                strSQL += "VALUES(" + oAmistadBean.getValues2() + ")";
                iResult = oMysql.executeInsertSQL(strSQL);
                }
            } else {
                strSQL = "UPDATE " + strTable + " ";
                strSQL += " SET " + oAmistadBean.toPairs();
                strSQL += " WHERE id=" + oAmistadBean.getId();
                iResult = oMysql.executeUpdateSQL(strSQL);
            }

        } catch (Exception ex) {
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":set ERROR: " + ex.getMessage()));
        }
        return iResult;
    }

   
    public Integer remove(Integer id) throws Exception {
        int result = 0;
        int id_usuario = 0;
        int id_usuario2 = 0;
        int id_nuevo = 0;
         String sentencia = "select * from amistad where id = "+ id;
        ResultSet oResultSet = oMysql.getAllSql(sentencia);
        if(oResultSet != null){
            while(oResultSet.next()){
                id_usuario = oResultSet.getInt("id_usuario");
                id_usuario2 = oResultSet.getInt("id_usuario2");
            }
        }
        
        String sentencia2 = "select * from amistad where (id_usuario="+ id_usuario+" and id_usuario2="+id_usuario2+") or (id_usuario="+id_usuario2+" and id_usuario2="+id_usuario+")";
        ResultSet oResultSet2 = oMysql.getAllSql(sentencia2);
        if(oResultSet2 != null){
            while(oResultSet2.next()){
                id_nuevo = oResultSet2.getInt("id");
                try {
                    result = oMysql.removeOne(id_nuevo, strTable);
                } catch (Exception ex) {
                    ExceptionBooster.boost(new Exception(this.getClass().getName() + ":remove ERROR: " + ex.getMessage()));
                }                
            }
        }
        
        
        return result;
    }

}
