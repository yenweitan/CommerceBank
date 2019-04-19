package com.example.customertest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
class BranchController {
	
	private final BranchRepository repository;

	BranchController(BranchRepository repository) {
		this.repository = repository;
	}

	// Aggregate root

	@GetMapping("/Branchs")
	List<Branch> all() {
		return repository.findAll();
	}

	@PostMapping("/Branchs")
	Branch newBranch(@RequestBody Branch newBranch) {
		return repository.save(newBranch);
	}

	// Single item

	@GetMapping("/Branchs/{id}")
	Branch one(@PathVariable Long id) {

		return repository.findById(id)
				.orElseThrow(() -> new BranchNotFoundException(id));
	}

	@PutMapping("/Branchs/{id}")
	Branch replaceBranch(@RequestBody Branch newBranch, @PathVariable Long id) {

		return repository.findById(id)
				.map(Branch -> {
					Branch.setBranchId(newBranch.getBranchId());
				    Branch.setStreet(newBranch.getStreet());
				    Branch.setCity(newBranch.getCity());
				    Branch.setState(newBranch.getState());
				    Branch.setZip(newBranch.getZip());
				    Branch.setStudentLoansCovered(newBranch.getStudentLoansCovered());
				    Branch.setCheckingCovered(newBranch.getCheckingCovered());
				    Branch.setHomeEquityCovered(newBranch.getHomeEquityCovered());
				    Branch.setMortgageCovered(newBranch.getMortgageCovered());
				    Branch.setStudentLoansCovered(newBranch.getStudentLoansCovered());
				    Branch.setRetirementCovered(newBranch.getRetirementCovered());
				    Branch.setInvestmentCovered(newBranch.getInvestmentCovered());
				    Branch.setCreditCardCovered(newBranch.getCreditCardCovered());
				    Branch.setOtherCovered(newBranch.getOtherCovered());
					return repository.save(Branch);
				})
				.orElseGet(() -> {
					newBranch.setBranchId(id);
					return repository.save(newBranch);
				});
	}

	@DeleteMapping("/Branchs/{id}")
	void deleteBranch(@PathVariable Long id) {
		repository.deleteById(id);
	}
}
