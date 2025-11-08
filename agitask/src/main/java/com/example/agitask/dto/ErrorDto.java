package com.example.agitask.dto;

import java.time.LocalDateTime;

public record ErrorDto (String message,
                        String detaisl,
                        LocalDateTime timeStamp){

}
