package test.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import test.beans.student;

public class stud 


{    
	JdbcTemplate t1;
	

	public void setT1(JdbcTemplate t1) {
		this.t1 = t1;
	}


	public int register(student s1) 
	{
		String sql;
		sql="insert into student (name,email)values('"+s1.getName()+"','"+s1.getEmail()+"')";
		return t1.update(sql);
	       
		
	}
	public List<student> getInfo()
	{
		return t1.query("select * from student ", new RowMapper<student>() {

			public student mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				student s =new student();
				s.setId(rs.getInt(1));
				s.setName(rs.getString(2));
				s.setEmail(rs.getString(3));
				
				return s;
			}
			
		});
	}
	
	public int deletedata (int id)
	{
		String sql ;
		sql="delete from student where id="+id;
		return t1.update(sql);
	}


	public student editdata(int id) {
		
		String sql;
		sql="select * from student where id=?";
		return t1.queryForObject(sql, new Object[] {id},new BeanPropertyRowMapper<student>(student.class));
	
		
	}

    public int updated(student s1) 
    {
		String sql;
		sql="update  student set name='"+s1.getName()+"',email='"+s1.getEmail()+"' where id="+s1.getId()+"";
		return t1.update(sql);
		
	}


	public student  login_check(String name, String email)
	{
		String sql;
		sql="select * from student where name=? and email=?";
		try
		{
		student s1= t1.queryForObject(sql, new Object[] {name,email},new RowMapper<student>() {
              public student mapRow(ResultSet rs, int rowNum) throws SQLException {
					student s1 =new student();
					s1.setId(rs.getInt(1));
					s1.setName(rs.getString(2));
					s1.setEmail(rs.getString(3));
					return s1;
				}
			});
		
			return s1;
			
		}
		catch (Exception e)
		{
			return null;
		}
		
	}

}
