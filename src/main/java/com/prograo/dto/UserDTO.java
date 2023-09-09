package com.prograo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {

    private Long id;
    private String name;
    private String email;
    private String title;
    private String profilePhoto;
    private String status;
    private boolean verified;
    private String city;
    private String country;
    private String userType;
}
