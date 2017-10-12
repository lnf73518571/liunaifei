package qst.com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import qst.com.bean.MedicineBean;
import qst.com.bean.RecordBean;
import qst.com.tools.ConnDB;

public class MedicineDao {
	//根据工作人员传过来的typeID进行该类别下数量不满足的情况的药品
	
	public List<MedicineBean> selectmebyid(int typeID) {
		ConnDB conn=new ConnDB();
		List<MedicineBean> medicine=new ArrayList<MedicineBean>();
		MedicineBean medicineBean=null;
		String sql="select * from m_medicine where typeID='"+typeID+"' and num<10";
		System.out.println(sql);
		ResultSet rs=conn.executeQuery(sql);
		try {
			while(rs.next()){
				//找出数据并将其存入到对象中；
				medicineBean=new MedicineBean();
				medicineBean.setMedicinename(rs.getString("medicinename"));
				medicineBean.setNum(rs.getInt("num"));
				System.out.println(rs.getInt("num"));
				medicineBean.setTypeID(rs.getInt(5));
				System.out.println(rs.getInt(5));
				medicine.add(medicineBean);
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}finally{
			conn.close();
		}
		return medicine;
	}
	//数据库查询买过同一类的药品的用户
	public List<RecordBean> selectuserbyme(int typeID) {
		ConnDB conn=new ConnDB();
		List<RecordBean> record=new ArrayList<RecordBean>();
		RecordBean recordBean=null;
		String sql="select * from m_record where typeID="+typeID+"";
		//String sql="select * from m_record,m where typeID='"+typeID+"' and num<10";
		System.out.println(sql);
		ResultSet rs=conn.executeQuery(sql);
		try {
			while(rs.next()){
				//找出数据并将其存入到对象中；
				recordBean=new RecordBean();
				recordBean.setMedicineID(rs.getInt(7));
				recordBean.setUsername(rs.getString(3));
				recordBean.setTypeID(rs.getInt(5));
				record.add(recordBean);
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}finally{
			conn.close();
		}
		return record;
		
	}
	//数据库中用来添加药品的方法
	public boolean add(String medicinename, String price, int num, int typeID) {
		ConnDB conn=new ConnDB();
		String addmessage="";
		boolean result=false;
		ResultSet rs=querymedicine(medicinename);
		//先来查找该药品，调用方法.若已经查找到数据库中有数据，那么只进行更新就可以了，若没有数据则需要进行插入数据
		try {
			if(!rs.next()){
				String sql="insert into m_medicine(medicinename,price,num,typeID) values('"+medicinename+"','"+price+"','"+num+"','"+typeID+"')";
				int rtn=conn.executeUpdate(sql);
				if(rtn>0){
					addmessage="插入成功";
					result=true;
				}
				else{
					addmessage="插入失败";
				}
			}//原有数据加上存入数据进行更新数据库
			else{
				//查出原来的数据库中该条记录的num然后加上现在要存入的num，update
				System.out.println("111"+rs.getInt("num"));
				num+=rs.getInt("num");
				String sql_edit="update m_medicine set num='"+num+"' where medicinename='"+medicinename+"'";
				System.out.println(sql_edit);
				int rtn_edit=conn.executeUpdate(sql_edit);
				if(rtn_edit>0){
					addmessage="更新成功";
					result=true;
				}
				else{
					addmessage="更新失败";
				}
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return result;
		
		
		
	}
	//通过传过来的medicinename来进行药品的查询
	public ResultSet querymedicine(String medicinename){
		ConnDB conn=new ConnDB();
		String sql="select * from m_medicine where medicinename='"+medicinename+"'";
		System.out.println(sql);
		ResultSet rs=conn.executeQuery(sql);
		return rs;
		
	}
	
	//通过typename查出所属id
	public static int queryidbyname(String typename) {
		ConnDB conn=new ConnDB();
		String sql="select * from m_type where typename="+typename+"";
		System.out.println(sql);
		ResultSet rs=conn.executeQuery(sql);
		int i=0;
		try {
			if(rs.next()){
				i=rs.getInt("id");
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return i;
	}
	//进行该类别下所有药品的查询
	public static List<MedicineBean> queryrsbyid(int typeID) {
		ConnDB conn=new ConnDB();
		List<MedicineBean> medicine=new ArrayList<MedicineBean>();
		MedicineBean medicineBean=null;
		String sql="select * from m_medicine where typeID='"+typeID+"'";
		System.out.println(sql);
		ResultSet rs=conn.executeQuery(sql);
		try {
			while(rs.next()){
				medicineBean=new MedicineBean();
				//找出数据并将其存入到对象中；
				medicineBean.setMedicinename(rs.getString("medicinename"));
				medicineBean.setNum(rs.getInt("num"));
				System.out.println(rs.getInt("num"));
				medicineBean.setPrice(rs.getFloat("price"));
				medicineBean.setTypeID(rs.getInt(5));
				System.out.println(rs.getInt(5));
				medicine.add(medicineBean);
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}finally{
			conn.close();
		}
		return medicine;
		
	}
	//通过药品名查询返回价格
	public static float getPrice(String medicinename) {
		ConnDB conn=new ConnDB();
		String sql="select * from m_medicine where medicinename='"+medicinename+"'";
		System.out.println(sql);
		ResultSet rs=conn.executeQuery(sql);
		float price=0;
		try {
			if(rs.next()){
				price=rs.getFloat("price");
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return price;
	}
	public static int getTypeID(String medicinename) {
		ConnDB conn=new ConnDB();
		String sql="select * from m_medicine where medicinename='"+medicinename+"'";
		System.out.println(sql);
		ResultSet rs=conn.executeQuery(sql);
		int typeID=0;
		try {
			if(rs.next()){
				typeID=rs.getInt("typeID");
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return typeID;
	}
	public static int getMedicineID(String medicinename) {
		ConnDB conn=new ConnDB();
		String sql="select * from m_medicine where medicinename='"+medicinename+"'";
		System.out.println(sql);
		ResultSet rs=conn.executeQuery(sql);
		int medicineID=0;
		try {
			if(rs.next()){
				medicineID=rs.getInt("id");
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return medicineID;
	}
	
	//用户买完药以后用来更新数据库
	public static int updateMedicine(int numall, int num2,String medicinename) {
		ConnDB conn=new ConnDB();
		int num=numall-num2;
		String sql_edit="update m_medicine set num='"+num+"' where medicinename='"+medicinename+"'";
		System.out.println(sql_edit);
		int rtn_edit=conn.executeUpdate(sql_edit);
		return rtn_edit;
		
	}
	
}
