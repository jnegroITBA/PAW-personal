package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.interfaces.UserService;
import ar.edu.itba.paw.model.User;
import ar.edu.itba.paw.webapp.exception.UserNotFoundException;
import ar.edu.itba.paw.webapp.form.UserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class HelloWorldController {

    private final UserService userService;

    @Autowired
    public HelloWorldController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/", method = { RequestMethod.GET, RequestMethod.HEAD })
    public ModelAndView helloWorld(@RequestParam(name = "userId", defaultValue = "1") final long userId) {
        final ModelAndView mav = new ModelAndView("index");
        mav.addObject("user", userService.getUserById(userId).orElseThrow(UserNotFoundException::new));
        return mav;
    }

    @RequestMapping(value = "/profile/{userId}", method = { RequestMethod.GET, RequestMethod.HEAD })
    public ModelAndView userProfile(@PathVariable("userId") final long userId) {
        final ModelAndView mav = new ModelAndView("index");
        mav.addObject("user", userService.getUserById(userId).orElseThrow(UserNotFoundException::new));
        return mav;
    }

    @RequestMapping(value = "/create", method = { RequestMethod.GET })
    public ModelAndView createForm(@Valid @ModelAttribute("registerForm") final UserForm form) {
        return new ModelAndView("helloworld/register");
    }

    @RequestMapping(value = "/create", method = { RequestMethod.POST })
    public ModelAndView create(@Valid @ModelAttribute("registerForm") final UserForm form, BindingResult errors) {
        if (errors.hasErrors()) {
            return createForm(form);
        }

        final User user = userService.create(form.getUsername(), form.getPassword());
        return new ModelAndView("redirect:/profile/" + user.getId());
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public ModelAndView handleUserNotFoundException() {
        return new ModelAndView("error/404");
    }


    @RequestMapping(value = "/login", method = { RequestMethod.GET })
    public ModelAndView loginForm() {
        return new ModelAndView("helloworld/login");
    }
}
