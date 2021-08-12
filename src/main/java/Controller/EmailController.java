package Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class EmailController {
    private static final String emailRegex = "^[A-Za-z0-9]+[A-Za-z0-9]*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)$";
    private static Pattern pattern;
    private Matcher matcher;

    public EmailController() {
        pattern = Pattern.compile(emailRegex);
    }

    @GetMapping("/")
    public String getIndex() {
        return "index.jsp";
    }

    @PostMapping("/validate")
    public String validateEmail(@RequestParam String email, Model model){
        boolean isValid = validate(email);
        if(!isValid){
            model.addAttribute("message","Email invalid");
            return "index.jsp";
        }
        model.addAttribute("email",email);
        return "success.jsp";
    }
    private boolean validate(String regex){
        matcher = pattern.matcher(regex);
        return matcher.matches();
    }
}
