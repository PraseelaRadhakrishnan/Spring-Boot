package com.demo.social.validator;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.demo.social.dao.AppUserDAO;
import com.demo.social.form.AppUserForm;
import com.demo.social.model.AppUser;
  
@Component
public class AppUserValidator implements Validator {
  
    // common-validator library.
    private EmailValidator emailValidator = EmailValidator.getInstance();
  
    @Autowired
    private AppUserDAO appUserDAO;
  
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz == AppUserForm.class;
    }
  
    @Override
    public void validate(Object target, Errors errors) {
         
        AppUserForm form = (AppUserForm) target;
           
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "", "Email is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "", "User name is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "", "First name is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "", "Last name is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "", "Password is required");
  
        if (errors.hasErrors()) {
            return;
        }
  
        if (!emailValidator.isValid(form.getEmail())) {
               
            errors.rejectValue("email", "", "Email is not valid");
            return;
        }
  
        AppUser userAccount = appUserDAO.findAppUserByUserName( form.getUserName());
        if (userAccount != null) {
            if (form.getUserId() == null) {
                errors.rejectValue("userName", "", "User name is not available");
                return;
            } else if (!form.getUserId().equals(userAccount.getUserId() )) {
                errors.rejectValue("userName", "", "User name is not available");
                return;
            }
        }
  
        userAccount = appUserDAO.findByEmail(form.getEmail());
        if (userAccount != null) {
            if (form.getUserId() == null) {
                errors.rejectValue("email", "", "Email is not available");
                return;
            } else if (!form.getUserId().equals(userAccount.getUserId() )) {
                errors.rejectValue("email", "", "Email is not available");
                return;
            }
        }
    }
  
}