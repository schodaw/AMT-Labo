package ch.heigvd.amt.lab5.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="sensors")
@NamedQueries({
        @NamedQuery(
            name="findAllSensors",
            query="SELECT s FROM Sensor s"
        ),
        @NamedQuery(
            name="findSensorsByDescription",
            query="SELECT s FROM Sensor s WHERE s.description LIKE :description"
        ),
        @NamedQuery(
            name="findSensorsByType",
            query="SELECT s FROM Sensor s WHERE s.type LIKE :type"
        )
})
public class Sensor implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String description;
    private String type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
