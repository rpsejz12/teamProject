package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;


public class HGairDAO {

	static String sql_SELECT_ONE="select * from hg_air where air_num=?";// �װ��� ���� ����  ������ ��ȣ����
	static String sql_SELECT_ALL="select * from hg_air "; // ��ü �װ��� ��� 
	static String sql_UPDATE_SEATMIINUS="update HG_AIR set s_cnt = s_cnt -? where air_num=? ";
	//static String sql_INSERT="insert into hg_air values (?,?,?,?)";
	//static String sql_UPDATE="update  set =?,=?,=? where =?";

	private static Connection conn=null;
	private static PreparedStatement pstmt=null;

	

	//�װ��� ���� ����, ��ȣ ���� / �¼� ���� -- �ؾ��ϴ���,,
	public HGairVO airInfo (HGairVO vo){
		conn=JDBC.getConnection();
		HGairVO data=null;
		try {
			pstmt=conn.prepareStatement(sql_SELECT_ONE);
			pstmt.setString(1, vo.getAir_num());
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				data=new HGairVO();
				data.setAir_num(rs.getString("air_num"));
				data.setAir_name(rs.getString("air_name"));
				data.setDeparture(rs.getString("departure"));
				data.setArrive(rs.getString("arrive"));
				data.setFlight_date(rs.getString("flight_date"));
				data.setPrice(rs.getInt("price"));
				data.setS_cnt(rs.getInt("s_cnt"));	
			}
			rs.close();
			//System.out.println("�װ��� ���� ���� ����");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC.close(conn, pstmt);
		}
		return data;
	}




	//�װ��� ��� �˻�
	public ArrayList<HGairVO> checkAll(HGairVO vo) {
		conn=JDBC.getConnection();
		HGairVO data = null;
		ArrayList<HGairVO> datas =new ArrayList();
		try {
			pstmt = conn.prepareStatement(sql_SELECT_ALL);
			ResultSet rs = pstmt.executeQuery();
			boolean flag =true;
			while(rs.next()) {
				flag = false;
				data = new HGairVO();
				data.setAir_num(rs.getString("air_num"));
				data.setAir_name(rs.getString("air_name"));
				data.setDeparture(rs.getString("departure"));
				data.setArrive(rs.getString("arrive"));
				data.setFlight_date(rs.getString("flight_date"));
				data.setPrice(rs.getInt("price"));
				data.setS_cnt(rs.getInt("s_cnt"));
				datas.add(data);
			}
			rs.close();

			//System.out.println("�װ��� ��ü �������"); //Ȯ���غ���� ����
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBC.close(conn, pstmt);
		}
		return datas;
	}
	
	// ����� ������ ������� ��ü �װ��� �¼� ������Ʈ
}
