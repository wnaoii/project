package com.icbc.sh.sukura.repository;

import com.icbc.sh.sukura.entity.AddressEntity;
import com.icbc.sh.sukura.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AddressRepository extends JpaRepository<AddressEntity, String>, JpaSpecificationExecutor<AddressEntity> {
}
