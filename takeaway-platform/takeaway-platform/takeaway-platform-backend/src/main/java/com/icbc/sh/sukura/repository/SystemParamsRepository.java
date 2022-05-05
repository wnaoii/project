package com.icbc.sh.sukura.repository;

import com.icbc.sh.sukura.entity.ImageEntity;
import com.icbc.sh.sukura.entity.SystemParamsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SystemParamsRepository extends JpaRepository<SystemParamsEntity, Integer>, JpaSpecificationExecutor<SystemParamsEntity> {
}
