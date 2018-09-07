package com.pinyougou.manager.controller;

import java.util.List;

import com.pinyougou.pojo.TbSpecification;
import entity.Result;
import entity.Specification;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.pojo.TbBrand;
import com.pinyougou.sellergoods.service.BrandService;

import entity.PageResult;

@RestController
@RequestMapping("/brand")
public class BrandController {

	@Reference
	private BrandService brandService;
	
	@RequestMapping("/findAll")
	public List<TbBrand> findAll() {

		return brandService.findAll();
	}




	@RequestMapping("/findPage")
	public PageResult findPage(int page, int rows) {

		return brandService.findPage(page, rows);
	}


	@RequestMapping("/add")
	public Result add(@RequestBody TbBrand brand){
		try {
			brandService.add(brand);
			return new Result(true,"");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false,"");
		}
	}


	@RequestMapping("/update")
	public Result update(@RequestBody TbBrand brand){
		try {
			brandService.update(brand);
			return new Result(true,"");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false,"");
		}
	}


	@RequestMapping("/findOne")
	public TbSpecification findOne(Long id){
		return brandService.findOne(id);
	}


	@RequestMapping("/delete")
	public Result delete(Long [] ids){
		try {
			brandService.delete(ids);
			return new Result(true,"");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false,"");
		}
	}


	@RequestMapping("/search")
	public PageResult search(@RequestBody TbBrand brand, int page, int rows  ){
		return brandService.findPage(brand, page, rows);
	}




}
