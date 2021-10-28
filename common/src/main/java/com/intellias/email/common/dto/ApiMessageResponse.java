package com.intellias.email.common.dto;

import static com.intellias.email.common.utils.Constants.SUCCESS_MESSAGE;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiMessageResponse<B> {
    private B item;
    private String message;

    public static <C> ApiMessageResponse<C> success(final C item) {
        return new ApiMessageResponse<>(item, SUCCESS_MESSAGE);
    }
}
