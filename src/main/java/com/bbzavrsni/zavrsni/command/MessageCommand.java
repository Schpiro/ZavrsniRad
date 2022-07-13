package com.bbzavrsni.zavrsni.command;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class MessageCommand {
    @NotNull(message = "messageBody must not be empty")
    private String messageBody;

    private Integer parentMessage;

    private String recipient;

    private String recipientGroup;

    @AssertTrue
    private boolean recipientExists(){
        return recipient != null || recipientGroup != null;
    }
}
