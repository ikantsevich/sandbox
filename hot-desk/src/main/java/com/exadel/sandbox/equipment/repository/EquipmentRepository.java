package com.exadel.sandbox.equipment.repository;

import com.exadel.sandbox.equipment.entity.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
    List<Equipment> findEquipmentBySeatId(Long id);
}