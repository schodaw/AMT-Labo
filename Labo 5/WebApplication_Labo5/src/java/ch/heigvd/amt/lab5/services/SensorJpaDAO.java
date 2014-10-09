/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.amt.lab5.services;

import ch.heigvd.amt.lab5.model.Sensor;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author admin
 */
@Stateless
public class SensorJpaDAO implements SensorDAO {
    
    //on peut passer en paramètre un persistence unit spécifique quand on crée le EntityManager
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public long create(Sensor s) {
        entityManager.persist(s);
        entityManager.flush();
        return s.getId();
    }

    @Override
    public void delete(int id) {
        entityManager.remove(entityManager.find(Sensor.class, id));
    }

    @Override
    public void update(Sensor oldS, Sensor newS) {
        //cette ligne crée l'objet s dans le contexte de persistence et copie les attribut de oldS dedans (oldS est toujours hors contexte de persistence)
        Sensor s = entityManager.merge(oldS);
        s.setDescription(newS.getDescription());
        s.setType(newS.getType());
        entityManager.flush();
    }

    @Override
    public Sensor findById(int id) {
        return entityManager.find(Sensor.class, id);
    }

    @Override
    public List<Sensor> findByType(String type) {
        return entityManager.createNamedQuery("findSensorsByType").setParameter("type", type).getResultList();
    }

    @Override
    public List<Sensor> findByDescription(String description) {
        return entityManager.createNamedQuery("findSensorsByDescription").setParameter("description", description).getResultList();
    }

    @Override
    public List<Sensor> findAll() {
        return entityManager.createNamedQuery("findAllSensors").getResultList();
    }
}
