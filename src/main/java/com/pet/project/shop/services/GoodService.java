package com.pet.project.shop.services;

import com.pet.project.shop.models.Good;
import com.pet.project.shop.repositories.GoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GoodService {

    private final GoodRepository goodRepository;

    public List<Good> allGoods(){
        return goodRepository.findAll();
    }

    public Good oneGood(Integer id){
        return goodRepository.findById(id).orElseThrow();
    }

    @Transactional
    public void saveNewGood(Good good){
        goodRepository.save(good);
    }
}
