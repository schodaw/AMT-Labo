package ch.heigvd.amt.lab1.services;

import ch.heigvd.amt.lab1.model.Measure;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class MeasuresManager {
    private static Random rn = new Random();
    
    public List<Measure> getMeasures() {
        int measureNb = 1;
        List<Measure> measures = new LinkedList<Measure>();
        for (int i = 0; i < measureNb; i++) {
            String sensorId = "ID-" + i;
            long ts = System.currentTimeMillis();
            double value = rn.nextDouble();
            measures.add(new Measure(sensorId,ts,value));
        }
        return measures;
    }
}
