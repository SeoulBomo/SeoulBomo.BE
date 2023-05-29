package SeoulBomo.SeoulBomoBe.common;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class helloController {
    @GetMapping("/health")
    public String health() {
        return "health";
    }
}
