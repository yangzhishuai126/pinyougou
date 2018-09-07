package com.pinyougou.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.pojo.TbSpecification;
import com.pinyougou.pojo.TbTypeTemplate;
import com.pinyougou.pojo.TbTypeTemplateExample;
import com.pinyougou.sellergoods.service.TypeTemplateService;
import entity.PageResult;
import entity.Result;
import entity.Specification;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created by 20160816-PC on 2018/9/6.
 */
@RestController
@RequestMapping("/Type")
public class typeTemplateController {

        @Reference
    private TypeTemplateService typeTemplateService;


    @RequestMapping("/findAll")
    public List<TbTypeTemplate> findAll() {

        return typeTemplateService.findAll();
    }


    @RequestMapping("/findPage")
    public PageResult findPage(int page, int rows) {

        return typeTemplateService.findPage(page, rows);
    }


    @RequestMapping("/search")
    public PageResult search(@RequestBody TbTypeTemplate TbTypeTemplate, int page, int rows  ){
        return typeTemplateService.findPage(TbTypeTemplate, page, rows);
    }


    @RequestMapping("/selectOptionList")
    public List<Map> selectOptionList(){

        return typeTemplateService.selectOptionList();
    }


    @RequestMapping("/selectOption")
    public List<Map> selectOption(){
        return typeTemplateService.selectOption();
    }


    @RequestMapping("/add")
    public Result add(@RequestBody TbTypeTemplate TbTypeTemplate){
        try {
            typeTemplateService.add(TbTypeTemplate);
            return new Result(true, "增加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "增加失败");
        }
    }

    @RequestMapping("/findOne")
    public TbTypeTemplate findOne(Long id){

        return typeTemplateService.findOne(id);
    }


    @RequestMapping("/update")
    public Result update(@RequestBody TbTypeTemplate TbTypeTemplate){
        try {
            typeTemplateService.update(TbTypeTemplate);
            return new Result(true, "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "修改失败");
        }
    }


    @RequestMapping("/delete")
    public Result delete(Long [] ids){
        try {
            typeTemplateService.delete(ids);
            return new Result(true,"");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"");
        }
    }


}
