package com.patient_service.security.repository;


import java.util.List;
import java.util.Optional;

import com.patient_service.security.entity.Token;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface TokenRepository extends CrudRepository<Token, Integer> {

    @Query(value = """
      select t from Token t inner join PatientEntity u\s
      on t.user.pid = u.pid\s
      where u.pid = :id and (t.expired = false or t.revoked = false)\s
      """)
    List<Token> findAllValidTokenByUser(String id);

    Optional<Token> findByToken(String token);
    @Transactional
    @Modifying
    @Query("delete  from Token t where t.expired = true and  t.revoked = true")
    void deleteTokenByExpiredAndRevoked();


}
