package qst.com.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import qst.com.bean.MedicineBean;
import qst.com.dao.MedicineDao;


@WebServlet("/MedicineServlet")
public class MedicineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public MedicineServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=request.getParameter("action");
		if("add".equals(action)){
			this.add(request,response);
		}else if("selectmedicine".equals(action)){
			this.select(request, response);
		}
	}
	//該方法用來查詢数据库中已经存在的类型的药品
	private void select(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String typename=request.getParameter("typename");
		//通过typename来进行药品所属id的查询
		int typeID=MedicineDao.queryidbyname(typename);
		System.out.println("类别"+typeID);
		//根据查出来的typeID进入药品表查询该类别下的所有药品
		List<MedicineBean> list=MedicineDao.queryrsbyid(typeID);
		HttpSession session=request.getSession();
		session.setAttribute("list", list);
		response.sendRedirect("userlistMedicine.jsp");
		
		
	}

	private void add(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		//添加药品所需的步骤
		//1.获取传过来的要添加进去的值
		//2.连接数据库,若存在该药品，则进行数据相加更新，若不存在该药品，则直接进行添加进去即可
		//3.最后提示添加是否成功，成功则转入到work.jsp，不成功则转入到medicineadd.jsp
		String medicinename=request.getParameter("medicinename");
		String price=request.getParameter("price");
		int num = Integer.parseInt(request.getParameter("num"));
		int typeID=Integer.parseInt(request.getParameter("typeID"));
		MedicineDao medicineDao=new MedicineDao();
		if(medicineDao.add(medicinename,price,num,typeID)){
			response.sendRedirect("work.jsp");
		}else{
			response.sendRedirect("medicineadd.jsp");
		}
		
		
		
	}
	

}
