package shop.mtcoding.blog.repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import shop.mtcoding.blog.dto.JoinDTO;
import shop.mtcoding.blog.dto.LoginDTO;
import shop.mtcoding.blog.model.User;

//가방 안에 담은걸 어디다 놔둬??
@Repository
public class UserRepository {

    @Autowired
    private EntityManager em;
    // getinstance

    @Transactional // 이걸 해줘야 db까지 가서 롤백, 커밋
    public void save(JoinDTO joinDTO) {
        Query query = em.createNativeQuery(
                "insert into user_tb (username, password, email) values(:username, :password, :email)");// 값을 받아와서
        query.setParameter("username", joinDTO.getUsername());// "username"(담는 곳)=:username 원하는 값을 꺼낼때도 dto를 사용한다
        query.setParameter("password", joinDTO.getPassword());
        query.setParameter("email", joinDTO.getEmail());

        query.executeUpdate();

    }

    public User login(LoginDTO loginDTO) {
        Query query = em.createNativeQuery("select * from user_tb where username=:username and password=:password");
        query.setParameter("username", loginDTO.getUsername());
        query.setParameter("password", loginDTO.getPassword());

        return (User) query.getSingleResult();

    }

}
