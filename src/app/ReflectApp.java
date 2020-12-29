package app;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import dto.JoinDto;
import dto.LoginDto;

public class ReflectApp {
	
	static <T> void myReflect(T dto) {	// 모든 타입을 다 받을 수 있음
		Method[] methods = dto.getClass().getMethods();
		
		for (Method method : methods) {
			try {
				Object result = method.invoke(dto, "메소드 호출");
				System.out.println(result);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		Field[] fs = dto.getClass().getDeclaredFields();
		
		for (Field f : fs) {
			f.setAccessible(true);
			try {
				if(f.getName().equals("password")) {
					f.set(dto, "5678");
				}
				Object o = f.get(dto);
				System.out.println(o);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		LoginDto loginDto = new LoginDto();
		loginDto.setUsername("ssar");
		loginDto.setPassword("1234");
		
		JoinDto joinDto = new JoinDto();
		joinDto.setUsername("ssar");
		joinDto.setPassword("1234");
		joinDto.setEmail("ssar@nate.com");
		
		myReflect(joinDto);
	}
}
