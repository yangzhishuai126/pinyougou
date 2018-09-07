package com.pinyougou.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.pojo.TbBrand;
import com.pinyougou.pojo.TbSpecification;
import com.pinyougou.sellergoods.service.SpecService;
import entity.PageResult;
import entity.Result;
import entity.Specification;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by 20160816-PC on 2018/9/5.
 */

@RestController
@RequestMapping("/spec")
public class SpecController {

    @Reference
    private SpecService SpecService;



    @RequestMapping("/findAll")
    public List<TbSpecification> findAll() {

        return SpecService.findAll();
    }


    @RequestMapping("/findPage")
    public PageResult findPage(int page, int rows) {

        return SpecService.findPage(page, rows);
    }


    @RequestMapping("/search")
    public PageResult search(@RequestBody TbSpecification TbSpecification, int page, int rows  ){
        return SpecService.findPage(TbSpecification, page, rows);
    }

    @RequestMapping("/add")
    public Result add(@RequestBody Specification specification){
        try {
            SpecService.add(specification);
            return new Result(true, "增加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "增加失败");
        }
    }

    @RequestMapping("/findOne")
    public Specification findOne(Long id){
        return SpecService.findOne(id);
    }


    @RequestMapping("/update")
    public Result update(@RequestBody Specification specification){
        try {
            SpecService.update(specification);
            return new Result(true, "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "修改失败");
        }
    }


    @RequestMapping("/delete")
    public Result delete(Long [] ids){
        try {
            SpecService.delete(ids);
            return new Result(true,"");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"");
        }
    }




}
