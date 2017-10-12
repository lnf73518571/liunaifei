package qst.com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;


import qst.com.bean.CardBean;
import qst.com.bean.RecordBean;
import qst.com.tools.ConnDB;

public class CardDao {

	public int queryalluser() {
		ConnDB conn=new ConnDB();
		String sql="select * from m_login where 1=1";
		System.out.println(sql);
		ResultSet rs=conn.executeQuery(sql);
		int login_id=0;
		/*try {
			while(!(rs.next())){
				login_id=rs.getInt("id");
				System.out.println("111");
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}*/
		try {
			if(rs.last()){
				login_id=rs.getInt("id");
				System.out.println("111");
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return login_id;
		
	}
	//通过当前登录的用户名进行查询数据的存在性
	public RecordBean querybyname(String userName) {
		ConnDB conn=new ConnDB();
		String sql="select * from m_record where username='"+userName+"'";
		System.out.println(sql);
		ResultSet rs=null;
		RecordBean recordBean = null;
		try {
			rs=conn.executeQuery(sql);
			if(rs.next()){
				recordBean = new RecordBean();
				System.out.println("1"+rs.getString("cardnum"));
				recordBean.setId(rs.getInt("id"));
				recordBean.setUsername(rs.getString("username"));
				recordBean.setCardnum(rs.getString("cardnum"));
			}
			
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		System.out.println("111");
		return recordBean;
	}
	//获取card表中第一条数据
	public CardBean catchfirst() {
		ConnDB conn=new ConnDB();
		String sql="select * from m_card where 1=1";
		System.out.println(sql);
		ResultSet rs=null;
		CardBean cardBean=null;
		try {
			rs=conn.executeQuery(sql);
			if(rs.first()){
				cardBean = new CardBean();
				cardBean.setCardnum(rs.getString("cardnum"));	
		} 
		}catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return cardBean;
		
	}
	//删除m_card表中第一条数据
	public int deletefirst(String cardnum) {
		ConnDB conn=new ConnDB();
		String sql="delete from m_card where cardnum='"+cardnum+"'";
		System.out.println(sql);
		int rs=conn.executeUpdate(sql);
		return rs;	
	}
	
	
}
