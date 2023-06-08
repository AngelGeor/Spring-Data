package softuni.exam.models.dto.ImportMechanic;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import javax.validation.constraints.Size;

public class ImportMechanicsDTO {

    @Email
    @NotBlank
    private String email;

    @Size(min = 2)
    @NotBlank
    private String firstName;

    @Size(min = 2)
    @NotBlank
    private String lastName;

    @Size(min = 2)
    @NotBlank
    private String phone;

    public ImportMechanicsDTO() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
