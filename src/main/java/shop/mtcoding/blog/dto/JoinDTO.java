package shop.mtcoding.blog.dto;
//회원가입 정보를 디비에 넣어줘야한다

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

//회원가입 값을 가방을 준다
@Getter
@Setter
@AllArgsConstructor
public class JoinDTO {
    private String username;
    private String password;
    private String email;

}
