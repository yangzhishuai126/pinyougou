package com.pinyougou.sellergoods.service.impl;

import java.util.List;

import com.pinyougou.pojo.*;
import entity.Specification;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.mapper.TbBrandMapper;
import com.pinyougou.mapper.TbSpecificationMapper;
import com.pinyougou.mapper.TbSpecificationOptionMapper;
import com.pinyougou.sellergoods.service.BrandService;

import entity.PageResult;
@Service
public class BrandServiceImpl implements BrandService {

	@Autowired
	private TbBrandMapper brandMapper;


	
	@Override
	public List<TbBrand> findAll() {
		return brandMapper.selectByExample(null);
	}

	@Override
	public PageResult findPage(int page, int rows) {
		PageHelper.startPage(page, rows);
		Page<TbBrand> p = (Page<TbBrand>) brandMapper.selectByExample(null);
		return new PageResult(p.getTotal(), p.getResult());
	}
	@Override
	public void add(TbBrand brand) {
		brandMapper.insert(brand);
	}
	@Override
	public void update(TbBrand brand){
		brandMapper.updateByPrimaryKey(brand);
	}

	@Override
	public TbSpecification findOne(Long id){

		return brandMapper.selectByPrimaryKey(id);
	}


	@Override
	public void delete(Long[] ids) {
		for(Long id:ids){
			brandMapper.deleteByPrimaryKey(id);
		}
	}


	@Override
	public PageResult findPage(TbBrand brand, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		TbBrandExample example=new TbBrandExample();
		TbBrandExample.Criteria criteria = example.createCriteria();
		if(brand!=null){
			if(brand.getName()!=null && brand.getName().length()>0){
				criteria.andNameLike("%"+brand.getName()+"%");
			}
			if(brand.getFirstChar()!=null && brand.getFirstChar().length()>0){
				criteria.andFirstCharEqualTo(brand.getFirstChar());
			}
		}
		Page<TbBrand> page= (Page<TbBrand>)brandMapper.selectByExample(example);
		return new PageResult(page.getTotal(), page.getResult());
	}




}
