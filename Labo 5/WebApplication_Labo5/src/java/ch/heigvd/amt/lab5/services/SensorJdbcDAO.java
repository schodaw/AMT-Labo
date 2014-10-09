package ch.heigvd.amt.lab5.services;

import ch.heigvd.amt.lab5.model.Sensor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;

@Stateless
public class SensorJdbcDAO implements SensorDAO {

    @Resource(lookup = "jdbc/AMTDatabase")	
    private DataSource dataSource;

    @Override
    public long create(Sensor s) {
        long generatedId = -1;
        try {	
            Connection con = dataSource.getConnection();	
            PreparedStatement ps = con.prepareStatement("INSERT INTO sensors (description, type) VALUES (?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, s.getDescription());
            ps.setString(2, s.getType());
            ps.executeUpdate();
            
            ResultSet rs = ps.getGeneratedKeys();
            if (rs != null && rs.first()) {
                // on récupère l'id généré
                generatedId = rs.getInt(1);
            }

            ps.close();	
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(SensorJdbcDAO.class.getName()).log(Level.SEVERE,	null,	ex);	
        }
        return generatedId;
    }

    @Override
    public void delete(int id) {
        try {	
            Connection con = dataSource.getConnection();	
            PreparedStatement ps = con.prepareStatement("DELETE FROM sensors WHERE id = ?");	
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();	
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(SensorJdbcDAO.class.getName()).log(Level.SEVERE,	null,	ex);	
        }
    }

    @Override
    public void update(Sensor oldS, Sensor newS) {
        try {	
            Connection con = dataSource.getConnection();	
            PreparedStatement ps = con.prepareStatement("UPDATE sensors SET id = ?, description = ?, type = ? WHERE id = ? AND description = ? AND type = ?");	
            ps.setInt(1, newS.getId());
            ps.setString(2, newS.getDescription());
            ps.setString(3, newS.getType());
            ps.setInt(4, oldS.getId());
            ps.setString(5, oldS.getDescription());
            ps.setString(6, oldS.getType());
            ps.executeUpdate();
            ps.close();	
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(SensorJdbcDAO.class.getName()).log(Level.SEVERE,	null,	ex);	
        }
    }

    @Override
    public Sensor findById(int id) {
        Sensor result = null;
        try {	
            Connection con = dataSource.getConnection();	
            PreparedStatement ps = con.prepareStatement("SELECT	* FROM sensors WHERE id = ?");	
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();	
            result.setId(rs.getInt("id"));
            result.setDescription(rs.getString("description"));
            result.setType(rs.getString("type"));
            ps.close();	
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(SensorJdbcDAO.class.getName()).log(Level.SEVERE,	null,	ex);	
        }
        return result;
    }

    @Override
    public List<Sensor> findByType(String type) {        
        List<Sensor> result = new LinkedList<>();
        try {	
            Connection con = dataSource.getConnection();	
            PreparedStatement ps = con.prepareStatement("SELECT	* FROM sensors WHERE type = ?");
            ps.setString(1, type);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Sensor sensor = new Sensor();	
                sensor.setId(rs.getInt("id"));
                sensor.setDescription(rs.getString("description"));
                sensor.setType(rs.getString("type"));
                result.add(sensor);
            }	
            ps.close();	
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(SensorJdbcDAO.class.getName()).log(Level.SEVERE,	null,	ex);	
        }	
        return	result;
    }

    @Override
    public List<Sensor> findByDescription(String description) {
        List<Sensor> result = new LinkedList<>();
        try {	
            Connection con = dataSource.getConnection();	
            PreparedStatement ps = con.prepareStatement("SELECT	* FROM sensors WHERE description = ?");
            ps.setString(1, description);
            ResultSet rs = ps.executeQuery();	
            while (rs.next()) {
                Sensor sensor = new Sensor();	
                sensor.setId(rs.getInt("id"));	
                sensor.setDescription(rs.getString("description"));	
                sensor.setType(rs.getString("type"));	
                result.add(sensor);	
            }	
            ps.close();	
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(SensorJdbcDAO.class.getName()).log(Level.SEVERE,	null,	ex);	
        }	
        return	result;
    }
    
    public List<Sensor> findAll() {	
        List<Sensor> result = new LinkedList<>();
        try {	
            Connection con = dataSource.getConnection();	
            PreparedStatement ps = con.prepareStatement("SELECT	* FROM sensors");	
            ResultSet rs = ps.executeQuery();	
            while (rs.next()) {
                Sensor sensor = new Sensor();	
                sensor.setId(rs.getInt("id"));	
                sensor.setDescription(rs.getString("description"));	
                sensor.setType(rs.getString("type"));	
                result.add(sensor);
            }	
            ps.close();	
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(SensorJdbcDAO.class.getName()).log(Level.SEVERE,	null,	ex);	
        }
        return	result;
    }
}
