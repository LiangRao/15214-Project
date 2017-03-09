package edu.cmu.cs.cs214.rec05.loggingsystem;

public interface Listener {
	void open();

	void writeError(String error);

	void writeDebug(String error);

	void close();

}
