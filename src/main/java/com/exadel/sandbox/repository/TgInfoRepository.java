package com.exadel.sandbox.repository;

import com.exadel.sandbox.entities.TgInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TgInfoRepository extends JpaRepository<TgInfo, Integer> {
}
