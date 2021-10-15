package common.crawling;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import java.util.UUID;

import org.apache.jasper.tagplugins.jstl.core.Set;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import model.common.JDBC;

public class Review {
	
	public static void main(String[] args) throws SQLException {
		
		Connection conn = JDBC.getConnection();
		PreparedStatement pstmt = null;

		String url = "https://movie.naver.com/movie/running/current.naver"; // 영화 리스트
		String url2 = "";
		
		Document doc = null;
		
		int mpkInt = 1001;
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("액션", "AC");
		map.put("애니메이션", "AN");
		map.put("멜로/로맨스", "RO");
		map.put("드라마", "DR");
		map.put("다큐멘터리", "DC");
		
		try {
			
			doc = Jsoup.connect(url).get();
			Elements imgUrl = doc.select(".thumb"); // 각 이미지 url 목록
			
			// 상영 순위순 10개 영화 리뷰 10개씩 크롤링
			for (int i=0; i<10; i++) {
				url2 = "https://movie.naver.com";

//				System.out.println((url2 + imgUrl.get(i).select("a").attr("href")).replace("basic", "point"));
				doc = Jsoup.connect((url2 + imgUrl.get(i).select("a").attr("href")).replace("basic", "point")).get(); // 각 이미지 클릭 시 이동되는 해당 영화 정보 페이지 document
				
				Element ele = doc.select(".mv_info_area").get(0);
				String genre = ele.select(".info_spec > dd").first().select("span").get(0).text(); // 장르 추출
				if (genre.indexOf(",") > 0) { // 장르 여러개일 경우 맨 첫번째 값만 저장
		               genre = genre.substring(0, genre.indexOf(","));
		            }
//				System.out.println(genre);
				
				url2 = url2 + doc.select(".ifr_module2 > iframe").attr("src");
				System.out.println(url2);
				
				doc = Jsoup.connect(url2).get();
//				System.out.println(doc);
				
				conn.setAutoCommit(false); // 트랜 잭션 scope 때문에 while문 밖으로
				
				String mpk = "";
				
				int sum = 0; // 각 영화의 rating 합계 변수			
				int cnt = 0; // 리뷰 개수
				
				Iterator<Element> itr = doc.select(".score_result li").iterator();
				while (itr.hasNext()) {
					Element el = itr.next();
					
					String review = el.select(".score_reple p").select("span").last().text(); // 리뷰
					String rdate = el.select("dt em").last().text(); // 리뷰 작성 날짜 및 시간
					int rating = Integer.parseInt(el.select(".star_score em").text())/2; // 별점 최고점 10점 기준 -> 5점 기준
					
					if (review.contains("스포일러가 포함된 감상평입니다. 감상평 보기 ")) { // 전처리
						review = review.replace("스포일러가 포함된 감상평입니다. 감상평 보기 ", "");
					}

					
					String id = UUID.randomUUID().toString(); // id 난수 처리
					id = id.substring(0, 8);
					
					String pw = UUID.randomUUID().toString(); // pw 난수 처리
					pw = pw.substring(0, 5);
					
					String email = UUID.randomUUID().toString(); // email 난수 처리
					email = email.substring(0, 6) + "@naver.com";
					
					if (map.containsKey(genre)) {
						mpk = map.get(genre) + mpkInt;
					} else {
						mpk = "EX" + mpkInt;
					}
					
					
					// 평균을 위한 rating 합계
					sum += rating;
					cnt++;
					
					System.out.println("리뷰 : " + review);
					System.out.println("id : " + id);
					System.out.println("pw : " + pw);
					System.out.println("email : " + email);
					System.out.println("mpk : " + mpk);
					System.out.println("작성날짜 : " + rdate);
					System.out.println("별점 : " + rating);
					System.out.println("=========================");
					
					// DB에 저장		
					String reviewInsert = "insert into review (cmt, id, mpk, rdate, rating) values (?, ?, ? ,date_format(?,'%Y-%m-%d %H:%i'), ?)";
							//"insert into review values (review_seq.NEXTVAL, ?, ?, ? ,date_format(?,'%Y-%m-%d %h:%i'),?)";
					String clientInsert = "insert into client values (?, ?, ?)";
					
					pstmt = conn.prepareStatement(reviewInsert);
					pstmt.setString(1, review);
					pstmt.setString(2, id);
					pstmt.setString(3, mpk);
					pstmt.setString(4, rdate);
					pstmt.setInt(5, rating);
					if (pstmt.executeUpdate()==0) {
						conn.rollback();
						return;
					}
					
					pstmt = conn.prepareStatement(clientInsert);
					pstmt.setString(1, id);
					pstmt.setString(2, pw);
					pstmt.setString(3, email);
					if (pstmt.executeUpdate()==0) {
						conn.rollback();
						return;
					}
				}
			
				System.out.println();
				mpkInt++;
				
				double ratingAvg = (double) sum / cnt ; // movie 테이블에 들어갈 별점 평균
				ratingAvg = Math.round(ratingAvg*10)/10.0;
				
				
				String movieUpdate = "update movie set ratingavg = ? where mpk = ?";
				pstmt = conn.prepareStatement(movieUpdate);
				pstmt.setDouble(1, ratingAvg);
				pstmt.setString(2, mpk);
				if (pstmt.executeUpdate()==0) {
					conn.rollback();
					return;
				}
				conn.commit();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			conn.rollback();
			e.printStackTrace();
		} finally {
			JDBC.close(conn, pstmt);
		}
		
	}
	
}
