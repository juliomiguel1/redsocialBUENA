package net.daw.bean.implementation;

import com.google.gson.annotations.Expose;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import net.daw.bean.publicinterface.GenericBean;
import net.daw.helper.statics.EncodingUtilHelper;


/**
 *
 * @author juliomiguel
 */
public class ProvinciasBean implements GenericBean {

    @Expose
    private Integer id;
    @Expose
    private String provincia;
    
    public ProvinciasBean() {
        this.id = 0;
    }

    public ProvinciasBean(Integer id) {
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
     * @return the texto
     */
    public String getProvincia() {
        return provincia;
    }

    /**
     * @param texto the texto to set
     */
    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }
    
      public String toJson(Boolean expand) {
        String strJson = "{";
        strJson += "id:" + id + ",";
        strJson += "provincia:" + provincia;
        strJson += "}";
        return strJson;
    }
    
    @Override
    public String getColumns() {
        String strColumns = "";
        strColumns += "id,";
        strColumns += "provincia";
        return strColumns;
    }

    @Override
    public String getValues() {
        String strColumns = "";
        strColumns += id + ",";        
        strColumns += '"'+provincia +'"'+ ",";   
        return strColumns;
    }

    @Override
    public String toPairs() {
       String strPairs = "";
        strPairs += "id=" + id + ",";
        strPairs += "provincia=" + EncodingUtilHelper.quotate(provincia); 
        return strPairs;
    }

    @Override
    public ProvinciasBean fill(ResultSet oResultSet, Connection pooledConnection, Integer expand) throws SQLException, Exception {
        this.setId(oResultSet.getInt("id"));
        this.setProvincia(oResultSet.getString("provincia"));
        return this;
    }

}
