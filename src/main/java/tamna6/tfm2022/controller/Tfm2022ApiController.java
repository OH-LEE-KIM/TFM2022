package tamna6.tfm2022.controller;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class Tfm2022ApiController {

//    @Autowired
//    private PlayerRepository playerRepository;

//    @GetMapping("/detail/player")
//    public Model players(PlayerForm form, Model model){
//
//        Player playerList = form.toEntity();
//
//        Player playerEntity = playerRepository.findById(1L).orElse(null);
//        model.addAttribute("playerList", playerEntity);
//        log.info("test" + playerEntity.toString());
//        return model;
//    }

    @GetMapping("/login")
    @ApiOperation(value="로그인", notes="localhost:8080")
    public String tfmLogin(){
        return "/login";
    }
    @GetMapping("/logout")
    public String tfmLogout(){
        return "/login";
    }

    @GetMapping("/login/error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }
}
