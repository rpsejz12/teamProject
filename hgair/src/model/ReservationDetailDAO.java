package model;

/*
<수정 사항>
1. 예약 조회 시 목록 조회
2. 운항편 조회 시 현재 시간을 지난 항공편은 나오지 않도록 
 */


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

// 예약 정보 조회/변경/취소
public class ReservationDetailDAO {

	private Connection conn = null;
	private PreparedStatement pstmt = null;

	// 로그인
	public Boolean login(MemberVO invo) { // member의 id, pw 필요

		conn = JDBC.getConnection();
		String sql = "select id from member where id = ? and pw = ?";
		Boolean flag = false;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, invo.getId());
			pstmt.setString(2, invo.getPw());
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				flag = true;
			}
			else {
				flag = false;
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC.close(conn, pstmt);
		}
		return flag;
	}
	

	// 예약(티켓) 정보 확인 -> 로그인 시 입력받은 id로 예약 정보 조회
	public TicketVO TicketCheck(TicketVO invo) {  // TicketVO의 id 필요

		conn = JDBC.getConnection();
		TicketVO outvo = null; // id 검색 결과 없을 시 null로 반환

		String sql_SELECT_TICKET = "select * from ticket_info where id = ?"; // 예약(티켓) 정보 조회

		try {
			pstmt = conn.prepareStatement(sql_SELECT_TICKET);
			pstmt.setString(1, invo.getId());
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				outvo = new TicketVO();
				outvo.setT_num(rs.getString("t_num"));  // 예약 번호 == 티켓 번호
				outvo.setId(rs.getString("id"));  // 사용자 id
				outvo.setAir_num(rs.getString("air_num")); // 운항편 번호
				outvo.setPayment(rs.getInt("payment")); // 결제 금액
				outvo.setUse_point(rs.getInt("use_point")); // 사용 마일리지
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC.close(conn, pstmt);
		}
		return outvo;
	}


	// 예약 변경 시 운항편 조회 -> 모든 운항편정보 데이터 조회
	public ArrayList<HGairVO> getAirinfoList() {

		conn = JDBC.getConnection();
		ArrayList<HGairVO> outvo = new ArrayList<HGairVO>();
		String sql_SELECT_HG_AIR = "select * from HG_air where to_date(flight_date, 'YYYY-MM-DD HH24:MI') > sysdate order by air_num"; // 운항편 조회

		try {
			pstmt = conn.prepareStatement(sql_SELECT_HG_AIR);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				HGairVO vo = new HGairVO();
				vo.setAir_num(rs.getString("air_num")); // 운항편 번호
				vo.setAir_name(rs.getString("air_name")); // 항공기명
				vo.setDeparture(rs.getString("departure")); // 출발지
				vo.setArrive(rs.getString("arrive")); // 도착지
				vo.setFlight_date(rs.getString("flight_date")); // 날짜
				vo.setPrice(rs.getInt("price")); // 가격
				vo.setS_cnt(rs.getInt("s_cnt")); // 예약 좌석 개수
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

	// 예약 변경 update -> 동일 가격 or 기존 가격보다 높은 가격 노선 변경만 가능
	// 동일 가격으로 변경 시 -> 운항편 번호만 ticket_info에 업데이트
	// 기존 가격보다 높은 가격으로 변경 시 -> 추가 마일리지 사용 불가, 운항편 번호와 추가 결제금액만 업데이트
	public void TicketChange(TicketVO invo) { // TicketVO의 t_num, 변경될 항공편 번호 air_num 속성값 필요

		conn = JDBC.getConnection();
		String sql = "update ticket_info set air_num = ?, payment = ? where t_num = ?";
		boolean isChange = false;
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, invo.getAir_num()); // 변경될 항공편 번호
			pstmt.setInt(2, invo.getPayment()); // 변경 후 결제 금액 = 항공권 정가 - 사용 마일리지
			pstmt.setString(3, invo.getT_num());
			
		} catch (SQLException e) {
			e.printStackTrace();
		}  finally {
			JDBC.close(conn, pstmt);
		}
	}

	// 예약 취소, 마일리지 사용 시 반환 update -> 구매 정보 delete ----> 트랜잭션 이용
	public void TicketCancel(TicketVO invo, MemberVO memvo) {
		// TicketVO의 입력받은 t_num로 마일리지 사용 여부 판단 -> 0인 경우 결제 시 사용하지 않고 적립

		conn = JDBC.getConnection();
		String sql = "select use_point from ticket_info where t_num = ?";
		try {
			conn.setAutoCommit(false); // 트랜잭션 처리

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, invo.getT_num());
			ResultSet rs = pstmt.executeQuery();
			int use_point = 0;
			if (rs.next()) {
				use_point = rs.getInt("use_point"); // 사용 마일리지
			}
			if (use_point == 0) { // 결제 시 적립한 경우 적립된 마일리지 차감 -> member 테이블 m_point에 업데이트
				// 결제 금액의 10% 적립금 차감
				sql = "update member set m_point = m_point - ? where id = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, invo.getPayment() /10);
				pstmt.setString(2, invo.getId());
				if (pstmt.executeUpdate() <= 0) {  // 데이터 삭제 실패
					conn.rollback(); // 트랜잭션
					return;
				}
			} else { // 사용 마일리지 반환 -> member 테이블 m_point에 업데이트
				sql = "update member set m_point = ? where id = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, memvo.getM_point()+ invo.getUse_point());
				pstmt.setString(2, invo.getId());
				pstmt.executeUpdate();
				if (pstmt.executeUpdate() <= 0) {  // 데이터 삭제 실패
					conn.rollback(); // 트랜잭션
					return;
				}
			}
			// ticket_info에서 취소 요청한 사용자 데이터 삭제 ---> 또는 flag 이용하는 방법도 고려
			String sql_DELETE_TICEKT = "delete from ticket_info where t_num = ?";

			pstmt = conn.prepareStatement(sql_DELETE_TICEKT);
			pstmt.setString(1, invo.getT_num());
			if (pstmt.executeUpdate() <= 0) {  // 데이터 삭제 실패
				conn.rollback(); // 트랜잭션
			}

			conn.commit(); // 트랜잭션

		} catch (SQLException e) {
			try {
				conn.rollback();
				return;
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} // 트랜잭션
			e.printStackTrace();
		} finally {
			JDBC.close(conn, pstmt);
		}
	}

	// 예약 시 운항편 잔여 좌석 수 update (--)
	public void SeatCntMinus(HGairVO avo, TicketVO tvo) {

		conn = JDBC.getConnection();
		String sql = "update HG_air set s_cnt = s_cnt - ? where air_num = ?";

		try {
			int s_cnt =  tvo.getp_cnt();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, s_cnt);
			pstmt.setString(2, avo.getAir_num());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC.close(conn, pstmt);
		}
	}
	
	// 예약 취소 시 운항편 잔여 좌석 수 update (++)
	public void SeatCntPlus(HGairVO avo, TicketVO tvo) {
		
		conn = JDBC.getConnection();
		String sql = "update HG_air set s_cnt = s_cnt + ? where air_num = ?";

		try {
			int s_cnt = tvo.getp_cnt();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, s_cnt);
			pstmt.setString(2, avo.getAir_num());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC.close(conn, pstmt);
		}
	}
	

}
