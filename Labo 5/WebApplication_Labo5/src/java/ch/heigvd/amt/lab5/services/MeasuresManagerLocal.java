/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.amt.lab5.services;

import ch.heigvd.amt.lab5.model.Measure;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author admin
 */
@Local
public interface MeasuresManagerLocal {
    public void addMeasure(Measure m);
    
    public List<Measure> getMeasures();
}
