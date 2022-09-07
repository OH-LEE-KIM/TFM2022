package tamna6.tfm2022.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tamna6.tfm2022.dto.TfmAuthDto;
import tamna6.tfm2022.dto.TfmUserDto;
import tamna6.tfm2022.entity.Player;
import tamna6.tfm2022.entity.Team;
import tamna6.tfm2022.entity.TfmAuth;
import tamna6.tfm2022.entity.TfmUser;
import tamna6.tfm2022.kisa.HojinTokenGenerator;
import tamna6.tfm2022.repository.PlayerRepository;
import tamna6.tfm2022.repository.TeamRepository;
import tamna6.tfm2022.repository.TfmAuthRepository;
import tamna6.tfm2022.repository.TfmUserRepository;

import javax.transaction.Transactional;

import java.time.LocalDateTime;
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

    @Autowired
    private TfmAuthRepository tfmAuthRepository;


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
        TfmUser tfmUser = tfmUserRepository.findById(dto.getId()).orElseGet(() -> null);
        log.info("비번 : " + dto.getPw());

        dto.setPw("{noop}" + dto.getPw());
        log.info("dto : " + dto);

        LocalDateTime nowTime = LocalDateTime.now();
        log.info("nowTime : " + nowTime);

        dto.setCreatedate(nowTime);
        log.info("date작업한 dto" + dto);

        //log.info("tfmUser : " + String.valueOf(tfmUser));

        String res = "";
        if (tfmUser != null) {  //null이라면 중복된 계정명이 있다는 것
            //log.info("noo:" + tfmUser);
            res = "Fail";
        } else if (tfmUser == null) { //null이라면 계정등록해도 괜찮
            //log.info("ok : " + tfmUser);
            tfmUser = dto.toEntity();
            //log.info("ok2 : " + tfmUser);
            tfmUser = tfmUserRepository.save(tfmUser);
            res = "OK";
        }

        return res;
    }

    //1차 검증 - ID/PW 조작 여부 체크
    public String CheckIdAndPw(TfmUserDto dto) {
        String result = "";

        if (dto.getId() != null && dto.getPw() != null) {
            //ID 체크
            String selectId = tfmUserRepository.findByUserId(dto.getId()); //id 조회값
            //PW 체크
            String noopPw = "{noop}" + dto.getPw(); //예진님 양념, 해킹 어렵게
            String selectPw = tfmUserRepository.findByPw(noopPw); //pw 조회값

//            log.info("selectId : " + selectId);
//            log.info("dto.getId(): " + dto.getId());
//            log.info("noopPw : " + noopPw.length());
//            log.info("selectPw : " + selectPw.length());

            //ID 및 PW 입력값과 DB값 비교
            if (selectId.equals(dto.getId()) && selectPw.equals(noopPw)) {  //=는 주소값을 비교하고, equals 메소드는 값 자체를 비교
                log.info("이상없음");
                result = selectId;
            } else {
                log.info("이상있음111");
                result = "Error1";
            }
        } else {
            log.info("이상있음222");
            result = "Error1";
        }

        return result;
    }

    public String CheckLogin(TfmUserDto dto) {
        String hojinToken = "";
        LocalDateTime nowTime = LocalDateTime.now();
        HojinTokenGenerator hojinTokenGenerator = new HojinTokenGenerator(); //토큰생성용

        TfmAuth CheckAuthId = tfmAuthRepository.findById(dto.getId()).orElse(null);
        log.info("CheckAuthId : " + CheckAuthId);

        //1. 토큰 생성 후 저장 - 최초 로그인, 중간 로그인 => (로그아웃값 < 로그인값) 조건 달성
        if (CheckAuthId == null) {
            //1-1. 저장할 Dto 준비
            TfmAuthDto authDto = new TfmAuthDto(
                    dto.getId(),
                    null,
                    nowTime,
                    null
            );

            log.info("CheckAuthId null authDto 1 : " + authDto);
            //1-2. 토큰 생성
            hojinToken = hojinTokenGenerator.CreateToken(authDto);
            //1-3. 토큰 세팅
            authDto.setToken(hojinToken);
            //1-4. authDto -> Entity변환
            TfmAuth tfmAuth = authDto.toEntity();
            //1-5. tfm_auth DB저장
            tfmAuthRepository.save(tfmAuth);
        } else if (CheckAuthId != null) {
            //A-1. 저장할 Dto 준비
            TfmAuthDto authDto = new TfmAuthDto(
                    dto.getId(),
                    null,
                    nowTime,
                    CheckAuthId.getLastlogout()
            );
            log.info("CheckAuthId not null authDto 1 : " + authDto);
            //A-2. 토큰 생성
            hojinToken = hojinTokenGenerator.CreateToken(authDto);
            //A-3. 토큰 세팅
            authDto.setToken(hojinToken);
            //A-4. authDto -> Entity변환
            TfmAuth tfmAuth = authDto.toEntity();
            //A-5. tfm_auth DB저장
            tfmAuthRepository.save(tfmAuth);
        }

        log.info("hojinToken : " + hojinToken);

        //2. 토큰 생성


        //함수
        //  authDto.setToken(encryptedMessage);

//            //encryptedMessage = seed.seedDecrypt(encryptedMessage);
//            log.info("encryptedMessage 디코딩 : " + encryptedMessage);
//            log.info("test : " + String.valueOf(encryptedMessage.equals(authDto.getLastlogin())));

//            //3. Dto -> Entity
//            TfmAuth tfmAuth = authDto.toEntity();
//
//            //4. 인증정보 저장
//            tfmAuth = tfmAuthRepository.save(tfmAuth);
//            log.info("tfmAuth : " + tfmAuth);
//            log.info("tfmAuth 토큰값: " + tfmAuth.getToken());
//
//            //String tnowTime = nowTime.format(dateTimeFormatter);
//            //nowTime = LocalDateTime.parse(nowTime.format(dateTimeFormatter));
//            if (tfmAuth.getLastlogin().equals(nowTime)){
//                log.info("시간 참 tfmAuth.getLastlogin() : " + tfmAuth.getLastlogin());
//                log.info("nowTime 참 : " + nowTime);
//                log.info("tfmAuth.getLastlogin() 길이 : " + tfmAuth.getLastlogin().toString().length());
//                log.info("nowTime 길이 : " + nowTime.toString().length());
////                log.info("encryptedMessage 길이 : " + String.valueOf(encryptedMessage.length()));
////
////                //가져가고
////                log.info("자르기 테스트 : " + encryptedMessage.substring(encryptedMessage.length() -29, encryptedMessage.length()));
////
//
//
//            } else {
//                log.info("시간 거짓 tfmAuth.getLastlogin() : " + tfmAuth.getLastlogin());
//                log.info("tnowTime 거짓 : " + nowTime);
//            }
//            }


        //authDto.setID(dto.getId());
        //authDto.setLastlogin(nowTime);

//        TfmUser tfmUser = tfmUserRepository.findById(dto.getId()).orElseGet(()->null);
//        log.info("authDto 값 : " + authDto);

//        tfmUser = dto.toEntity();
//        authDto tempAuthDto = tfmUserRepository.findById(dto.getId()).orElse(null);

        //    String selectId = tfmUserRepository.checkLastlogin(dto.getId(), LocalDateTime.now());
//            if (selectId != null){
        return hojinToken;
    }
}
