package ru.atas.TRPfinder.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestAPI {
    @GetMapping("/test/hello")
    public String getTest(){

        System.out.println("test");
        return "Seems working";
    }

    @GetMapping("/test/{num}")
    public int getNumericTest(@PathVariable int num){
        return num;
    }

    @GetMapping("/test/string/{str}")
    public String getBreakNumeric(@PathVariable String str){
        return (str + " ahahahaha");
    }
}
