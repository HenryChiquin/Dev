package com.dxlatam.dashboard.domain.repository;

import com.dxlatam.dashboard.domain.entity.GetDashboard;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface GetDashboardRepository extends JpaRepository<GetDashboard,Integer> {
    boolean existsByCodigo(int codigo);
    Optional<GetDashboard> findByCodigo(int codigo);
}
