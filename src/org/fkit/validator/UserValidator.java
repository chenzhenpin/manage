package org.fkit.validator;
import org.fkit.table.User;
import org.springframework.stereotype.Repository;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;



public class UserValidator implements Validator {
	
	public boolean supports(Class<?> clazz){
		//注意User不能导错
		return User.class.isAssignableFrom(clazz);
	}
	public void validate(Object target,Errors errors){
		ValidationUtils.rejectIfEmpty(errors, "username", null,"用户名不能为空的");
		ValidationUtils.rejectIfEmpty(errors, "password", null,"密码不能为空的");
		User user =(User)target;
		if(!user.getUsername().equals("chen")){
			errors.rejectValue("username", null, "用户名不正确");	
		}
		if(!user.getPassword().equals("11")){
			errors.rejectValue("password", null, "密码不正确");	
		}
	}

}
