package com.weisen.www.code.keybox.web.rest;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.weisen.www.code.keybox.service.Rewrite_000_UserService;
import com.weisen.www.code.keybox.web.rest.errors.EmailAlreadyUsedException;
import com.weisen.www.code.keybox.web.rest.errors.InvalidPasswordException;
import com.weisen.www.code.keybox.web.rest.errors.InvalidLoginException;
import com.weisen.www.code.keybox.web.rest.errors.LoginAlreadyUsedException;
import com.weisen.www.code.keybox.web.rest.vm.Rewrite_000_ManagedUserVM;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * REST controller for managing the current user's account.
 */
@RestController
@RequestMapping("/api/rewrite")
@Api(tags={"000_用户操作接口"})
public class Rewrite_000_AccountResource {

	private final Rewrite_000_UserService rewrite_000_userService;

	public Rewrite_000_AccountResource(Rewrite_000_UserService rewrite_000_userService) {
		this.rewrite_000_userService = rewrite_000_userService;
	}

	/**
	 * POST /register : register the user.
	 *
	 * @param managedUserVM the managed user View Model
	 * @throws InvalidPasswordException  400 (Bad Request) if the password is
	 *                                   incorrect
	 * @throws EmailAlreadyUsedException 400 (Bad Request) if the email is already
	 *                                   used
	 * @throws LoginAlreadyUsedException 400 (Bad Request) if the login is already
	 *                                   used
	 */
	@ApiOperation(value="注册用户",notes="传入账号密码即可，账号不能重复，11位手机号，暂没有短信发送功能")
	@PostMapping("/register")
	@ResponseStatus(HttpStatus.CREATED)
	public void registerAccount(@Valid @RequestBody Rewrite_000_ManagedUserVM rewrite_000_ManagedUserVM) {
		if (!checkPasswordLength(rewrite_000_ManagedUserVM.getPassword())) {
			throw new InvalidPasswordException();
		}
		if (!checkLoginLength(rewrite_000_ManagedUserVM.getLogin())) {
			throw new InvalidLoginException();
		}
		rewrite_000_userService.registerUser(rewrite_000_ManagedUserVM);
	}

	private static boolean checkPasswordLength(String password) {
		return !StringUtils.isEmpty(password) && password.length() >= Rewrite_000_ManagedUserVM.PASSWORD_MIN_LENGTH
				&& password.length() <= Rewrite_000_ManagedUserVM.PASSWORD_MAX_LENGTH;
	}

	private static boolean checkLoginLength(String login) {
		return !StringUtils.isEmpty(login) && login.matches(Rewrite_000_ManagedUserVM.REGEX);
	}
}
