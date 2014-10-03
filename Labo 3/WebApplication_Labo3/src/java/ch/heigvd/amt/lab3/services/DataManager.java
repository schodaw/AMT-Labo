/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.amt.lab3.services;

import ch.heigvd.amt.lab3.model.Measure;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.Singleton;

/**
 *
 * @author admin
 */
@Singleton
public class DataManager implements DataManagerLocal {

    private static List<Object> data = new LinkedList<Object>();

    @Override
    public List<Object> getData() {
        return new LinkedList(data);
    }

    @Override
    public void insertData(Object o) {
        data.add(o);
    }
}
