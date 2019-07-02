package com.zx.demo.mapper;


import com.zx.demo.pojo.Emp;
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

    @Update("update emp set ename = #{ename}, esex = #{esex} ,epassword=#{epassword}, eaddress = #{eaddress} where eid =#{eid} ")
    public int update(Emp user);

    @Select("select count(*) from emp where ename = #{ename} and epassword=#{epassword}")
    public int login(Emp user);
}
