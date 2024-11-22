package member;

public interface MemberDAO {
	// 회원 가입
    int insert(MemberVO vo);

    // 사용자 검색 (userid)
    MemberVO selectByUserId(String userid);

    // 사용자 정보 전체 검색
    MemberVO selectUserInfo(String userid);
    
    // 회원 탈퇴
    boolean deleteAccount(String userid);
    
    int updateAccount(MemberVO vo);
}
