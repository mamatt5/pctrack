package com.fdmgroup.PCTrack.model;

import java.util.Comparator;

public class ProgramComparator implements Comparator<Program> {

	@Override
	public int compare(Program o1, Program o2) {
		return versionCompare(o1.getVersion(), o2.getVersion());
	}
	
	static int versionCompare(String version1, String version2) {
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");
        for (int i = 0; i < Math.min(v1.length, v2.length); i++) {
        	if (Integer.parseInt(v1[i]) > Integer.parseInt(v2[i])) {
        		return -1;
        	}
        	if (Integer.parseInt(v1[i]) < Integer.parseInt(v2[i])) {
        		return 1;
        	}
        }
        if (v1.length > v2.length) {
        	return -1;
        } else if (v1.length < v2.length) {
        	return 1;
        }
        return 0;
	}
}
