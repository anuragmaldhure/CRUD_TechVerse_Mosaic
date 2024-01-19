package com.app.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import static org.springframework.http.MediaType.IMAGE_GIF_VALUE;
import static org.springframework.http.MediaType.IMAGE_JPEG_VALUE;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

import com.app.dto.EmployeeDTO;
import com.app.dto.SigninRequest;
import com.app.entities.Employee;
import com.app.service.EmployeeService;
import com.app.service.ImageHandlingService;

@RestController
@RequestMapping("/employees")
@CrossOrigin(origins = "http://localhost:3000")
@Validated
public class EmployeeController {
	// dep :
	@Autowired // (required = true)
	private EmployeeService empService;

	// dep
	@Autowired
	@Qualifier("image_folder")
	private ImageHandlingService imgService;

	public EmployeeController() {
		System.out.println("in ctor of " + getClass());
	}

	// REST API end point
	// URL : http://localhost:8080/employees/
	// Method : GET
	// resp : List<Emp>
	@GetMapping
	public List<Employee> listAllEmps() {
		System.out.println("in list all emps");
		return empService.getAllEmps();
	}

	// URL : http://localhost:8080/employees/{deptId}
	// Method : POST
	// Payload : deptId + emp details
	// Resp : Emp resp
	@PostMapping("/{deptId}")
	public ResponseEntity<?> addEmpDetails(@PathVariable 
			@NotNull(message="Dept id can't be null") Long deptId,
			@RequestBody @Valid  EmployeeDTO empDto) {
		System.out.println("in add emp " + empDto);
		//Sending complete resp pkt containing : resp sts code n body
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(empService.addEmpDetails(deptId,empDto));
	}

	// URL : http://localhost:8080/employees/empId
	// Method : GET
	// resp : detached emp or exc
	@GetMapping("/{empId}")
	public Employee getEmpDetails(@PathVariable Long empId) {
		System.out.println("in get emp dtls " + empId);
		return empService.getEmpDetails(empId);
	}

	// send emp details : whose adhaar card is created before specific date
	// http://host:port/employees/card_date/{cardDate} : (yr-mon-day)
	// method : GET
	@GetMapping("/card_date/{cardDate}")
	public List<EmployeeDTO> getEmpsByCardDate(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate cardDate)

	{
		System.out.println("in get by card date " + cardDate);
		return empService.getEmpsByCardDate(cardDate);
	}

	// URL : http://localhost:8080/employees/signin
	// Method : POST
	// payload : emp req (em ,pwd)
	// resp in case of success : SC 200 + emp details
	// resp in case of failure : api resp (SC 404)
	@PostMapping("/signin")
	public EmployeeDTO authenticateEmp(@RequestBody 
			@Valid SigninRequest request) {
		System.out.println("in auth emp " + request);
		return empService.authenticateEmployee(request);	
		
	}
	
	

//  upload image from clnt n saving it either on db or in server side folder
	// http://host:port/employees/images/{empId} ,
	// method=POST , req param :
	// multipart file(image data)
	@PostMapping(value = "/images/{empId}", 
			consumes = "multipart/form-data") //consumes : required ONLY for swagger testing
	public ResponseEntity<?> uploadImage(@PathVariable Long empId, 
			@RequestParam MultipartFile imageFile)
			throws IOException {
		System.out.println("in upload img " + empId);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(imgService.uploadImage(empId, imageFile));
	}

	// serve(download image) of specific emp
	// http://host:port/employees/images/{empId} , method=GET
	@GetMapping(value = "/images/{empId}", 
			produces = { IMAGE_GIF_VALUE, IMAGE_JPEG_VALUE, IMAGE_PNG_VALUE })
	public ResponseEntity<?> serveEmpImage(@PathVariable Long empId) throws IOException {
		System.out.println("in download img " + empId);
		return ResponseEntity.ok(imgService.downloadImage(empId));
	}
	// Pagination demo
	// Get all emps
	// http://host:port/employees/paginate , method=GET
	// req params : pageNumber , def val 0 , optional
	// pageSize : def val 3 , optional

	@GetMapping("/paginate")
	public ResponseEntity<?> getAllEmpsPaginated(@RequestParam(defaultValue = "0", required = false) int pageNumber,
			@RequestParam(defaultValue = "3", required = false) int pageSize) {
		System.out.println("in get all emps " + pageNumber + " " + pageSize);
		List<EmployeeDTO> list = empService.getAllEmployees(pageNumber, pageSize);
		if (list.isEmpty())
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		// emps found
		return ResponseEntity.ok(list);
	}

}
