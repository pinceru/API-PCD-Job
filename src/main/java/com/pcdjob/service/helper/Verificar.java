package com.pcdjob.service.helper;

import java.util.Optional;

public class Verificar {
	public static boolean verificarOptional(Optional<?> optional) {
		if(optional.isPresent()) {
			return true;
		} else {
			return false;
		}
	}
	
}
