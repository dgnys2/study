package jpabook.jpashop.api;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.stream.Collectors;

//@Controller
//@ResponseBody
@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;


    @GetMapping("/api/v2/members")
    public Result memberV2(){

        List<Member> findMembers = memberService.findMembers();
        List<MemberDto> collect  = findMembers.stream()
                                                .map(m-> new MemberDto(m.getName()))
                                                .collect(Collectors.toList());

        return new Result(collect.size(),collect);
    }


    @Data
    @AllArgsConstructor
    static class Result<T> {
        private int count;
        private T data;
    }

    @Data
    @AllArgsConstructor
    static class MemberDto {
        private String name;

    }
//    @PostMapping("/api/v1/members")
//    public CreateMemberResponse saveMemberV1(@RequestBody @Valid Member member){
//        Long memberId = memberService.join(member);
//        return new CreateMemberResponse(memberId);
//    }

    @PostMapping("/api/v2/members")
    public CreateMemberResponse saveMemberV2(@RequestBody @Valid CreateMemberRequest request){

        Member member  = new Member();
        member.setName(request.getName());

        Long memberId = memberService.join(member);
        return new CreateMemberResponse(memberId);
    }

    @PutMapping("/api/v2/members/{id}")
    public UpdateMemberResponse updateMemberV2(
            @PathVariable("id") Long id,
            @RequestBody @Valid UpdateMemberRequest request){

        //command 와 query 를 분리하면 유지보수면에서 좋다.
        memberService.update(id,request.getName());
        Member findMember = memberService.findOne(id);
        return new UpdateMemberResponse(findMember.getId(),findMember.getName());

    }



    @Data
    static class UpdateMemberRequest {
        private String name;
    }


    @Data
    @AllArgsConstructor
    static class UpdateMemberResponse {
        private Long id;
        private String name;

    }


    @Data
    static class CreateMemberResponse{

        public CreateMemberResponse(Long id) {
            this.id = id;
        }
        private Long id;
    }

    @Data
    static class CreateMemberRequest{
        @NotEmpty
        private String name;
    }
}
