package com.springboot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.model.Paciente;
import com.springboot.repository.PacienteRepository;

@CrossOrigin(origins ="http://localhost:3001" )
@RestController
@RequestMapping("/api")
public class PacienteController {
 
	@Autowired
	private PacienteRepository pacienteRepository;
	
	
	//get all pacientes
	@GetMapping("/pacientes")
	public List<Paciente> getAllPacientes(){
		return pacienteRepository.findAll();
	}
	
	
	//create a rest pacientes
	@PostMapping("/pacientes")
	public Paciente createPaciente(@RequestBody Paciente paciente) {
		return pacienteRepository.save(paciente);
	}
	
	//get paciente by id
	@GetMapping("/pacientes/{id}")
	public ResponseEntity<Paciente> getPacienteById(@PathVariable Long id) {
		Paciente paciente = pacienteRepository.findById(id).orElseThrow();
		return ResponseEntity.ok(paciente);
		
	}
	
	
	//update paciente
	@PutMapping("/pacientes/{id}")
	public ResponseEntity<Paciente> updateEntity(@PathVariable Long id, @RequestBody Paciente pacienteDetails){
		Paciente paciente = pacienteRepository.findById(id).orElseThrow();

		paciente.setNombre(pacienteDetails.getNombre());
		paciente.setPropietario(pacienteDetails.getPropietario());
		paciente.setTelefono(pacienteDetails.getTelefono());
		paciente.setFecha(pacienteDetails.getFecha());
		paciente.setHora(pacienteDetails.getHora());
		paciente.setSintomas(pacienteDetails.getSintomas());
		
		Paciente updatePaciente = pacienteRepository.save(paciente);
		return ResponseEntity.ok(updatePaciente);
	}
	
	
	//DELETE paciente REST API
	@DeleteMapping("/pacientes/{id}")
	public ResponseEntity<Map<String, Boolean>> deletePaciente( @PathVariable Long id){
		
		Paciente paciente = pacienteRepository.findById(id).orElseThrow();
		pacienteRepository.delete(paciente);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
		
	};
	

	
	
}
