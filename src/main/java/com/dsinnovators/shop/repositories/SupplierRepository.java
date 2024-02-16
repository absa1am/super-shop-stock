package com.dsinnovators.shop.repositories;

import com.dsinnovators.shop.entities.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    List<Supplier> findAllByIsDeletedIsFalse();
    Page<Supplier> findAllByIsDeletedIsFalse(Pageable pageable);

}
