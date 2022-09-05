package tamna6.tfm2022.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import tamna6.tfm2022.entity.TfmUser;

import java.util.Optional;

public interface UserRepository extends CrudRepository<TfmUser, String> {

    //findBy(규칙) -> Username(문법)
    // select * from TfmUser where id = ?


    //TfmUser findById(String username);
    //public static TfmUser findById(String username);
}
