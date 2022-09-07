package tamna6.tfm2022.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tamna6.tfm2022.entity.TfmUser;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

public interface TfmUserRepository extends JpaRepository<TfmUser, String> {

    @Query(value =
            "SELECT id FROM tfm_user WHERE id = :dtoId",
            nativeQuery = true)
    String findByUserId(@Param("dtoId") String dtoId);

    @Query(value =
            "SELECT pw FROM tfm_user WHERE pw = :dtoPw",
            nativeQuery = true)
    String findByPw(@Param("dtoPw") String dtoPw);

    //현재로그인시간 > 마지막로그아웃시간이어야 로그인 가능
    //최초 로그인은 ifnull로 인핸 결과값 출력 가능
//    @Query(value =
//            "SELECT id FROM tfm_user" +
//                    "WHERE id = :dtoId " +
//                    "AND IFNULL(lastlogout, 0) < :dtoLastlogin",
//            nativeQuery = true)
//    String checkLastlogin(@Param("dtoId") String dtoId,
//                          @Param("dtoLastlogin") String dtoLastlogin);

}
