package com.exadel.hotdesk.employee.repository;

import com.exadel.hotdesk.employee.entity.TgInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TgInfoRepository extends JpaRepository<TgInfo, Long> {
}
