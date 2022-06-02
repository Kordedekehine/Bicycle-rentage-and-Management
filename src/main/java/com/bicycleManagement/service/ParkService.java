package com.bicycleManagement.service;

import com.bicycleManagement.model.Park;
import com.bicycleManagement.repository.ParkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkService {

    @Autowired
    ParkRepository parkRepository;

public List<Park> findAll(){
    return parkRepository.findAll();
}

public boolean existById(Integer id){
    return parkRepository.existsById(id);
}
}
