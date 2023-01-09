package com.freshersProject.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import com.freshersProject.ecommerce.model.Coupons;

@Repository
@EnableJpaRepositories
public interface CouponsRepository extends JpaRepository<Coupons,Integer> {

	Coupons findById(int couponId);


}
