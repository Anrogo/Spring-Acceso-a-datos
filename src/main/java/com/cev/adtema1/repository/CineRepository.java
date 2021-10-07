package com.cev.adtema1.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cev.adtema1.domain.Cine;

@Repository
public interface CineRepository extends JpaRepository<Cine, Long> {

}
