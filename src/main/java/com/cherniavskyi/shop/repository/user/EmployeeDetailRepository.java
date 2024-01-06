package com.cherniavskyi.shop.repository.user;

import com.cherniavskyi.shop.entity.user.employee.EmployeeDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeDetailRepository extends JpaRepository<EmployeeDetail, Long> {
}
