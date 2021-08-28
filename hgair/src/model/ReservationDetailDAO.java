package model;

/*
<���� ����>
1. ���� ��ȸ �� ��� ��ȸ
2. ������ ��ȸ �� ���� �ð��� ���� �װ����� ������ �ʵ��� 
 */


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

// ���� ���� ��ȸ/����/���
public class ReservationDetailDAO {

	private Connection conn = null;
	private PreparedStatement pstmt = null;

	// �α���
	public Boolean login(MemberVO invo) { // member�� id, pw �ʿ�

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
	

	// ����(Ƽ��) ���� Ȯ�� -> �α��� �� �Է¹��� id�� ���� ���� ��ȸ
	public TicketVO TicketCheck(TicketVO invo) {  // TicketVO�� id �ʿ�

		conn = JDBC.getConnection();
		TicketVO outvo = null; // id �˻� ��� ���� �� null�� ��ȯ

		String sql_SELECT_TICKET = "select * from ticket_info where id = ?"; // ����(Ƽ��) ���� ��ȸ

		try {
			pstmt = conn.prepareStatement(sql_SELECT_TICKET);
			pstmt.setString(1, invo.getId());
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				outvo = new TicketVO();
				outvo.setT_num(rs.getString("t_num"));  // ���� ��ȣ == Ƽ�� ��ȣ
				outvo.setId(rs.getString("id"));  // ����� id
				outvo.setAir_num(rs.getString("air_num")); // ������ ��ȣ
				outvo.setPayment(rs.getInt("payment")); // ���� �ݾ�
				outvo.setUse_point(rs.getInt("use_point")); // ��� ���ϸ���
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC.close(conn, pstmt);
		}
		return outvo;
	}


	// ���� ���� �� ������ ��ȸ -> ��� ���������� ������ ��ȸ
	public ArrayList<HGairVO> getAirinfoList() {

		conn = JDBC.getConnection();
		ArrayList<HGairVO> outvo = new ArrayList<HGairVO>();
		String sql_SELECT_HG_AIR = "select * from HG_air where to_date(flight_date, 'YYYY-MM-DD HH24:MI') > sysdate order by air_num"; // ������ ��ȸ

		try {
			pstmt = conn.prepareStatement(sql_SELECT_HG_AIR);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				HGairVO vo = new HGairVO();
				vo.setAir_num(rs.getString("air_num")); // ������ ��ȣ
				vo.setAir_name(rs.getString("air_name")); // �װ����
				vo.setDeparture(rs.getString("departure")); // �����
				vo.setArrive(rs.getString("arrive")); // ������
				vo.setFlight_date(rs.getString("flight_date")); // ��¥
				vo.setPrice(rs.getInt("price")); // ����
				vo.setS_cnt(rs.getInt("s_cnt")); // ���� �¼� ����
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

	// ���� ���� update -> ���� ���� or ���� ���ݺ��� ���� ���� �뼱 ���游 ����
	// ���� �������� ���� �� -> ������ ��ȣ�� ticket_info�� ������Ʈ
	// ���� ���ݺ��� ���� �������� ���� �� -> �߰� ���ϸ��� ��� �Ұ�, ������ ��ȣ�� �߰� �����ݾ׸� ������Ʈ
	public void TicketChange(TicketVO invo) { // TicketVO�� t_num, ����� �װ��� ��ȣ air_num �Ӽ��� �ʿ�

		conn = JDBC.getConnection();
		String sql = "update ticket_info set air_num = ?, payment = ? where t_num = ?";
		boolean isChange = false;
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, invo.getAir_num()); // ����� �װ��� ��ȣ
			pstmt.setInt(2, invo.getPayment()); // ���� �� ���� �ݾ� = �װ��� ���� - ��� ���ϸ���
			pstmt.setString(3, invo.getT_num());
			
		} catch (SQLException e) {
			e.printStackTrace();
		}  finally {
			JDBC.close(conn, pstmt);
		}
	}

	// ���� ���, ���ϸ��� ��� �� ��ȯ update -> ���� ���� delete ----> Ʈ����� �̿�
	public void TicketCancel(TicketVO invo, MemberVO memvo) {
		// TicketVO�� �Է¹��� t_num�� ���ϸ��� ��� ���� �Ǵ� -> 0�� ��� ���� �� ������� �ʰ� ����

		conn = JDBC.getConnection();
		String sql = "select use_point from ticket_info where t_num = ?";
		try {
			conn.setAutoCommit(false); // Ʈ����� ó��

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, invo.getT_num());
			ResultSet rs = pstmt.executeQuery();
			int use_point = 0;
			if (rs.next()) {
				use_point = rs.getInt("use_point"); // ��� ���ϸ���
			}
			if (use_point == 0) { // ���� �� ������ ��� ������ ���ϸ��� ���� -> member ���̺� m_point�� ������Ʈ
				// ���� �ݾ��� 10% ������ ����
				sql = "update member set m_point = m_point - ? where id = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, invo.getPayment() /10);
				pstmt.setString(2, invo.getId());
				if (pstmt.executeUpdate() <= 0) {  // ������ ���� ����
					conn.rollback(); // Ʈ�����
					return;
				}
			} else { // ��� ���ϸ��� ��ȯ -> member ���̺� m_point�� ������Ʈ
				sql = "update member set m_point = ? where id = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, memvo.getM_point()+ invo.getUse_point());
				pstmt.setString(2, invo.getId());
				pstmt.executeUpdate();
				if (pstmt.executeUpdate() <= 0) {  // ������ ���� ����
					conn.rollback(); // Ʈ�����
					return;
				}
			}
			// ticket_info���� ��� ��û�� ����� ������ ���� ---> �Ǵ� flag �̿��ϴ� ����� ���
			String sql_DELETE_TICEKT = "delete from ticket_info where t_num = ?";

			pstmt = conn.prepareStatement(sql_DELETE_TICEKT);
			pstmt.setString(1, invo.getT_num());
			if (pstmt.executeUpdate() <= 0) {  // ������ ���� ����
				conn.rollback(); // Ʈ�����
			}

			conn.commit(); // Ʈ�����

		} catch (SQLException e) {
			try {
				conn.rollback();
				return;
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} // Ʈ�����
			e.printStackTrace();
		} finally {
			JDBC.close(conn, pstmt);
		}
	}

	// ���� �� ������ �ܿ� �¼� �� update (--)
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
	
	// ���� ��� �� ������ �ܿ� �¼� �� update (++)
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
