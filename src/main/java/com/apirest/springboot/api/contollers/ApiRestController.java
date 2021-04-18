package com.apirest.springboot.api.contollers;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.*;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.apirest.springboot.api.errores.BadRequestException;
import com.apirest.springboot.api.responses.Horario;

@RestController
@RequestMapping("/")
public class ApiRestController {	
	
	private Pattern patronTime = Pattern.compile("[0-9]{2}:[0-9]{2}:[0-9]{2}");
	
	@CrossOrigin(origins= "http://localhost:3000")
	@PostMapping("/time")
	public  Map<String, Horario> horarios(@RequestParam String time, @RequestParam String timezone) {

		Horario horario = new Horario(time, timezone);
		Map<String, Horario> map = new HashMap<String, Horario>(); 

		Matcher m = patronTime.matcher(time);
		boolean result = m.matches();
		
		String utc = horario.getTimezone();
		Integer utc1 = Integer.parseInt(utc);
		
		String[] parts = horario.getTime().split(":");
		Integer part1 = Integer.parseInt(parts[0]);
		Integer part2 = Integer.parseInt(parts[1]);
		Integer part3 = Integer.parseInt(parts[2]);

		if (!result) {
			throw new BadRequestException("Error en formato de la time, debe ser min '00:00:00' o max '23:59:59'");
		}else if(utc1 < -12 || utc1 > 12 || 
				part1 > 24 || part1 < 0 || part2 > 59 ||
				part2 < 0 || part3 > 59){
			throw new BadRequestException("Error en formato de la time, debe ser min '00:00:00' o max '23:59:59'");
		}else{
			
			Integer timeMod = (part1 + utc1);
			if (timeMod > 24) {
				timeMod = timeMod - 24;
			}
			
			String horaOk = timeMod.toString();
			
			if(timeMod == 24) {
				horaOk = "00";
			}else if(timeMod > 0 && timeMod < 9 ) {
				horaOk = "0"+timeMod;
			}
			
			String timeNueva = horaOk + ":" +part2 + ":"+part3;
			
			horario.setTime(timeNueva);
			
			map.put("response", horario);

			return map;
		}
	}
}
