package com.example.javaweb_be.repository;

import com.example.javaweb_be.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {

    List<Bill> findBillByAccount_Id(Long id);
}
