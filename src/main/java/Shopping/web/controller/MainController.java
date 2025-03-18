package Shopping.web.controller;

import Shopping.web.newEntity.LoginDto;
import Shopping.web.newEntity.MemberData;
import Shopping.web.newEntity.MemberDataService;
import Shopping.web.productEntity.Product;
import Shopping.web.productEntity.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final MemberDataService memberDataService;

    private final ProductService productService;
    String loginState = "N";

    //로그인 화면 이동
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    //로그인 체크 컨트롤러
    @GetMapping("/loginCheck")
    public ResponseEntity<String> loginCheck(MemberData memberData) {
        //loginCheck?Id=1&password=1
        System.out.println("getId> " + memberData.getId());
        System.out.println("getPassword> " + memberData.getPassword());

        //NO 회원이 아님, OKA 어드민 계정, OKU 회원 계정
        String message1 = "NO";
        List<MemberData> memberDataList = memberDataService.memberDataList();
        for (int i =0; i<memberDataList.size(); i++){

            if (memberDataList.get(i).getId().equals(memberData.getId()) && memberDataList.get(i).getPassword().equals(memberData.getPassword())){

                if (memberDataList.get(i).getRole().equals("A")){
                    message1 = "OKA";
                } else {
                    message1 = "OKU";
                }
                //로그인 성공
                loginState = "Y";
            }
        }

        return ResponseEntity.ok(message1);
    }

    @GetMapping("/logOut")
    public ResponseEntity<HttpStatus> logOut(MemberData memberData) {
        loginState = "N";
        return ResponseEntity.ok(HttpStatus.OK);
    }



    //회원가입 화면 이동
    @GetMapping("/saveMember")
    public String saveMember(MemberData memberData) {
        memberData.setId(memberData.getId());
        memberData.setPassword(memberData.getPassword());
        memberData.setName(memberData.getName());
        memberData.setEmail(memberData.getEmail());
        memberData.setAddress(memberData.getAddress());
        memberData.setRole(memberData.getRole());
        memberDataService.saveUsersData(memberData);
        return "login";
    }
    //회원가입 안쓰는거
    @GetMapping("/Member")
    public String saveMember(LoginDto loginDto) {
        loginDto.setId(loginDto.getId());
        loginDto.setPassword(loginDto.getPassword());

        return "login";
    }

    //MemberData 조회
    @GetMapping("/findMember")
    public String findMember() {
        memberDataService.memberDataList();
        return "login";
    }

    //관리자, 사용자 사용 전체 메인
    @GetMapping("/main")
    public String defaultHome(Model model) {
        List<Product> productList = productService.getProductList();

        model.addAttribute("productList", productList);
        model.addAttribute("loginState", loginState);
        return "productDataList";
    }

    //상품 리스트 // 관리자 메인
    @GetMapping("/productList")
    public String productList(Model model) {
        List<Product> productList = productService.getProductList();
        model.addAttribute("productList", productList);
        model.addAttribute("loginState", loginState);
        return "productList";
    }

    @GetMapping("/myPage")
    public String myPage() {
        return "myPage";
    }
    //안쓰는거
    @GetMapping("/newEntity/MemberDataService")
    public String memberDataList(Model model) {
        List<MemberData> memberDataList = memberDataService.memberDataList();
        model.addAttribute("memberList", memberDataList);
        return "memberDataList";
    }
}
