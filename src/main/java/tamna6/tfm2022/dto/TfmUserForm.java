package tamna6.tfm2022.dto;

import lombok.AllArgsConstructor;
import lombok.ToString;
import tamna6.tfm2022.entity.TfmUser;

@AllArgsConstructor
@ToString
public class TfmUserForm {
    private String id;
    private String pw;
    private String nick;
    private String uname;
    private String tamid;
    private int gisu;
    private String mail;

    public TfmUser toEntity(){
        return new TfmUser(id, pw, nick, uname, tamid, gisu, mail, null, null);
    }
}
