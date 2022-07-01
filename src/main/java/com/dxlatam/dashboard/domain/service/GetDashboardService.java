package com.dxlatam.dashboard.domain.service;

import com.dxlatam.dashboard.domain.entity.GetDashboard;
import com.dxlatam.dashboard.domain.repository.GetDashboardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class GetDashboardService {

    @Autowired
    GetDashboardRepository getDashboardRepository;

    public List<GetDashboard> list(){return getDashboardRepository.findAll();}
    public void save(GetDashboard getDashboard){getDashboardRepository.save(getDashboard);}

    public boolean existsByCodigo(int codigo){return getDashboardRepository.existsByCodigo(codigo);}
    public Optional<GetDashboard> findByCodigo(int codigo){return getDashboardRepository.findByCodigo(codigo);};

    public Optional<GetDashboard> getOne(int id){return getDashboardRepository.findById(id);}
}
