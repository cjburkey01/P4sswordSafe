package com.cjburkey.vault.local;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class LocalHelper {
	
	public static final File folder = new File(System.getProperty("user.home"), "/.ps/");
	public static final File infoFile = new File(folder, "/queryFind.info");
	
	public static List<LoginInfo> logins = new ArrayList<LoginInfo>();
	
	public static final File getPasswordFile() {
		if(!infoFile.getParentFile().exists()) infoFile.getParentFile().mkdirs();
		try {
			if(!infoFile.exists()) {
				FileWriter w = new FileWriter(infoFile);
				String write = "";
				for(int i = 0; i < 5; i ++) {
					write += UUID.randomUUID().toString() + "-";
				}
				w.write(write.substring(0, write.length() - 1));
				w.close();
			}
			String name = "";
			Scanner s = new Scanner(infoFile);
			if(s.hasNextLine()) {
				name = s.nextLine().trim();
			}
			s.close();
			return new File(folder, "/vault/" + name + ".doz");
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static final void loadLogins() {
		try {
			if(getPasswordFile().exists()) {
				FileOutputStream fos = new FileOutputStream(getPasswordFile());
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(logins);
				oos.close();
				fos.close();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public static final void saveLogins() {
		try {
			if(!getPasswordFile().getParentFile().exists()) getPasswordFile().getParentFile().mkdirs();
			if(!getPasswordFile().exists()) getPasswordFile().createNewFile();
			
			FileInputStream fis = new FileInputStream(getPasswordFile());
			ObjectInputStream ois = new ObjectInputStream(fis);
			Object in = ois.readObject();
			if(in instanceof ArrayList) {
				logins = (ArrayList<LoginInfo>) in;
			}
			ois.close();
			fis.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}