package com.zx.demo.server;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zx.demo.mapper.Empmapper;
import com.zx.demo.pojo.Emp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Transactional
@Service
public class EmpserviceImpl implements Empservice {

	@Autowired
	private Empmapper ums;


	public Map<String,Object> getfenye(Integer pageNum, Integer pagesize){

		PageHelper.startPage(pageNum, pagesize);
		List<Emp> userInfoList = ums.selectuser();

		PageInfo<Emp> fenye = new PageInfo<>(userInfoList);

		Map<String,Object> jsonMap = new HashMap<String,Object>();

		jsonMap.put("ret", "0");
		jsonMap.put("msg", "SUCCESS");
		jsonMap.put("body", userInfoList);//数据结果
		jsonMap.put("total", fenye.getTotal());//获取数据总数
		jsonMap.put("pageSize", fenye.getPageSize());//获取长度
		jsonMap.put("pageNum", fenye.getPageNum());//获取当前页数


		return jsonMap;
	}

	public List<Emp> select() { return ums.selectuser(); }
	public int insert(Emp emp){return ums.insert(emp); }
	public int delete(int eid){return ums.delete(eid); }

	public Emp selectbyid(int eid){return ums.selectbyid(eid); }

	public int update(Emp user){ return ums.update(user); }

	public int login(Emp user){return ums.login(user); }


}
