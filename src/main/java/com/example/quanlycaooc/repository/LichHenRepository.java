package com.example.quanlycaooc.repository;

import com.example.quanlycaooc.model.LichHen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LichHenRepository extends JpaRepository<LichHen, Integer> {
}