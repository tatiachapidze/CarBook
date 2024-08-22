package ge.gov.dga.carbook.model.dto.auth;

import ge.gov.dga.carbook.validators.ValidPassword;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    @NotBlank
    private String username;
    @NotBlank
    @ValidPassword
    private String password;
    @NotBlank
    @Pattern(regexp = "^(\\+?995)?5\\d{8}$", message = "Invalid phone number format")
    private String phone;
    @NotBlank
    @Email(message = "Invalid email format")
    private String userMail;
}
