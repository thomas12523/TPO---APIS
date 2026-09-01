package com.uade.tpo.marketplace.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.uade.tpo.marketplace.entity.Category;
@Repository
public interface ICategoriesRepository extends JpaRepository<Category, Integer> {

    @Query(value = "select c from Category c where c.nombre = ?1 and c.activo = true")
    List<Category> findByNombre(String nombre);

    Page<Category> findByActivoTrue(Pageable pageable);
}
