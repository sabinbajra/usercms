package Tetrad.CMS.project.repository;

import Tetrad.CMS.project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserRepository extends JpaRepository <User, Long> {
}
