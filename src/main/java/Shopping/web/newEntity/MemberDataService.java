package Shopping.web.newEntity;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Shopping.web.newEntity.MemberDataRepository;
import Shopping.web.newEntity.MemberData;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberDataService {
    @Autowired
    private final MemberDataRepository memberDataRepository;

    //회원 정보 저장
    public void saveUsersData(MemberData memberData){
        memberDataRepository.save(memberData);
    }
    //회원 정보 조회
    public List<MemberData> memberDataList() {
        List<MemberData> memberList = memberDataRepository.findAll();
        return memberList;
    }

    public MemberData findMemberData(LoginDto loginDto) {
        System.out.println(loginDto.getId());
        MemberData memberData = memberDataRepository.findById(loginDto.getId());
        return memberData;
    }
}