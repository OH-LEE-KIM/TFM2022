package tamna6.tfm2022.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class TfmUser {

    @Id
    @Column(length = 20)
    private String id;
    @Column(nullable = false, length = 30)
    private String pw;
    @Column(nullable = false, length = 20)
    private String nick;
    @Column(nullable = false, length = 20)
    private String uname;
    @Column(nullable = false, length = 10)
    private String tamid;
    @Column(nullable = false, length = 2)
    private int gisu;
    @Column(nullable = false, length = 50)
    private String mail;
    @Column(length = 20)
    private Date lastlogin;
    @Column(length = 20)
    private Date lastlogout;
}
