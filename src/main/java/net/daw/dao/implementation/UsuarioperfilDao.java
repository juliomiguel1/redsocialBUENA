
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
import net.daw.bean.implementation.ComentarioBean;
import net.daw.bean.implementation.PerfilBean;
import net.daw.bean.implementation.UsuarioBean;
import net.daw.bean.implementation.UsuarioperfilBean;
import net.daw.dao.publicinterface.TableDaoInterface;
import net.daw.dao.publicinterface.ViewDaoInterface;
import net.daw.data.implementation.MysqlDataSpImpl;
import net.daw.helper.statics.ExceptionBooster;
import net.daw.helper.statics.FilterBeanHelper;
import net.daw.helper.statics.SqlBuilder;

public class UsuarioperfilDao {

    private String strTable = "comentario";
    private String strSQL = "select * from comentario where 1=1 ";
    private MysqlDataSpImpl oMysql = null;
    private Connection oConnection = null;

    public UsuarioperfilDao(Connection oPooledConnection) throws Exception {
        try {
            oConnection = oPooledConnection;
            oMysql = new MysqlDataSpImpl(oConnection);
        } catch (Exception ex) {
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":constructor ERROR: " + ex.getMessage()));
        }
    }

     public Integer set(UsuarioperfilBean oUsuarioperfilBean) throws Exception {
          Integer iResult = null;
          UsuarioBean oUsuarioBean = new UsuarioBean();
          oUsuarioBean.setId(oUsuarioperfilBean.getId());
          oUsuarioBean.setApellido(oUsuarioperfilBean.getApellido());
          oUsuarioBean.setEmail(oUsuarioperfilBean.getEmail());
          oUsuarioBean.setNombre(oUsuarioperfilBean.getNombre());
          oUsuarioBean.setPassword(oUsuarioperfilBean.getPassword());
           
          
        try {
            if (oUsuarioperfilBean.getId() == 0) {
                strSQL = "INSERT INTO " + " usuario " + " ";
                strSQL += "(" + oUsuarioBean.getColumns() + ")";
                strSQL += "VALUES(" + oUsuarioBean.getValues() + ")";
                iResult = oMysql.executeInsertSQL(strSQL);
                int maxusuario = 0;
                
                if(iResult != null){
                    
                    PerfilBean oPerfilBean = new PerfilBean();          
                    String consulta = "SELECT MAX(id) AS id FROM usuario";
                    ResultSet oResultSet = oMysql.getAllSql(consulta);
                    if(oResultSet != null){
                        while(oResultSet.next()){
                            oPerfilBean.setId_usuario(oResultSet.getInt("id"));
                            maxusuario = oResultSet.getInt("id");
                        }
                    }
                    
                    oPerfilBean.setDireccion(oUsuarioperfilBean.getDireccion());
                    oPerfilBean.setEstado_civil(oUsuarioperfilBean.getEstado_civil());
                    oPerfilBean.setEstudio(oUsuarioperfilBean.getEstudio());
                    oPerfilBean.setOcupacion(oUsuarioperfilBean.getOcupacion());
                    
                        strSQL = "INSERT INTO " + " perfil " + " ";
                        strSQL += "(" + oPerfilBean.getColumns() + ")";
                        strSQL += "VALUES(" + oPerfilBean.getValues() + ")";
                        iResult = oMysql.executeInsertSQL(strSQL);
                        int num= 0;
                        consulta = "SELECT MAX(id) AS id FROM perfil";
                        ResultSet oResultSet2 = oMysql.getAllSql(consulta);
                        if(oResultSet2 != null){
                            while(oResultSet2.next()){
                                num = oResultSet2.getInt("id");                            
                            }
                        }
                        strSQL = "UPDATE " + " usuario " + " ";
                        strSQL += " SET id_perfil=" + num + " ";
                        strSQL += " WHERE id=" + maxusuario;
                        iResult = oMysql.executeUpdateSQL(strSQL);
                }
                
            } /*else {
                strSQL = "UPDATE " + " usuario " + " ";
                strSQL += " SET " + oUsuarioBean.toPairs();
                strSQL += " WHERE id=" + oUsuarioBean.getId();
                iResult = oMysql.executeUpdateSQL(strSQL);
            }*/

        } catch (Exception ex) {
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":set ERROR: " + ex.getMessage()));
        }
        return iResult;
    }

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
