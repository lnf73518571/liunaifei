package qst.com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import qst.com.bean.CardBean;
import qst.com.bean.RecordBean;
import qst.com.dao.CardDao;

@WebServlet("/CardServlet")
public class CardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public CardServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=request.getParameter("action");
		if("catchnum".equals(action)){
			this.catchnum(request,response);
		}if("catchcard".equals(action)){
			this.catchcard(request,response);
		}
	}
	//获取数据库中卡号进行买药
	private void catchcard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		//用户登录之后，若要进行取卡号操作，则需要先进行记录表中用户的信息，如果是存在该用户根据名字，
		//则将卡号取出来，提示给用户，说已经存在卡号信息，然后可以直接进行买药操作
		//如果查询没有查询到记录表中用户的信息，那么从卡表中给用户分配一个数据并随之删除
		
		//根据传过来的登录用户名userName
		HttpSession session=request.getSession();
		String userName=(String) session.getAttribute("userName");
		System.out.println("用户名："+userName);
		//调用方法进行查询记录表中是否存在数据
		CardDao cardDao=new CardDao();
		RecordBean recordBean = cardDao.querybyname(userName);
		System.out.println(recordBean);
		try {
			if(recordBean!=null){
				session.setAttribute("cardnum", recordBean.getCardnum());
				response.sendRedirect("alredaylogin.jsp");
			}else{
				//记录表中没有出现用户数据
				//从卡表中取出第一个结果集
				System.out.println("222");
				CardBean cardBean=cardDao.catchfirst();
				System.out.println("取卡");
				if(cardBean!=null){
					String cardnum=cardBean.getCardnum();
					System.out.println(cardnum);
					session.setAttribute("cardnum", cardnum);
					//删除第一个结果集
					if(cardDao.deletefirst(cardnum)>0){
						session.setAttribute("deletemessage", "删除成功，取得卡号成功");
					}else{
						session.setAttribute("deletemessage", "删除未成功，会影响接下来的人员取卡号哟");
					}
					
				}
				response.sendRedirect("catchcard.jsp");
			}
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		
	}

	//用户登录成功之后进行挂号处理
	//挂号处理步骤：从用户登录表中进行获取，按照序号进行取号处理，序号到多少，就显示您的所取得号码是多少
	private void catchnum(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//进行当前登录表中所有用户的查询
		CardDao cardDao=new CardDao();
		//查询出表中最后一条记录，将其序号加1变成此位用户的买药顺序
		int login_id=cardDao.queryalluser();
		HttpSession session=request.getSession();
		session.setAttribute("login_id", login_id+1);
		response.sendRedirect("number.jsp");
	}

}
