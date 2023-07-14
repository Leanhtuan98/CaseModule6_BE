package com.casemodule6_be.dto.account;

import com.casemodule6_be.model.Role;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.Multipart;
import java.util.List;

@Data
public class RegisterForm {
    private String name;
    private String email;
    private String phone;
    private String password;
    private String rePassword;
    private String avatar;
    private List<Role> roles;
}
