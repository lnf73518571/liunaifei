//ͨ�õ����ݲ�����
package qst.com.tools;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class ConnDB {
	//�������Ӷ���properties
	public Connection conn=null;
	//Statementʵ��
	public Statement stmt=null;
	//����result����ʵ��
	public ResultSet rs=null;
	//ָ����Դ�ļ������λ��
	private static String propFileName="connDB.properties";
	//����һ��properties�����ʵ��
	private static Properties prop=new Properties();
	//���屣�����ݿ������ı���
	private static String dbClassName="com.mysql.jdbc.Driver";
	private static String dbUrl="jdbc:mysql://127.0.0.1:3306/db_demo1?user=root&password=";
	public ConnDB(){
		try{
			//��Properties�ļ���ȡ��inputstream������
			InputStream in=getClass().getResourceAsStream(propFileName);
			//ͨ���������������Properties�ļ�
			prop.load(in);
			//��ȡ���ݿ�������
			dbClassName=prop.getProperty("DB_CLASS_NAME");
			//��ȡURL
			dbUrl=prop.getProperty("DB_URL", dbUrl);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public static Connection getConnection(){
		Connection conn=null;
		try{
			//�������ݿ�����
			Class.forName(dbClassName).newInstance();
			//���������ݿ������
			conn=DriverManager.getConnection(dbUrl);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		if(conn==null){
			System.err.println("���棺�������ݿ�ʧ��"+dbClassName+dbUrl);
		}
		return conn;
	}
	public void close(){
		try{
			if(rs!=null){
				rs.close();
			}
			if(stmt!=null){
				stmt.close();
			}
			//��connection�����ʵ����Ϊ��ʱ���رմ˶���
			if(conn!=null){
				conn.close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	//4.ִ��select���ķ���
	public ResultSet executeQuery(String sql){
		try{
			//����getConnection��������connection�����һ��ʵ��
			conn=getConnection();
			stmt=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			//ִ��sql��䣬������ResultSet����rs
			rs=stmt.executeQuery(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		return rs;
	}
	//5.ִ��insert,delete,update ���ķ�����������Ӱ�������
	public int executeUpdate(String sql){
		int result=0;
		try{
			conn=getConnection();
			stmt=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			result=stmt.executeUpdate(sql);//ִ�и��²���
			

		}catch(Exception e){
			//����ʱ����Ӱ��������Ϊ0
			result=0;
			//e.printStackTrace();
		}
		return result;
	}
	
	
}
