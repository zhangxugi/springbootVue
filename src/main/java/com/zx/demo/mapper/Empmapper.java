package com.zx.demo.mapper;


import com.zx.demo.pojo.*;
import org.apache.ibatis.annotations.*;

import java.util.List;


public interface Empmapper<T>  {


  /*  select * from(select a.*,rownum rn from
            (select * from t_articles) a where rownum < 11) where rn>5*/
    @Select("select * from emp order by eid ")
    public List<Emp> selectuser();

    @Insert("insert into emp(ename,epassword,esex,ecreaton,eaddress) values(#{ename},#{epassword},#{esex}, sysdate(),#{eaddress})")
    public int insert(Emp u);

    @Delete("delete from emp where eid =#{eid}")
    public int delete(int eid);

    @Select("select *  from emp where eid =#{eid}")
    public Emp selectbyid(int eid);

    @Update("update emp set ename = #{ename}, esex = #{esex} ,epassword=#{epassword},ecreaton=#{ecreaton},eaddress = #{eaddress} where eid =#{eid} ")
    public int update(Emp user);

    @Select("select count(*) from emp where ename = #{ename} and epassword=#{epassword}")
    public int login(Emp user);

    //获取全部省份
  @Select("select * from province")
    List<Province> selecctProvince();
  //根据省份获取城市
  @Select("select * from city where father=#{father}")
   List<City>getCityByProvinceId(String father);
  //根据省份获取城市
  @Select("select * from area where father=#{father}")
  List<Area>getAreaByCityId(String father);

  @Select("select * from messages order by mid ")
  public List<Messages> selectmessages();

  @Insert("insert into messages(company,phone) values(#{company},#{phone})")
  public int insertmessages(Messages messages);

  @Delete("delete from messages where mid =#{mid}")
  public int deletemessages(int mid);

  @Select("select *  from messages where mid =#{mid}")
  public Messages selectmessagesbyid(int mid);

  @Update("update messages set company = #{company}, phone = #{phone}  where mid =#{mid} ")
  public int updatemessages(Messages messages);


  //判断手机号是不是存在
@Select("select phone from messages where phone=#{phone}")
  String phone(String phone);
//手机号登录
  @Select("select * from messages where phone = #{phone} ")
   int phonelogin(String phone);
}

