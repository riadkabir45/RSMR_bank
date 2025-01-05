package com.bracu.rsmr.CashPackage;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CashPackageRepository extends JpaRepository<CashPackage,Long> {
    List<CashPackage> findByPackageType(CashPackageType type);
}
