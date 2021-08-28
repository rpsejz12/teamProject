package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;


public class HGairDAO {

	static String sql_SELECT_ONE="select * from hg_air where air_num=?";// 항공편 선택 보기  운항편 번호선택
	static String sql_SELECT_ALL="select * from hg_air "; // 전체 항공편 출력 
	static String sql_UPDATE_SEATMIINUS="update HG_AIR set s_cnt = s_cnt -? where air_num=? ";
	//static String sql_INSERT="insert into hg_air values (?,?,?,?)";
	//static String sql_UPDATE="update  set =?,=?,=? where =?";

	private static Connection conn=null;
	private static PreparedStatement pstmt=null;

	

	//항공권 선택 보기, 번호 선택 / 좌석 언재 -- 해야하는지,,
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
			//System.out.println("항공편 선택 보기 수행");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC.close(conn, pstmt);
		}
		return data;
	}




	//항공권 모두 검색
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

			//System.out.println("항공편 전체 보기수행"); //확인해볼라고 넣음
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBC.close(conn, pstmt);
		}
		return datas;
	}
	
	// 예약시 예약한 사람수와 전체 항공기 좌석 업데이트
}
