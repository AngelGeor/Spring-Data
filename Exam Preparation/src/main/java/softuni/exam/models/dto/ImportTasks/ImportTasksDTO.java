package softuni.exam.models.dto.ImportTasks;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.math.BigDecimal;

@XmlAccessorType(XmlAccessType.FIELD)
public class ImportTasksDTO {

    @XmlElement
    private String date;

    @XmlElement
    @Positive
    private BigDecimal price;

    @XmlElement
    @NotNull
    private CarDTO car;

    @XmlElement
    private NameDTO mechanic;

    @XmlElement
    @NotNull
    private PartDTO part;

    public ImportTasksDTO() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public CarDTO getCar() {
        return car;
    }

    public void setCar(CarDTO car) {
        this.car = car;
    }

    public NameDTO getMechanic() {
        return mechanic;
    }

    public void setMechanic(NameDTO mechanic) {
        this.mechanic = mechanic;
    }

    public PartDTO getPart() {
        return part;
    }

    public void setPart(PartDTO part) {
        this.part = part;
    }
}
