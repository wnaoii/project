package com.icbc.sh.sukura.repository;

import com.icbc.sh.sukura.entity.ADEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface ADRepository extends JpaRepository<ADEntity, String>, JpaSpecificationExecutor<ADEntity> {
}
