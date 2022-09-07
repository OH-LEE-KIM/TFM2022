package tamna6.tfm2022.controller;

import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tamna6.tfm2022.dto.TeamDto;
import tamna6.tfm2022.dto.TfmUserDto;
import tamna6.tfm2022.entity.Player;
import tamna6.tfm2022.entity.Team;
import tamna6.tfm2022.service.Tfm2022Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@RestController
@Slf4j
public class Tfm2022ApiController {
    @Autowired
    private Tfm2022Service tfm2022Service;

    @GetMapping("/api/teamlist")
    public List<Team> teamlist() {
        return tfm2022Service.teamlist();
    }

    @PostMapping("/api/teamdetail")
    public ResponseEntity<List<Player>> teamdetail(@RequestBody TeamDto teamDto) {
        Long tno = teamDto.getTno();
        List<Player> players = tfm2022Service.plyaerlist(tno);

        return ResponseEntity.status(HttpStatus.OK).body(players);
    }


    @PostMapping("/api/loginCheck")
    public ResponseEntity<JSONObject> loginCheck(@RequestBody TfmUserDto dto) {
        String CheckLogin = "";

        //1. 1차검증 - ID 및 PW 검사
        String CheckId = tfm2022Service.CheckIdAndPw(dto);
        log.info("CheckId : " + CheckId);

        //2. 2차검증 - 1차검증 이상 없을 경우 진행]]]]]]]]]]]]]]]]
        if (CheckId.contains("Error") != true) { //1차검증 후 Error문자열이 없으면 2차검증 진행
            log.info("2차검증 들어옴");
            CheckLogin = tfm2022Service.CheckLogin(dto);
        } //else 도 만들자]]]]]]]]]]]]]]]]]

        JSONObject json = new JSONObject();
        //json.put("idp", dto.getId()); //(보안)혹시 모를 사태에 대비해 DB값 전달하지 ㅇ낳음
        //json.put("pwo", dto.getPw());
        json.put("HojinToken", CheckLogin);

        log.info("json : " + json);

//        "{\"test\":\"wow\"}"
        return (CheckId.contains("Error") != true) ? //CheckId가 Error문자열 포함하지 않다면
                ResponseEntity.status(HttpStatus.OK).body(json) : //참이면 여기 코드 실행
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); //거짓이면 여기 코드 실행
    }

    @PostMapping("/api/signup")
    public ResponseEntity<String> signup(@RequestBody TfmUserDto dto) {

        //웨이터가 주방장에 주문내용 전달
        String result = tfm2022Service.signup(dto);
        log.info("test : " + result);
        return (result == "OK") ? //created가 null이 아니라면
                ResponseEntity.status(HttpStatus.OK).body(result) : //참이면 여기 코드 실행
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); //거짓이면 여기 코드 실행

    }
}
