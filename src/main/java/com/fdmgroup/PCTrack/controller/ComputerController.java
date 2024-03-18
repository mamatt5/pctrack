package com.fdmgroup.PCTrack.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.fdmgroup.PCTrack.model.Computer;
import com.fdmgroup.PCTrack.model.Report;
import com.fdmgroup.PCTrack.model.SearchConfig;
import com.fdmgroup.PCTrack.service.ComputerService;
import com.fdmgroup.PCTrack.service.ReportService;

/**
 * Controller for Computer entity
 * Takes in Computer service and Report service to allow cascade delete if deleting a computer
 */

@RestController
@CrossOrigin("http://localhost:5173")
public class ComputerController {
	
	private ComputerService computerService;
	private ReportService reportService;
	
	@Autowired
	public ComputerController(ComputerService computerService, ReportService reportService) {
		super();
		this.computerService = computerService;
		this.reportService = reportService;
	}
	
	@GetMapping("computers")
	public List<Computer> getComputers() {
		return computerService.findAllComputers();
	}
	
	/**
	 * Finds the computer based on id
	 * @param computerId
	 * @return Computer
	 */
	@GetMapping("computers/{computerId}")
	public Computer findById(@PathVariable int computerId) {
		return computerService.findById(computerId);
	}
	
	@GetMapping("computers/programs/{programId}")
	public List<Computer> findByProgramId(@PathVariable int programId) {
		return computerService.findByProgramId(programId);
	}
	
	/**
	 * Finds the computer based on code through search config which is used in filtering results
	 * in front end
	 * @param searchConfig
	 * @return Computer
	 */
	@PostMapping("computers/search")
	public List<Computer> searchByComputerCode(@RequestBody SearchConfig searchConfig) {
		return computerService.searchByComputerCode(searchConfig.getComputerCode(), searchConfig.getRoomId(), searchConfig.getRole());
	}
	
	@PostMapping("computers")
	public Computer createNewComputer(@RequestBody Computer newComputer) {
		Computer computer = new Computer(newComputer.getComputerCode(), newComputer.getRoom());
		computerService.save(computer);
		computer.setProgramList(newComputer.getProgramList());
		computerService.update(computer);
		return computerService.findById(computer.getComputerId());
	}
	
	@PutMapping("computers")
	public Computer updateComputer(@RequestBody Computer newComputer) {
		computerService.update(newComputer);
		return computerService.findById(newComputer.getComputerId());
	}
	
	@DeleteMapping("computers/{computerId}")
	public void deleteComputer(@PathVariable int computerId) {
		for (Report report : reportService.findByComputerId(computerId)) {
			reportService.deleteById(report.getReportId());
		}
		computerService.deleteByComputerId(computerId);
	}

}
