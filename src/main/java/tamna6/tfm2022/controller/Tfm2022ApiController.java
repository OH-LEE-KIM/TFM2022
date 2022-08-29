package tamna6.tfm2022.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tamna6.tfm2022.dto.TeamForm;
import tamna6.tfm2022.entity.Player;
import tamna6.tfm2022.entity.Team;
import tamna6.tfm2022.service.Tfm2022Service;

import java.util.List;

@RestController
@Slf4j
public class Tfm2022ApiController {
    @Autowired
    private Tfm2022Service tfm2022Service;

    @GetMapping("/api/teamlist")
    public List<Team> teamlist(){
        return tfm2022Service.teamlist();
    }

    @PostMapping("/api/teamdetail")
    public ResponseEntity<List<Player>> teamdetail(@RequestBody TeamForm teamDto){
        Long tno = teamDto.getTno();
        List<Player> players = tfm2022Service.plyaerlist(tno);

        return ResponseEntity.status(HttpStatus.OK).body(players);
    }
}
