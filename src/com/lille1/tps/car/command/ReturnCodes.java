package com.lille1.tps.car.command;

public class ReturnCodes {

	public static final String CRLF = "\r\n";

	/**
	 * Service ready
	 */
	public static final String RC_220 = "220 Service Ready" + CRLF;

	/**
	 * Login success
	 */
	public static final String RC_230 = "230 Login success" + CRLF;

	/**
	 * PWD Response with path
	 */
	public static final String RC_257 = "257 {{0}}" + CRLF;

	/**
	 * Waiting for password
	 */
	public static final String RC_331 = "331 Waiting for password" + CRLF;

	/**
	 * Invalid login or password
	 */
	public static final String RC_430 = "430 Invalid login or password" + CRLF;

	/**
	 * Unexisting command
	 */
	public static final String RC_502 = "502 Unexisting command" + CRLF;

	/**
	 * Allows to fill a return code containing custom parameters with a parameters table
	 * @param returnCode The return code
	 * @param params The list of params to use to fill the return code
	 * Example : The return code is "257 {{0}}" with params equals to ["/home"], the return code will be compiled like that : 257 "/home"
	 * @return
	 */
	public static final String compile(String returnCode, final String... params) {
		String rtc = returnCode;
		for (int i = 0; i < params.length; ++i) {
			rtc = rtc.replace("{{" + (i) + "}}", params[i]);
		}
		return rtc;
	}
}
