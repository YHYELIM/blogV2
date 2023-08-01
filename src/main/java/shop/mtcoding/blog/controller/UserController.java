package shop.mtcoding.blog.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import shop.mtcoding.blog.dto.JoinDTO;
import shop.mtcoding.blog.dto.LoginDTO;
import shop.mtcoding.blog.model.User;
import shop.mtcoding.blog.repository.UserRepository;

@Controller
public class UserController {

    @Autowired // 자동으로 이어준다
    private UserRepository userRepository;

    @Autowired
    private HttpSession httpSession;

    @GetMapping("/joinForm")
    public String join() {
        return "user/joinForm";
    }

    @PostMapping("/join")
    public String save(JoinDTO joinDTO) {
        // 유효성 검사
        if (joinDTO.getUsername() == null || joinDTO.getUsername().isEmpty()) {
            return "error/40x";
        }
        if (joinDTO.getPassword() == null || joinDTO.getPassword().isEmpty()) {
            return "error/40x";
        }
        if (joinDTO.getEmail() == null || joinDTO.getEmail().isEmpty()) {
            return "error/40x";
        }
        try {
            userRepository.save(joinDTO);
        } catch (Exception e) {
            return "error/50x";
        }

        return "redirect:/";
    }

    @GetMapping("/loginForm")
    public String loginForm() {
        return "user/loginForm";
    }

    @PostMapping("/login")
    public String login(LoginDTO loginDTO) {
        if (loginDTO.getUsername() == null || loginDTO.getUsername().isEmpty()) {
            return "error/40x";
        }
        if (loginDTO.getPassword() == null || loginDTO.getPassword().isEmpty()) {
            return "error/40x";
        }

        try {
            User user = (User) userRepository.login(loginDTO);
            httpSession.setAttribute("sessionUser", user);
            return "/";
        } catch (Exception e) {
            return "error/exLogin";

        }

    }

}
