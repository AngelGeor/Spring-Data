package softuni.exam.models.dto.ImportPart;

import javax.validation.constraints.*;

public class ImportPartsDTO {

    @Size(min = 2, max = 19)
    @NotNull
    private String partName;

    @Min(10)
    @Max(2000)
    @NotNull
    private double price;

    @Positive
    @NotNull
    private Integer quantity;

    public ImportPartsDTO() {
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
