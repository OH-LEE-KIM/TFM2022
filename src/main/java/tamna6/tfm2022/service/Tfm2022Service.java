package tamna6.tfm2022.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tamna6.tfm2022.entity.Player;
import tamna6.tfm2022.entity.Team;
import tamna6.tfm2022.repository.PlayerRepository;
import tamna6.tfm2022.repository.TeamRepository;
import tamna6.tfm2022.repository.Tfm2022Repository;

import java.util.List;

@Service
@Slf4j
public class Tfm2022Service {
    @Autowired
    private Tfm2022Repository tfm2022Repository;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private PlayerRepository playerRepository;


    public List<Team> teamlist() {
        return teamRepository.findAll();
    }

    public List<Player> plyaerlist(Long tno) {
        List<Player> players = playerRepository.findByPlayer(tno);

        System.out.println("zidh" + players.toString());
        return players;
    }
}
