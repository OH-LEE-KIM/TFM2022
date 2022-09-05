package tamna6.tfm2022.config.auth;

//시큐리티가 /login 주소 요청이 오면 낚아채서 로그인 진행시킴
//로그인 진행이 완료 되면 시큐리티 session을 만들어줌 (Security ContextHolder)
// 오브젝트 => Authentication 타입 객체
//Authentication 안에 User정보가 있어야함
//User오브젝트타입 => UserDetails 타입 객체

//Security Session => Authentication => UserDetails(PrincipalDetails)

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import tamna6.tfm2022.entity.TfmUser;

import java.util.ArrayList;
import java.util.Collection;

public class PrincipalDetails implements UserDetails {

    private TfmUser user;

    public PrincipalDetails(TfmUser user) {
        this.user = user;
    }

    //해당 User의 권한을 리턴하는 곳
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collect = new ArrayList<>();
        collect.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return null;//user.getRole() 권한 리턴
            }
        });
        return null;
    }

    @Override
    public String getPassword() {
        return user.getPw();
    }

    @Override
    public String getUsername() {
        return user.getId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
