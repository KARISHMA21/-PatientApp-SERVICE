package com.patient_service.repository;

import com.patient_service.bean.entity.PatientEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;


@Repository
public interface PatientRepository extends JpaRepository<PatientEntity,String> {
    Optional<PatientEntity> findByUsername(String username);

    @Modifying
    @Query("UPDATE PatientEntity cl SET " +
            "cl.name=:Name  ,cl.phone=:Phone ,cl.email=:Email  " +
            "WHERE " +
            "cl.pid=:pid")
    public void updateProfile(
            @Param("pid") String pid,
            @Param("Name") String Name,
            @Param("Phone") BigInteger Phone,
            @Param("Email") String Email

    );

    @Query("SELECT p FROM PatientEntity p WHERE p.pid = :pid AND p.phone = :phone")
    Optional<PatientEntity> findByUsernameAndPhone(@Param("pid") String pid, @Param("phone") BigInteger phone);

    @Modifying
    @Query("UPDATE PatientEntity cl SET " +
            "cl.password=:password "+
            "WHERE " +
            "cl.pid=:pid")
    public void updatePassword(
            @Param("pid") String pid,
            @Param("password") String password
    );


}