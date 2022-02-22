package com.exadel.sandbox.employee.repository;

import com.exadel.sandbox.employee.entity.TgInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TgInfoRepository extends JpaRepository<TgInfo, Long> {
}
