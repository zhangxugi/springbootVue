package com.zx.demo.server;


import com.zx.demo.pojo.*;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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


	//获取全部省份
	List<Province> selecctProvince();
	//根据省份获取城市
	List<City>getCityByProvinceId(String id);
	//根据省份获取城市
	List<Area>getAreaByCityId(String id);

	public List<Messages> selectmessages();

	public int insertmessages(Messages messages);

	public int deletemessages(int mid);


	public Messages selectmessagesbyid(int mid);

	public int updatemessages(Messages messages);

	String phone(String phone);
	int phonelogin(String phone);
}
