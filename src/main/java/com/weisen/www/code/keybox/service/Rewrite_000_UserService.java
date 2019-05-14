package com.weisen.www.code.keybox.service;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weisen.www.code.keybox.domain.Authority;
import com.weisen.www.code.keybox.domain.User;
import com.weisen.www.code.keybox.domain.Usertoken;
import com.weisen.www.code.keybox.repository.AuthorityRepository;
import com.weisen.www.code.keybox.repository.UserRepository;
import com.weisen.www.code.keybox.repository.UsertokenRepository;
import com.weisen.www.code.keybox.security.AuthoritiesConstants;
import com.weisen.www.code.keybox.web.rest.errors.LoginAlreadyUsedException;
import com.weisen.www.code.keybox.web.rest.vm.Rewrite_000_ManagedUserVM;

/**
 * Service class for managing users.
 */
@Service
@Transactional
public class Rewrite_000_UserService {

	private final Logger log = LoggerFactory.getLogger(Rewrite_000_UserService.class);

	private final UserRepository userRepository;

	private final PasswordEncoder passwordEncoder;

	private final AuthorityRepository authorityRepository;

	private final CacheManager cacheManager;

	private final UsertokenRepository usertokenRepository;

	public Rewrite_000_UserService(UserRepository userRepository, PasswordEncoder passwordEncoder,
			AuthorityRepository authorityRepository, CacheManager cacheManager,UsertokenRepository usertokenRepository) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.authorityRepository = authorityRepository;
		this.cacheManager = cacheManager;
		this.usertokenRepository = usertokenRepository;
	}

	public User registerUser(Rewrite_000_ManagedUserVM rewrite_000_ManagedUserVM) {
		userRepository.findOneByLogin(rewrite_000_ManagedUserVM.getLogin().toLowerCase()).ifPresent(existingUser -> {
			boolean removed = removeNonActivatedUser(existingUser);
			if (!removed) {
				throw new LoginAlreadyUsedException();
			}
		});
		User newUser = new User();
		String encryptedPassword = passwordEncoder.encode(rewrite_000_ManagedUserVM.getPassword());
		newUser.setLogin(rewrite_000_ManagedUserVM.getLogin());
		// new user gets initially a generated password
		newUser.setPassword(encryptedPassword);
		newUser.setFirstName(rewrite_000_ManagedUserVM.getLogin());
		newUser.setLastName(rewrite_000_ManagedUserVM.getLogin());
		newUser.setEmail(rewrite_000_ManagedUserVM.getLogin().toLowerCase() + "@localhost");
		newUser.setImageUrl("");
		newUser.setLangKey("zh-cn");
		// new user is not active
		newUser.setActivated(true);
		Set<Authority> authorities = new HashSet<>();
		authorityRepository.findById(AuthoritiesConstants.USER).ifPresent(authorities::add);
		newUser.setAuthorities(authorities);
		userRepository.save(newUser);
		this.clearUserCaches(newUser);
		log.debug("Created Information for User: {}", newUser);
		Usertoken usertoken = new Usertoken();
		usertoken.setUserid(newUser.getId().toString());
		usertoken.setState("1");
		usertoken.setEndDate(0L);
		usertokenRepository.save(usertoken);
		log.debug("Request to save Usertoken : {}", usertoken);
		return newUser;
	}

	private boolean removeNonActivatedUser(User existingUser) {
		if (existingUser.getActivated()) {
			return false;
		}
		userRepository.delete(existingUser);
		userRepository.flush();
		this.clearUserCaches(existingUser);
		return true;
	}

	private void clearUserCaches(User user) {
		Objects.requireNonNull(cacheManager.getCache(UserRepository.USERS_BY_LOGIN_CACHE)).evict(user.getLogin());
		Objects.requireNonNull(cacheManager.getCache(UserRepository.USERS_BY_EMAIL_CACHE)).evict(user.getEmail());
	}
}
