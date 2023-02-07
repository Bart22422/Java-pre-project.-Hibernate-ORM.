package hiber.model;

import javax.persistence.*;

@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "model")
    private String model;
    @Column(name = "series")
    private int series;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private User user;


    public Car(String model, int series) {
        this.model = model;
        this.series = series;
    }

    public Car() { }

    public void setModel(String model) {
        this.model = model;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    public int getSeries() {
        return series;
    }
    public String getModel(){
        return model;
    }

    public Long getId() {
        return id;
    }
   ;
}
