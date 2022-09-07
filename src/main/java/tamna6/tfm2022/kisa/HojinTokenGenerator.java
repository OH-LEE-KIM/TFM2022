package tamna6.tfm2022.kisa;

import lombok.extern.slf4j.Slf4j;
import tamna6.tfm2022.dto.TfmAuthDto;

@Slf4j
public class HojinTokenGenerator {
    public String CreateToken(TfmAuthDto authDto) {

        //1. 토큰 내용 : ID + 로그인시간
        String readyToken = authDto.getID() + authDto.getLastlogin();
        log.info("readyToken : " + readyToken);

        //2. 국산 대칭키 암호 SEED 암호화
        Seed seed = new Seed();
        String hojinToken = seed.seedEncrypt(readyToken);
        log.info("hojinToken : " + hojinToken);
        
        //3. 토큰 반환
        return hojinToken;
    }
}
