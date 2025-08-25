package RegrasSintaticas;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface SyntaxErrorException {	
	
	Logger error = LoggerFactory.getLogger("error");
	Logger warn = LoggerFactory.getLogger("warn");
	
	default void logWarning(String message, int lineError) {
		warn.warn("C:" + lineError + " " + message);
	}
	
	default void logError(String message, int lineError) {
		error.error("C:" + lineError + " " + message);
	}
	
}
