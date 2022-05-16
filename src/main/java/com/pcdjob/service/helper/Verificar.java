package com.pcdjob.service.helper;

import java.util.List;
import java.util.Optional;

public class Verificar {
	public static boolean verificarOptional(Optional<?> optional) {
		if(optional.isPresent()) {
			return true;
		} else {
			return false;
		}
	}
	
	public static void verificarList(List<?> list) {
		if(list.size() == 0) {
			throw new NullPointerException();
		}
	}
}
