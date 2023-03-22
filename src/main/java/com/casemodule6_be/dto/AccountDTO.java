package com.casemodule6_be.dto;

import com.casemodule6_be.model.Role;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.ManyToMany;
import java.util.List;

@Data
public class AccountDTO {
    private Long id;
    private String name;
    private String password;
    private String phone;
    private String email;
    private MultipartFile avatar;
    private boolean status;

}
