package com.meeteam4.meeting.entity;

import com.meeteam4.meeting.dto.SearchProfilesRespDto;
import com.meeteam4.meeting.security.PrincipalUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.minidev.json.JSONUtil;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private int userId;
    private String name;
    private String username;
    private String password;
    private String nickname;
    private String email;
    private int roleId;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    private Student student;
    private Teacher teacher;

    private List<RoleRegister> roleRegisters;

    public List<SimpleGrantedAuthority> getAuthorities() {

        String roleName = null;
        if(roleId == 1) {
            roleName = "학생";
        } else if (roleId== 2) {
            roleName = "선생";
        }

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

            authorities.add(new SimpleGrantedAuthority(roleName));

        return authorities;
    }

    public PrincipalUser toprincipalUser() {
        return PrincipalUser.builder()
                .userId(userId)
                .username(username)
                .name(name)
                .roleId(roleId)
                .authorities(getAuthorities())
                .build();
    }



    public SearchProfilesRespDto toSearchProfilesRespDto() {
        return SearchProfilesRespDto
                .builder()
                .userId(userId)
                .nickname(nickname)
                .genderId(teacher.getGenderId())
                .email(email)
                .universityId(teacher.getUniversityId())
                .departmentName(teacher.getDepartmentName())
                .graduateStateId(teacher.getGraduateStateId())
                .build();
    }

}
