/**
 * The MyLogger class defines Logger object used for logging purpose.
 *
 * @author  Riya Malhotra
 * @version 1.0
 * @since   2020-05-21
 */

package com.myshopify.ecom_optimus.logManager;

import org.apache.log4j.Logger;

public class MyLogger {

	public static Logger log = Logger.getLogger(MyLogger.class); // Logger object is defined as static so that it can be
																	// directly in other classes.
}
