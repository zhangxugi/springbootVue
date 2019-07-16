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

  /*
   # 星号代替，需要屏蔽几位就输入几个字符，这儿是 4 位星号
        '****',
        # user_name = 手机号字段。取第 1 位开始一共 3 个字符（手机号前 3 位
        substring(u.phone, 1, 3),
        # 取第 8 位开始一共 4 个字符（手机号后 4 位
          substring(u.phone, 8, 4)
   */
 // @Select("SELECT u.mid,u.company,u.phone,concat_ws('****',substring(u.phone, 1, 3),substring(u.phone, 8, 4)) AS 'phones' FROM messages AS u ")
  @Select("select * from messages")
  public List<Messages> selectmessages();

  @Insert("insert into messages(company,phone,phones) values(#{company},#{phone},#{phones})")
  public int insertmessages(Messages messages);

  @Delete("delete from messages where mid =#{mid}")
  public int deletemessages(int mid);

  @Select("select *  from messages where mid =#{mid}")
  public Messages selectmessagesbyid(int mid);

  @Update("update messages set company = #{company}, phone = #{phone},phones = #{phones}  where mid =#{mid} ")
  public int updatemessages(Messages messages);


  //判断手机号是不是存在
@Select("select phone from messages where phone=#{phone}")
  String phone(String phone);
//手机号登录
  @Select("select * from messages where phone = #{phone} ")
   int phonelogin(String phone);
}

