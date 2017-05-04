package com.lille1.tps.car.command.impl;

public class ReturnCodes {

	public static final String CRLF = "\r\n";

	/**
	 * Connection established
	 */
	public static final String RC_125 = "125 Connection established, beginning transfer" + CRLF;

	/**
	 * Command executed OK
	 */
	public static final String RC_150 = "150 Opening ASCII mode data connection for file list" + CRLF;

	/**
	 * Command executed OK
	 */
	public static final String RC_200 = "200 Command executed OK" + CRLF;
	/**
	 * Service ready
	 */
	public static final String RC_220 = "220 Service Ready" + CRLF;

	/**
	 * Deconnection
	 */
	public static final String RC_221 = "221 Deconnected" + CRLF;

	// /**
	// * permission linkCount owner group size lastModified name
	// */
	// public static final String RC_226 = "226
	// {{0}}\t\t{{1}}\t{{2}}\t{{3}}\t{{4}}\t{{5}}\t{{6}}" + CRLF;
	public static final String RC_226 = "226 Transfer complete" + CRLF;

	/**
	 * Passive mode
	 */
	public static final String RC_227 = "227 Passive mode" + CRLF;
	
	/**
	 * Extended Passive Mode
	 */
	public static final String RC_229 = "229 Entering Extended Passive Mode (|||{{0}}|)" + CRLF;
	
	/**
	 * Login success
	 */
	public static final String RC_230 = "230 Login success" + CRLF;

	/**
	 * Action on directory or file executed with success
	 */
	public static final String RC_250 = "250 CWD Changed" + CRLF;

	/**
	 * PWD Response with path
	 */
	public static final String RC_257 = "257 \"{{0}}\"" + CRLF;

	/**
	 * Waiting for password
	 */
	public static final String RC_331 = "331 Waiting for password" + CRLF;

	/**
	 * Invalid login or password
	 */
	public static final String RC_430 = "430 Invalid login or password" + CRLF;
	
	/**
	 * Syntax error
	 */
	public static final String RC_501 = "501 Syntax error" + CRLF;
	
	/**
	 * Unexisting command
	 */
	public static final String RC_502 = "502 Unexisting command" + CRLF;

	/**
	 * No such file or directory or already existing.
	 */
	public static final String RC_550 = "550 No such file or directory." + CRLF;

	/**
	 * Error while saving the file
	 */
	public static final String RC_451 = "451 Error while saving the file" + CRLF;


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
