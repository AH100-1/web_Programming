package Shopping.web.newEntity;

import Shopping.web.newEntity.MemberData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberDataRepository extends JpaRepository<MemberData, Long> {

    MemberData findById(String id);

}