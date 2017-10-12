package qst.com.servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import qst.com.bean.MedicineBean;
import qst.com.bean.RecordBean;
import qst.com.dao.MedicineDao;
import qst.com.tools.ConnDB;

@WebServlet("/WorkUserServlet")
public class WorkUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public WorkUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String action=request.getParameter("action");
		if("wlogin".equals(action)){
			this.login(request,response);
			}else if("exit".equals(action)){
				this.exit(request,response);
			}else if("selectmedicine".equals(action)){
				this.selectmedicine(request,response);
			}else if("selectuserbyme".equals(action)){
				this.selectuserbyme(request,response);
			}
	}
	//工作人员查询购买该药品的所有用户
	private void selectuserbyme(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		int typeID=Integer.valueOf(request.getParameter("id"));
		MedicineDao medicineDao=new MedicineDao();
		List<RecordBean> medicineuser = medicineDao.selectuserbyme(typeID);
		HttpSession session = request.getSession();
		session.setAttribute("medicineuser", medicineuser);
		
		response.sendRedirect("ListMedicineUser.jsp");
		
	}

	//工作人员查询药品数量小于10的药品列表
	private void selectmedicine(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		int typeID=Integer.valueOf(request.getParameter("id"));
		//连接数据库，在dao层中进行
		MedicineDao medicineDao=new MedicineDao();
		List<MedicineBean> medicine = medicineDao.selectmebyid(typeID);
		//查询m_medicine表中num小于10的字段，并将其所属类型显示出来
		System.out.println(medicine);
		HttpSession session = request.getSession();
		session.setAttribute("medicine", medicine);
		
		response.sendRedirect("ListMedicine.jsp");
		
	}

	private void login(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{
		String worknum=request.getParameter("worknum");
		String password=request.getParameter("password");
		String message="";
		String url="";
		ConnDB conn=new ConnDB();
		String sql="select * from w_reg where worknum='"+worknum+"'";
		ResultSet rs=conn.executeQuery(sql);
		try{
			int id=0;
			if(rs.next()){
				id=rs.getInt("id");
				if(password.equals(rs.getString("password"))){
					HttpSession session=request.getSession();
					session.setAttribute("worknum", worknum);
					session.setAttribute("id", id);
					String sql_ins="insert into w_login(regID,worknum,password) values('"+id+"','"+worknum+"','"+password+"')";
					int rtn=conn.executeUpdate(sql_ins);
					System.out.println(sql_ins);
					if(rtn>0){
						message="登录成功！";
						url="work.jsp";
					}else{
						message="保存失败！请重新登录！";
						url="wlogin.jsp";
					}
				}else{
					message="密码错误";
					url="wlogin.jsp";
				}
			}else{
				message="没有该用户,请您先去注册";
				url="index.jsp";
			}
		}catch(Exception e){
			e.printStackTrace();
			message="失败了";
		}finally{
			conn.close();
		}
		request.setAttribute("message", message);
		request.setAttribute("url", url);
		request.getRequestDispatcher("wlogin_ok.jsp").forward(request, response);
	}
		
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}
	public void exit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		HttpSession session=request.getSession();
		session.invalidate();
		request.getRequestDispatcher("index.jsp").forward(request, response);
		
	}

}
