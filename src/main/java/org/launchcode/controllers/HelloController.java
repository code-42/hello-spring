package org.launchcode.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;
import java.lang.Long;
import java.util.function.LongFunction;


/**
 * Created by Edward Dupre on 3/5/17.
 */
@Controller
public class HelloController {

    @RequestMapping(value = "")
    @ResponseBody
    public String index(HttpServletRequest request){
        String name = request.getParameter("name");

        if (name == null){  // name.equals(null) throws error and status=500
            name = "World";
        }
        return "Hello " + name;
    }

    @RequestMapping(value = "hello", method=RequestMethod.GET)
    @ResponseBody
    public String helloForm(){
        String html = "<form method='post'>" +
                "<input type='text' name='name' />" +
                "<select name='language'>" +
                "<option value='Hello'>English</option>" +
                "<option value='Bonjour'>French</option>" +
                "<option value='Hola'>Spanish</option>" +
                "<option value='Hallo'>German</option>" +
                "<option value='Ciao'>Italian</option>" +
                "<input type='submit' value='Greet me' />" +
                "</form>";

        return html;
    }


    @RequestMapping(value = "hello", method=RequestMethod.POST)
    @ResponseBody
    public String helloMessage(HttpServletRequest request){
        String name = request.getParameter("name");
        String language = request.getParameter("language");
        return "<span style='color:red'>" + language + " " + name + "</span>" +
                " for the nth time. ";  // " + writeCookie() + "
    }


    // viralpatel.net/blogs/spring-mvc-cookie-example
    @RequestMapping(value = "hello/cookie")
    @ResponseBody
    private static String writeCookie(@CookieValue(value = "hitCounter", defaultValue = "0") Long hitCounter, HttpServletResponse response){
        hitCounter++;
        Cookie cookie = new Cookie("hitCounter", hitCounter.toString());
        response.addCookie(cookie);
        System.out.println("cookie.getValue() = " + cookie.getValue());
        return cookie.getValue().toString();
    }


     @RequestMapping(value = "hello/{name}")
     @ResponseBody
     public String helloUrlSegment(@PathVariable String name){
     // String name = request.getParameter("name");
     // String language = request.getParameter("language");
     // return language + " " + name;
        return "Hello " + name;
     }


    @RequestMapping(value = "goodbye")
    //@ResponseBody
    public String goodbye(){
        return "redirect:/";
    }

}