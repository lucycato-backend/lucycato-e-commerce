package org.lucycato.memberservice;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.common.SelfValidating;

@Getter
@NoArgsConstructor
public class TestRequest extends SelfValidating<TestRequest> {

    @NotBlank
    private String name;

    @Email
    @NotBlank
    private String email;

    public TestRequest(String name, String email) throws Exception {
        this.name = name;
        this.email = email;

        this.validateSelf();
    }
}
