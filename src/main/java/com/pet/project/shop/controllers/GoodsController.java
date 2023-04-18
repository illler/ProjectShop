package com.pet.project.shop.controllers;

import com.pet.project.shop.exception.GoodNotFoundException;
import com.pet.project.shop.models.Good;
import com.pet.project.shop.services.GoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
@RestController
@RequiredArgsConstructor
public class GoodsController {

    private final GoodService goodService;

    @GetMapping("/goods")
    public List<EntityModel<Good>> getAllGoods(){
        List<Good> goodList = goodService.allGoods();
        List<EntityModel<Good>> entityModels = new ArrayList<>();
        for (int i = 0; i < goodList.size(); i++) {
            EntityModel<Good> entityModel = EntityModel.of(goodList.get(i));
            WebMvcLinkBuilder linkBuilder = linkTo(methodOn(this.getClass()).getOneGoods(i+1));
            entityModel.add(linkBuilder.withRel("current-good"));
            entityModels.add(entityModel);
        }
        return entityModels;
    }

    @GetMapping("/goods/{id}")
    public EntityModel<Good> getOneGoods(@PathVariable Integer id){
        Good good = goodService.oneGood(id);
        if (good==null){
            throw new GoodNotFoundException("id: " + id);
        }

        EntityModel<Good> entityModel = EntityModel.of(good);

        WebMvcLinkBuilder link =linkTo(methodOn(this.getClass()).getAllGoods());
        entityModel.add(link.withRel("all-goods"));
        return entityModel;
    }

    @PostMapping("/goods/save")
    public ResponseEntity<Good> saveNewGood(@RequestBody Good good){
        goodService.saveNewGood(good);
        return ResponseEntity.ok(good);
    }
}
