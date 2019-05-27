package com.example.demo.service.impl;

import com.example.demo.dto.DashboardDto;
import com.example.demo.model.Dashboard;
import com.example.demo.repository.DashboardRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashboardServiceImpl implements DashboardService {

    private DashboardRepository repository;
    private UserRepository userRepository;

    @Autowired
    public DashboardServiceImpl(DashboardRepository repository,
                                UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    @Override
    public Dashboard create(Dashboard dashboard) {
        if(!repository.existsByName(dashboard.getName())){
            dashboard.setUser(userRepository.findUserById(dashboard.getUser().getId()));
            return repository.save(dashboard);
        }else {
            return null;
        }
    }

    @Override
    public Dashboard getDashboardById(String id) {
        return repository.findDashboardById(id);
    }

    @Override
    public Dashboard update(Dashboard dashboard) {
        return repository.save(dashboard);
    }

    @Override
    public List<Dashboard> getAllDashboards() {
        return (List<Dashboard>) repository.findAll();
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }

    @Override
    public List<Dashboard> getAllDashboardByUserId(String id) {
        return repository.findAllByUser_Id(id);
    }
}
