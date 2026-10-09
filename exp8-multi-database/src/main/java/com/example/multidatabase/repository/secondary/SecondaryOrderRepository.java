package com.example.multidatabase.repository.secondary;

import com.example.multidatabase.model.secondary.SecondaryOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SecondaryOrderRepository extends JpaRepository<SecondaryOrder, Long> {
}
