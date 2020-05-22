package com.testing.enums;

public final class AllEnums {
	
	public static enum  AuthProvider {
	    local,
	    facebook,
	    google,
	    github,
	    twitter
	}
	

	public static enum  Role {
	    admin,
	    user
	    }
	
	public static enum ResultCode {
	    SUCCESS, ERROR, USERNAME_EXIST, EMAIL_EXIST, PHONE_EXIST, USER_NOT_FOUND, INVALID_PASSWORD, INVALID_TOKEN,
	    INVALID_USERNAME, INVALID_STATUS, INVALID_DATA, INVALID_CODE, CODE_EXPIRED, VERIFICATION_SENT, UNAUTHORIZED,
	    GROUP_NAME_EXIT, INVALID_ACESS, PASSWORD_NOT_MATCH, IP_NOT_ALLOWED, SCHEDULE_BOT_ALLOWED, PRODUCT_NAME_EXIST, TOO_FAST,
	    STILL_NOT_CHECKOUT, STILL_NOT_CHECKIN, STILL_NOT_LUNCH_OUT, STILL_NOT_LUNCH_IN, SKILL_ALREADY_EXIST;


	    ResultCode() {
	    }

	}
	
	public static enum  QuestionType {
	    CheckBox,
	    Text,
	    Radio
	}
}
