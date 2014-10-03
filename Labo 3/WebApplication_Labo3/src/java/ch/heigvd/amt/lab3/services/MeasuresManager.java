/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.amt.lab3.services;

import ch.heigvd.amt.lab3.model.Measure;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author admin
 */
@Stateless
public class MeasuresManager implements MeasuresManagerLocal {

    @EJB
    private DataManagerLocal dataManager;
    
    public void addMeasure(Measure m) {
        dataManager.insertData(m);
    }
    
    public List<Measure> getMeasures() {
        return dataManager.getData();
    }
}
