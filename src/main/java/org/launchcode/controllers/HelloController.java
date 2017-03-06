package org.launchcode.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.RequestWrapper;

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
                "<option value='english'>English</option>" +
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
    public String createMessage(HttpServletRequest request){
        String name = request.getParameter("name");
        String language = request.getParameter("language");
        return "<span style='color:red'>" + language + " " + name + "</span>";
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
