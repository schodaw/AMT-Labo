/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.amt.lab5.services;

import ch.heigvd.amt.lab5.model.Sensor;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author admin
 */
@Local
public interface SensorDAO {
    long create(Sensor s);
    void delete(int id);
    void update(Sensor oldS, Sensor newS);
    Sensor findById(int id);
    List<Sensor> findByType(String type);
    List<Sensor> findByDescription(String description);
    List<Sensor> findAll();
}
