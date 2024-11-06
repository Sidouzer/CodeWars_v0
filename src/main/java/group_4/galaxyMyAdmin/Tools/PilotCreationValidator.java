package group_4.galaxyMyAdmin.Tools;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import group_4.galaxyMyAdmin.Models.Pilot;



@Component
public class PilotCreationValidator implements Validator {

    final int MIN_NAMES_LENGTH = 1;
    final int MAX_NAMES_LENGTH = 50;
    final int MIN_AGE = 10;
    final int MAX_AGE = 800;

    


    @Override
    public boolean supports(@NonNull Class<?> clazz) {
        return Pilot.class.equals(clazz);
    }




    @Override

    public void validate(@NonNull Object target, @NonNull Errors errors) {
        Pilot pilot = (Pilot) target;
        if(pilot.getFirstname().trim().length() < MIN_NAMES_LENGTH || pilot.getFirstname().trim().length() > MAX_NAMES_LENGTH) {
            errors.rejectValue(
                "firstname", 
                "field.length", 
                String.format("Length must be between %d and %d characters", 
                    MIN_NAMES_LENGTH, MAX_NAMES_LENGTH));
        }
        if(pilot.getLastname().trim().length() < MIN_NAMES_LENGTH || pilot.getFirstname().trim().length() > MAX_NAMES_LENGTH) {
            errors.rejectValue(
                "lastname", 
                "field.length", 
                String.format("Length must be between %d and %d characters", 
                    MIN_NAMES_LENGTH, MAX_NAMES_LENGTH));        
        }   
        if(pilot.getRace() == null) {
            errors.rejectValue(
                "race", 
                "field.value", 
                "Please choose a race from the list");
        }
        if(pilot.getRegistrationAge() < MIN_AGE || pilot.getRegistrationAge() > MAX_AGE) {
            errors.rejectValue(
                "registrationAge", 
                "field.value", 
                String.format("The age must be between %d and %d", 
                    MIN_AGE, MAX_AGE));
        }
    }
}
