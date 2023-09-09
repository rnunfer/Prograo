package com.prograo.dto;

import java.util.Date;

public interface CalificationDTO {

    Long getIdSeeker();
    String getNameSeeker();
    String getProfilePhotoSeeker();
    Long getIdCalification();
    Double getNoteCalification();
    String getDescriptionCalification();
    String getImageCalification();
    Date getDateCalification();
    int getProjectId();
}
