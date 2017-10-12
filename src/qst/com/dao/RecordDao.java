package qst.com.dao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import qst.com.tools.ConnDB;

public class RecordDao {
	//添加买药人的用户信息
	public static boolean addMessage(HttpServletRequest request, HttpServletResponse response) {
		boolean result=true;
		HttpSession session = request.getSession();
		int medicineID=(int) session.getAttribute("medicineID");
		float price=(float) session.getAttribute("price2");
		int typeID=(int) session.getAttribute("typeID");
		int num= (int) session.getAttribute("num2");
		String cardnum=(String) session.getAttribute("cardnum");
		String username=(String) session.getAttribute("userName");
		
		ConnDB conn=new ConnDB();
		String sql="insert into m_record (cardnum,username,price,typeID,num,medicineID) values ('"
						+cardnum
						+"','"
						+username
						+"',"
						+price
						+","
						+typeID
						+","
						+num
						+","
						+medicineID
						+")";
		System.out.println(sql);
		int rtn=conn.executeUpdate(sql);//���û���Ϣ���浽���ݱ���
		System.out.println("rtn="+rtn);
		if(rtn==0){
			result=false;
		}
		return result;
		
	}
	
}
