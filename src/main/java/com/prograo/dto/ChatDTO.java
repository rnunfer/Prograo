package com.prograo.dto;

import java.util.Date;

public interface ChatDTO {

    Long getChatId();
    String getChatMessage();
    Date getChatDate();
    Long getUserId();
    String getUserProfilePhoto();
}
