package ru.ravvcheck.web3lab.validation;


import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.FacesValidator;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;

import java.math.BigDecimal;
import java.util.Map;
import java.util.regex.Pattern;


@FacesValidator("validatorX")
public class ValidatorR implements Validator {
    private static final String NUMBER_PATTERN = "^(-)?[0-9]+(\\.[0-9]+)?$";

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        Map<String, String> requestParameterMap = facesContext.getExternalContext().getRequestParameterMap();
        String source = requestParameterMap.get("form:source");
        if (o == null) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "The R field cannot be empty!"));
        }

        String input = o.toString();

        input = input.replace(",", ".");

        if (!Pattern.matches(NUMBER_PATTERN, input)) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "The value of R must be a number!"));
        }

        BigDecimal x = new BigDecimal(input);

        BigDecimal minX = new BigDecimal("2");
        BigDecimal maxX = new BigDecimal("5");
        BigDecimal step = new BigDecimal("0.1");

        if ("button".equals(source)) {
            BigDecimal currentValue = minX;
            boolean isValidX = false;
            while (currentValue.compareTo(maxX) <= 0) {
                if (currentValue.compareTo(x) == 0) {
                    isValidX = true;
                    break;
                }
                currentValue = currentValue.add(step);
            }
            if (!isValidX) {
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
                        "The value of R must be one of the numbers in the range from 2 to 5 with step 0,1"));
            }
        }
    }
}