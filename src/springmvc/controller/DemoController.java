package springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import springmvc.model.DemoModel;

/**
 * Created by scnzhg on 5/29/2015.
 */
@Controller
public class DemoController {
    @RequestMapping(value = "/model/{modelId}", method = RequestMethod.GET)
    public @ResponseBody DemoModel getModels(@PathVariable("modelId") String modelId){
        System.out.println("enter the method");
        DemoModel model =new DemoModel();
        model.setName("123");
        model.setDescription("desc");
        return model;
    }
}
