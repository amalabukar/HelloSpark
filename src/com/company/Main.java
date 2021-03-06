package com.company;
import spark.Spark;
import spark.template.mustache.MustacheTemplateEngine;
import spark.ModelAndView;
import java.util.HashMap;


public class Main {
    public static User user;

    //    everything we want our page to do starts with main spark.init gets the page ready, spark to initialize
//    get and post most important posts=new forms get=full pages
    public static void main(String[] args) {
        Spark.init();

        Spark.get(
                "/",
                ((request, response) -> {
                    HashMap m = new HashMap();
                    if(user ==null) {
                        return new ModelAndView(m, "login.html");
                    } else {
                        m.put("name", user.name);
                        return new ModelAndView(m, "home.html");
                    }

                }),
                new MustacheTemplateEngine()
        );

        Spark.post (
                "/login",
                ((request, response) -> {
                    String name= request.queryParams("loginName");
                    user = new User(name);
                    response.redirect("/");
                    return " ";

                })

        );
        Spark.post (
                "/logout",
                ((request, response) -> {
                    user = null;
                    response.redirect("/");
                    return "";

                })

        );
    }
}
