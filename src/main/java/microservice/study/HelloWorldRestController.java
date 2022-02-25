package microservice.study;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/hello-world")
public class HelloWorldRestController {

    @GetMapping
    public String get() {
        return "Hello World";
    }
}
