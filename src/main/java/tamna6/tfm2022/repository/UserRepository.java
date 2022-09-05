package tamna6.tfm2022.repository;

import org.springframework.data.repository.CrudRepository;
import tamna6.tfm2022.entity.TfmUser;


public interface UserRepository extends CrudRepository<TfmUser, String> {


}
