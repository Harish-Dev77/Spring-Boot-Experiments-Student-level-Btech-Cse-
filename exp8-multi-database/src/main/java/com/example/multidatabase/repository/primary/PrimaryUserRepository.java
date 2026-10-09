package com.example.multidatabase.repository.primary;

import com.example.multidatabase.model.primary.PrimaryUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrimaryUserRepository extends JpaRepository<PrimaryUser, Long> {
}
