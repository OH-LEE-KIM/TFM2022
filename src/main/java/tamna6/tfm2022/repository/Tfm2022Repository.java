package tamna6.tfm2022.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import tamna6.tfm2022.entity.TfmUser;

import java.util.List;

public interface Tfm2022Repository extends CrudRepository<TfmUser, String> {

}
