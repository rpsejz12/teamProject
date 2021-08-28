package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TicketDAO {
	static String sql_SELECT_TICKET = "select * from ticket_info where id = ?"; // 예약(티켓) 정보 조회
	static String sql_INSERT="insert into ticket_info values(?)";
	static String sql_INSERT_reservation="insert into ticket_info values(?,?,?,?,?,?)";
	static String sql_SELECT_all ="select * from ticket_info";

	private Connection conn=null;
	private PreparedStatement pstmt=null;


	// 예약 확인
	// 예약(티켓) 정보 확인 -> 로그인 시 입력받은 id로 예약 정보 조회
	public ArrayList<TicketVO> TicketCheck(MemberVO memvo) {  // TicketVO의 id 필요

		conn = JDBC.getConnection();
		ArrayList<TicketVO> outvo = new ArrayList<TicketVO>();
		try {
			pstmt = conn.prepareStatement(sql_SELECT_TICKET);
			pstmt.setString(1, memvo.getId());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				TicketVO vo = new TicketVO();
				vo.setT_num(rs.getString("t_num"));  // 예약 번호 == 티켓 번호
				vo.setId(rs.getString("id"));  // 사용자 id
				vo.setAir_num(rs.getString("air_num")); // 운항편 번호
				vo.setPayment(rs.getInt("payment")); // 결제 금액
				vo.setUse_point(rs.getInt("use_point")); // 사용 마일리지
				vo.setp_cnt(rs.getInt("p_num"));
				outvo.add(vo);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC.close(conn, pstmt);
		}
		return outvo;
	}

	public ArrayList<TicketVO> tCheckAll() {
		conn=JDBC.getConnection();
		TicketVO data =null;
		ArrayList<TicketVO> datas = new ArrayList();
		try {
			pstmt = conn.prepareStatement(sql_SELECT_all);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				data = new TicketVO();
				data.setT_num(rs.getString("t_num"));
				data.setId(rs.getString("id"));
				data.setAir_num(rs.getString("air_num"));
				data.setPayment(rs.getInt("payment"));
				data.setUse_point(rs.getInt("use_point"));
				data.setp_cnt(rs.getInt("p_num"));
				datas.add(data);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC.close(conn, pstmt);
		}
		return datas;

	}

	// 예약하는 메서드
	public void airReservation(TicketVO vo) {
		conn=JDBC.getConnection();
		try {
			pstmt =conn.prepareStatement(sql_INSERT_reservation);
			pstmt.setString(1, vo.getT_num());
			pstmt.setString(2, vo.getId());
			pstmt.setString(3, vo.getAir_num());
			pstmt.setInt(4, vo.getPayment());
			pstmt.setInt(5, vo.getUse_point());
			pstmt.setInt(6, vo.getp_cnt());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC.close(conn, pstmt);
		}
	}



}