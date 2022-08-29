package tamna6.tfm2022.dto;

import lombok.AllArgsConstructor;
import lombok.ToString;
import tamna6.tfm2022.entity.Team;

@AllArgsConstructor
@ToString
public class TeamForm {
    private Long tno;
    private String tname;
    private String logo;
    private String history;
    private String alias;
    private String stadium;
    private String trophy;

    public Team toEntity(){
        return new Team(tno, tname, logo, history, alias, stadium, trophy);
    }

}
