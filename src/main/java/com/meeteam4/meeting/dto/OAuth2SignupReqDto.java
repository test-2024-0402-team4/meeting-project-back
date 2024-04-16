package com.meeteam4.meeting.dto;


import com.meeteam4.meeting.entity.OAuth2;
import com.meeteam4.meeting.entity.Student;
import com.meeteam4.meeting.entity.Teacher;
import com.meeteam4.meeting.entity.User;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@Builder
public class OAuth2SignupReqDto {

    // 공통 부분
    @Pattern(regexp = "^[가-힣]{1,}$", message = "한글문자 형식이어야 합니다.")   // 이름은 한글이어야하고 한글자이상
    private String name;

    @Pattern(regexp = "^[A-Za-z0-9]{4,15}$", message = "영문 대소문자, 숫자 5 ~ 15자리 형식이어야 합니다." )  // 영어 + 숫자 조합으로 대소문자 구분
    private String username;

    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{7,128}$", message = "영문, 숫자, 특수문자를 포함한 5~128자리 형식이어야 합니다.")
    private String password;

    @Pattern(regexp = "^[가-힣A-Za-z0-9]{1,10}$", message = "공백 또는 특수문자가 포함되었습니다.")
    private String nickname;

    @Email(regexp = "^[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*\\.[a-zA-Z]{1,3}$", message = "이메일 형식이어야 합니다.")
    private String email;

    private int roleId;


    @Pattern(regexp = "^(19|20)\\d{2}(0[1-9]|1[0-2])(0[1-9]|[12]\\d|3[01])$", message = " - 없이 8자리 입력해주세요. 예) 20010707")
    private String birthDate;

    @Pattern(regexp = "^01(0|1|[6-9])[0-9]{3,4}[0-9]{4}$", message = " - 없이 11자리 입력해주세요. 예) 01012341234")
    private String phoneNumber;

    private int genderId;
    private int regionId;

    // -------------------------------------------------------------------------------------------------------------------------------
    // 학생부분
    private int studentTypeId;

    // -------------------------------------------------------------------------------------------------------------------------------
    // 선생부분
    private int universityId;
    private String departmentName;
    private int graduateStateId;

    @NotBlank
    private String oAuth2Name;
    @NotBlank
    private String providerName;

    public User toUserEntity(BCryptPasswordEncoder passwordEncoder) {
        return User.builder()
                .name(name)
                .username(username)
                .password(passwordEncoder.encode(password))
                .nickname(nickname)
                .email(email)
                .roleId(roleId)
                .build();
    }

    public Student toStudentEntity(int userId) {
        return Student.builder()
                .birthDate(birthDate)
                .userId(userId)
                .genderId(genderId)
                .studentTypeId(studentTypeId)
                .phoneNumber(phoneNumber)
                .regionId(regionId)
                .build();
    }

    public Teacher toTeacherEntity(int userId) {
        return Teacher.builder()
                .birthDate(birthDate)
                .userId(userId)
                .genderId(genderId)
                .universityId(universityId)
                .departmentName(departmentName)
                .graduateStateId(graduateStateId)
                .phoneNumber(phoneNumber)
                .build();
    }

    public OAuth2 toOAuth2Entity(int userId) {
        return OAuth2.builder()
                .oAuth2Name(oAuth2Name)
                .userId(userId)
                .providerName(providerName)
                .build();
    }
}