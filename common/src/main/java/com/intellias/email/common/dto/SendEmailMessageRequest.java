package com.intellias.email.common.dto;

import java.nio.ByteBuffer;
import java.util.List;
import java.util.Map;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SendEmailMessageRequest {
    @NotNull
    @NotEmpty
    private String from;
    private String subject;
    @NotNull
    @NotEmpty
    private List<String> to;
    private List<String> cc;
    private List<String> bcc;
    private boolean mimeMessage;
    @NotNull
    private String content;
    private Map<String, ByteBuffer> attachments;

}
