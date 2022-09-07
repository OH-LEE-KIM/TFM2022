package tamna6.tfm2022.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tamna6.tfm2022.entity.TfmUser;
import tamna6.tfm2022.repository.UserRepository;

//시큐리티 설정에서 loginProcessingUrl.loginProcessingUrl("/login_pro");
//login 요청이 오면 자동으로 UserDetailsService 타입으로 IoC 되어있는 loadUserByUsername 함수가 실행
@Service
public class PrincipalDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    //시큐리티 sesssion (내부 Authentication(내부 UsrDetails))
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("username : "+username);
        TfmUser userEntity = userRepository.findById(username).orElse(null);
        if(userEntity != null){

            return new PrincipalDetails(userEntity); // Authentication 내부에 들어감
        }
        return null;
    }
}
