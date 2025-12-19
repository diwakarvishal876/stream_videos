package com.streaming.transcoding_service.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoUploadedEvent implements Serializable {
    private String videoId;      // The DB ID of the video
    private String fileName;     // Original file name
    private String minioBucket;  // Bucket name
    private String objectPath;   // Path/Key in MinIO (e.g., "uploads/uuid-video.mp4")
    private String contentType;  // e.g., "video/mp4"
    private String userEmail;
}