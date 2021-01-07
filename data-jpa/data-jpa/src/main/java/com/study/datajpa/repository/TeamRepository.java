package com.study.datajpa.repository;

import com.study.datajpa.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team,Long> {

}
