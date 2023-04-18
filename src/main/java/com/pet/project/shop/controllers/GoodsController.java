package com.pet.project.shop.controllers;

import com.pet.project.shop.models.Good;
import com.pet.project.shop.services.GoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class GoodsController {

    private final GoodService goodService;

    @GetMapping("/goods")
    public List<Good> getAllGoods(){
        return goodService.allGoods();
    }

    @GetMapping("/goods/{id}")
    public Good getOneGoods(@PathVariable Integer id){
        return goodService.oneGood(id);
    }

    @PostMapping("/goods/save")
    public ResponseEntity<Good> saveNewGood(@RequestBody Good good){
        goodService.saveNewGood(good);
        return ResponseEntity.ok(good);
    }
}
