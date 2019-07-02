package com.zx.demo.server;


import com.zx.demo.pojo.Emp;

import java.util.List;
import java.util.Map;


public interface Empservice {

	public Map<String,Object> getfenye(Integer pageNum, Integer pagesize);
	public List<Emp> select();
	public int insert(Emp user);
	public int delete(int eid);
	public Emp selectbyid(int eid);
	public int update(Emp user);
	public int login(Emp user);


}
