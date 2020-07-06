package com.demo.social.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.demo.social.dao.AppUserDAO;
import com.demo.social.form.AppUserForm;
import com.demo.social.model.AppRole;
import com.demo.social.model.AppUser;
import com.demo.social.utils.SecurityUtil;
import com.demo.social.utils.WebUtils;
import com.demo.social.validator.AppUserValidator;
  
@Controller
@Transactional
public class MainController {
  
    @Autowired
    private AppUserDAO appUserDAO;
  
    @Autowired
    private ConnectionFactoryLocator connectionFactoryLocator;
  
    @Autowired
    private UsersConnectionRepository connectionRepository;
  
    @Autowired
    private AppUserValidator appUserValidator;
  
    @InitBinder
    protected void initBinder(WebDataBinder dataBinder) {
  
        // Form target
        Object target = dataBinder.getTarget();
        if (target == null) {
            return;
        }
        System.out.println("Target=" + target);
  
        if (target.getClass() == AppUserForm.class) {
            dataBinder.setValidator(appUserValidator);
        }
        // ...
    }
  
    @RequestMapping(value = { "/", "/welcome" }, method = RequestMethod.GET)
    public String welcomePage(Model model) {
        model.addAttribute("title", "Welcome");
        model.addAttribute("message", "This is welcome page!");
        return "welcomePage";
    }
  
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminPage(Model model, Principal principal) {
  
        // After user login successfully.
        String userName = principal.getName();
  
        System.out.println("User Name: " + userName);
  
        UserDetails loginedUser = (UserDetails) ((Authentication) principal).getPrincipal();
  
        String userInfo = WebUtils.toString(loginedUser);
        model.addAttribute("userInfo", userInfo);
  
        return "adminPage";
    }
  
    @RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
    public String logoutSuccessfulPage(Model model) {
        model.addAttribute("title", "Logout");
        return "logoutSuccessfulPage";
    }
  
    @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
    public String userInfo(Model model, Principal principal) {
  
        // After user login successfully.
        String userName = principal.getName();
  
        System.out.println("User Name: " + userName);
  
        UserDetails loginedUser = (UserDetails) ((Authentication) principal).getPrincipal();
  
        String userInfo = WebUtils.toString(loginedUser);
        model.addAttribute("userInfo", userInfo);
  
        return "userInfoPage";
    }
  
    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied(Model model, Principal principal) {
  
        if (principal != null) {  
            UserDetails loginedUser = (UserDetails) ((Authentication) principal).getPrincipal();
             
            String userInfo = WebUtils.toString(loginedUser);
  
            model.addAttribute("userInfo", userInfo);
  
            String message = "Hi " + principal.getName() //
                    + "<br> You do not have permission to access this page!";
            model.addAttribute("message", message);
  
        }
  
        return "403Page";
    }
  
    @RequestMapping(value = { "/login" }, method = RequestMethod.GET)
    public String login(Model model) {
        return "loginPage";
    }
  
    // User login with social networking,
    // but does not allow the app to view basic information
    // application will redirect to page / signin.
    @RequestMapping(value = { "/signin" }, method = RequestMethod.GET)
    public String signInPage(Model model) {
        return "redirect:/login";
    }
  
    @RequestMapping(value = { "/signup" }, method = RequestMethod.GET)
    public String signupPage(WebRequest request, Model model) {
  
        ProviderSignInUtils providerSignInUtils //
                = new ProviderSignInUtils(connectionFactoryLocator, connectionRepository);
  
        // Retrieve social networking information.
        Connection<?> connection = providerSignInUtils.getConnectionFromSession(request);
        //
        AppUserForm myForm = null;
        //
        if (connection != null) {
            myForm = new AppUserForm(connection);
        } else {
            myForm = new AppUserForm();
        }
        model.addAttribute("myForm", myForm);
        return "signupPage";
    }
  
    @RequestMapping(value = { "/signup" }, method = RequestMethod.POST)
    public String signupSave(WebRequest request, //
            Model model, //
            @ModelAttribute("myForm") @Validated AppUserForm appUserForm, //
            BindingResult result, //
            final RedirectAttributes redirectAttributes) {
  
        // Validation error.
        if (result.hasErrors()) {
            return "signupPage";
        }
  
        List<String> roleNames = new ArrayList<String>();
        roleNames.add(AppRole.ROLE_USER);
  
        AppUser registered = null;
  
        try {
            registered = appUserDAO.registerNewUserAccount(appUserForm, roleNames);
        } catch (Exception ex) {
            ex.printStackTrace();
            model.addAttribute("errorMessage", "Error " + ex.getMessage());
            return "signupPage";
        }
  
        if (appUserForm.getSignInProvider() != null) {
            ProviderSignInUtils providerSignInUtils //
                    = new ProviderSignInUtils(connectionFactoryLocator, connectionRepository);
  
            // (Spring Social API):
            // If user login by social networking.
            // This method saves social networking information to the UserConnection table.
            providerSignInUtils.doPostSignUp(registered.getUserName(), request);
        }
  
        // After registration is complete, automatic login.
        SecurityUtil.logInUser(registered, roleNames);
  
        return "redirect:/userInfo";
    }
  
}