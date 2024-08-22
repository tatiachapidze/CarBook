package ge.gov.dga.carbook.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<ValidPassword, String> {

    private static final String PASSWORD_PATTERN =
            "^(?:(?=.*[A-Z].*)(?=.*[a-z].*)(?=.*\\d.*)(?=.*\\W.*)|(?=.*[A-Z].*)(?=.*\\d.*)(?=.*\\W.*).{2}|(?=.*[A-Z].*)(?=.*[a-z].*)(?=.*\\W.*).{2}|(?=.*[a-z].*)(?=.*\\d.*)(?=.*\\W.*).{2}).{8,}$";

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        return value.matches(PASSWORD_PATTERN);
    }
}