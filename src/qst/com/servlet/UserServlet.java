package qst.com.servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import qst.com.dao.MedicineDao;
import qst.com.dao.RecordDao;
import qst.com.tools.ConnDB;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		if ("reg".equals(action)) {
			this.register(request, response);
		} else if ("login".equals(action)) {
			this.login(request, response);
		} else if ("exit".equals(action)) {
			this.exit(request, response);
		}else if("buymedicine".equals(action)){
			this.buymedicine(request,response);
		}else if("confirm".equals(action)){
			this.confirm(request,response);
		}else if("cancel".equals(action)){
			this.cancel(request,response);
		}
	}
	//用户取消买药也需要存到数据库中，只不过此时将某些字段保存为0
	private void cancel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		HttpSession session=request.getSession();
		String userName = (String) session.getAttribute("userName");
		String cardnum=(String) session.getAttribute("cardnum");
		float price=0;
		int typeID=0;
		int num=0;
		int medicineID=0;
		ConnDB conn=new ConnDB();
		String sql="insert into m_record (cardnum,username,price,typeID,num,medicineID) values ('"
						+cardnum
						+"','"
						+userName
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
		int rtn=conn.executeUpdate(sql);
		if(rtn>0){
			session.setAttribute("errormessage", "用户没有购买药品，但是也将信息保存到数据库中了。");
		}else{
			session.setAttribute("errormessage", "用户没有购买药品，将信息保存到数据库中失败。");
		}
		response.sendRedirect("cancel.jsp");
		
	}
	//用户确认买药,则将其信息添加到m_record中并提示成功信息
	private void confirm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		boolean result=RecordDao.addMessage(request,response);
		HttpSession session=request.getSession();
		int num2=(int) session.getAttribute("num2");
		System.out.println("要购买的数量"+num2);
		String medicinename=(String) session.getAttribute("medicinename2");
		System.out.println("传过来的medicinename"+medicinename);
		MedicineDao medicineDao=new MedicineDao();
		ResultSet rs=medicineDao.querymedicine(medicinename);
		int numall=0;
		int rtn_edit=0;
		if(result){
			session.setAttribute("addrecord", "用户买药成功，保存用户信息成功");
		}else{
			session.setAttribute("addrecord", "用户买药成功，保存用户信息失败");
		}
		try {
			if(rs.next()){
				numall=rs.getInt("num");
				//用户买完药之后用来更新数据库
				rtn_edit=MedicineDao.updateMedicine(numall,num2,medicinename);
				session.setAttribute("numall", numall);
				if(rtn_edit>0){
					session.setAttribute("addme", "药品表数量更新成功");
				}else{
					session.setAttribute("addme", "药品表数量更新失败");
				}
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		response.sendRedirect("addrecord.jsp");
	}

	//用户购买药品操作
	private void buymedicine(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//从前台传入用户所需购买的药品的数量
		//然后进行数据库数据比对，将其价格取出来，然后进行总价计算显示给用户
		//若用户点击确认购买，则将其数据存到m_record中，
		//若用户点击取消购买，那么则将typeid.medicineid,price全部存为0
		String medicinename=request.getParameter("medicinename");
		int num=Integer.parseInt(request.getParameter("num"));
		System.out.println("药品名"+medicinename);
		System.out.println("数量"+num);
		float price=MedicineDao.getPrice(medicinename);
		int typeID=MedicineDao.getTypeID(medicinename);
		int medicineID=MedicineDao.getMedicineID(medicinename);
		HttpSession session = request.getSession();
		session.setAttribute("medicinename2", medicinename);
		session.setAttribute("num2", num);
		session.setAttribute("price2", price);
		session.setAttribute("typeID", typeID);
		session.setAttribute("medicineID", medicineID);
		response.sendRedirect("buyMedicine.jsp");
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String message = "";
		String url = "";
		ConnDB conn = new ConnDB();
		String sql = "select * from m_user where userName='" + userName + "'";
		ResultSet rs = conn.executeQuery(sql);
		try {
			int id = 0;
			if (rs.next()) {
				id = rs.getInt("id");
				if (password.equals(rs.getString("password"))) {
					HttpSession session = request.getSession();
					session.setAttribute("userName", userName);
					session.setAttribute("id", id);
					// session.setAttribute("cardID", rs.getInt("cardID"));
					// 进行向login表中插入数据，在这里有一个要求，
					// 当login表中已经存在该用户的数据时，则直接显示登录成功，不会再往里插入数据
					String sql_edit = "select * from m_login where userName='" + userName + "'";
					ResultSet rst = conn.executeQuery(sql_edit);
					if (rst.next()) {
						message = "登录成功，您的数据已经早就存在我们的数据库中了";
						url = "task.jsp";
					} else {
						String sql_ins = "insert into m_login(userID,username) values('" + id + "','" + userName + "')";
						int rtn = conn.executeUpdate(sql_ins);
						System.out.println(sql_ins);
						if (rtn > 0) {
							message = "登录成功！";
							url = "task.jsp";
						} else {
							message = "保存失败！请重新登录！";
							url = "login.jsp";
						}
					}
				} else {
					message = "密码错误";
					url = "login.jsp";
				}
			} else {
				message = "没有该用户,请您先去注册";
				url = "reg.jsp";
			}

		} catch (Exception e) {
			e.printStackTrace();
			message = "失败了";
		} finally {
			conn.close();
		}
		request.setAttribute("message", message);
		request.setAttribute("url", url);
		request.getRequestDispatcher("login_ok.jsp").forward(request, response);
	}

	private void register(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		String message = "";
		String url = "";
		ConnDB conn = new ConnDB();
		String sql = "select * from m_user where userName='" + userName + "'";
		ResultSet rs = conn.executeQuery(sql);
		try {
			if (rs.next()) {
				message = "注册失败！";
				url = "reg.jsp";
			} else {
				String sql_ins = "insert into m_user(userName,password,address,phone) values('" + userName + "','"
						+ password + "','" + address + "','" + phone + "')";
				int rtn = conn.executeUpdate(sql_ins);
				System.out.println(sql_ins);
				if (rtn > 0) {
					message = "注册成功，可以进行登录了！";
					url = "login.jsp";
				} else {
					message = "保存失败！请重新注册！";
					url = "reg.jsp";
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
		request.setAttribute("message", message);// ���û���Ϣ���浽HttpServletRequest
		request.setAttribute("url", url);
		request.getRequestDispatcher("register_ok.jsp").forward(request, response);

	}

	public void exit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();// ʹsessionʧЧ
		request.getRequestDispatcher("index.jsp").forward(request, response);

	}

}
