package tamna6.tfm2022.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tamna6.tfm2022.dto.TfmUserDto;
import tamna6.tfm2022.entity.Player;
import tamna6.tfm2022.entity.Team;
import tamna6.tfm2022.entity.TfmUser;
import tamna6.tfm2022.repository.PlayerRepository;
import tamna6.tfm2022.repository.TeamRepository;
import tamna6.tfm2022.repository.TfmUserRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
public class Tfm2022Service {
    @Autowired
    private TfmUserRepository tfmUserRepository;
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

    @Transactional
    public String signup(TfmUserDto dto) {

       //ID중복 검사
       TfmUser tfmUser = tfmUserRepository.findById(dto.getId()).orElseGet(()->null);
       log.info("비번 : " + dto.getPw());

       dto.setPw("{noop}" + dto.getPw());
       log.info("dto : " + dto);

       //log.info("tfmUser : " + String.valueOf(tfmUser));

        String res = "";
        if(tfmUser != null) {  //null이라면 중복된 계정명이 있다는 것
            //log.info("noo:" + tfmUser);
            res = "Fail";}
        else if (tfmUser == null) { //null이라면 계정등록해도 괜찮
            //log.info("ok : " + tfmUser);
            tfmUser = dto.toEntity();
            //log.info("ok2 : " + tfmUser);
            tfmUser = tfmUserRepository.save(tfmUser);
            res = "OK";
        }

       return res;
    }
}
