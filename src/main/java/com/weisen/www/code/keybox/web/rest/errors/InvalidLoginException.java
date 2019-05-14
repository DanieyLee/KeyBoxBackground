package com.weisen.www.code.keybox.web.rest.errors;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

public class InvalidLoginException extends AbstractThrowableProblem {

    private static final long serialVersionUID = 1L;

    public InvalidLoginException() {
        super(ErrorConstants.INVALID_LOGIN_TYPE, "Incorrect login", Status.BAD_REQUEST);
    }
}
